/**  
 * @Title: IndexingPlugin.java
 * @Package com.cms.scaffold.mybatis.interceptor
 * 
 * @author zhangjiahengpoping@gmail.com
 * @date 2017-8-9
 */
package com.cms.scaffold.plugin.mybatis.interceptor;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;


/**
 * MySQL查询语句性能分析功能:检测索引是否存在
 * @author zhangjiahengpoping@gmail.com
 * @date 2017-8-9
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
	RowBounds.class, ResultHandler.class }) })
public class IndexingPlugin implements Interceptor {

	private static final Logger logger = LoggerFactory.getLogger(IndexingPlugin.class);
	//系统环境
	private static final String ENV = "LOCAL";
	
	/**
	 * 获取数据库连接
	 * <p>
	 *
	 * @param transaction
	 * @param statementLog
	 * @return
	 * @throws SQLException
	*/
	protected Connection getConnection(Transaction transaction, Log statementLog ) throws SQLException {
		Connection connection = transaction.getConnection();
		return connection;
	}


	protected void printWarn(Configuration configuration, MappedStatement mappedStatement, BoundSql boundSql,
                             Connection connection, Object parameter ) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			StringBuilder explain = new StringBuilder("EXPLAIN ");
			explain.append(boundSql.getSql());
			String sqlExplain = explain.toString();
			StaticSqlSource sqlsource = new StaticSqlSource(configuration, sqlExplain, boundSql.getParameterMappings());
			MappedStatement.Builder builder = new MappedStatement.Builder(configuration, "explain_sql", sqlsource,
					SqlCommandType.SELECT);
			builder.resultMaps(mappedStatement.getResultMaps()).resultSetType(mappedStatement.getResultSetType())
					.statementType(mappedStatement.getStatementType());
			MappedStatement query_statement = builder.build();
			DefaultParameterHandler handler = new DefaultParameterHandler(query_statement, parameter, boundSql);
			stmt = connection.prepareStatement(sqlExplain);
			handler.setParameters(stmt);
			rs = stmt.executeQuery();
			int count = 0;
			while ( rs.next() ) {
				count++;
				String type = rs.getString("type");
				if ( "ALL".equals(type) ) {
					logger.error(boundSql.getSql());
					logger.error("使用了全表扫描的方式!");
				}
				if ( rs.getString("key") == null ) {
					logger.error(boundSql.getSql());
					logger.warn("没有使用索引,可能存在性能问题!");
				}
				int rows = rs.getInt("rows");
				if ( rows > 500 ) {
					logger.error(boundSql.getSql());
					logger.trace("影响行数为" + rows + ",可能存在性能问题!");
				}
				String extra = rs.getString("Extra");
				if ( extra != null && extra.contains("Using temporary") ) {
					logger.error(boundSql.getSql());
					logger.warn("使用临时表,可能存在性能问题!");
				}
				if ( extra != null && extra.contains("Using filesort") ) {
					logger.error(boundSql.getSql());
					logger.warn("使用文件排序,可能存在性能问题!");
				}
			}
			if ( count > 1 ) {
				logger.error(boundSql.getSql());
				logger.error("您的sql语句中用了子查询或者连接查询,为了保证性能和可扩展性,请不要使用子查询或者连接查询,尽量使用n+1查询或者字段冗余!");
			}

		} catch ( Exception e ) {

		} finally {
			if ( rs != null ) {
				rs.close();
				rs = null;
			}
			if ( stmt != null ) {
				stmt.close();
				stmt = null;
			}
		}
	}


	@Override
	public Object intercept(Invocation invocation ) throws Throwable {
		//产品和测试环境不检测索引
		if ("PRODUCT".equals(ENV)) {
			return invocation.proceed();
		}
		Object result = invocation.proceed();
		//返回结果是空,explain没有意义
		if ( result instanceof List) {
			List<?> re = (List<?>) result;
			if ( re.isEmpty() ) {
				return result;
			}
		}

		try {
			Object parameter = invocation.getArgs()[1];
			MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
			Configuration configuration = mappedStatement.getConfiguration();
			BoundSql boundSql = mappedStatement.getBoundSql(parameter);
			Executor exe = (Executor) invocation.getTarget();
			Connection connection = getConnection(exe.getTransaction(), mappedStatement.getStatementLog());
			printWarn(configuration, mappedStatement, boundSql, connection, parameter);
		} catch ( Exception e ) {
			logger.error("索引拦截监控出错", e);
		}
		return result;

	}


	@Override
	public Object plugin(Object target ) {
		return Plugin.wrap(target, this);
	}


	@Override
	public void setProperties( Properties properties ) {
	}
}
