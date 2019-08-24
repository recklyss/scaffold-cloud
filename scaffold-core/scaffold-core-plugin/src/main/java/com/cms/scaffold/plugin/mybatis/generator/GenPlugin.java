package com.cms.scaffold.plugin.mybatis.generator;

import cn.hutool.core.date.DateUtil;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Context;

import java.sql.Types;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;


/**
 * 自定义插件
 */
public class GenPlugin extends PluginAdapter {
    private Set<String> mappers = new HashSet<String>();
    // 注释生成器
    private CommentGeneratorConfiguration commentCfg;

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public void setContext(Context context) {
        super.setContext(context);
        // 设置默认的注释生成器
        commentCfg = new CommentGeneratorConfiguration();
        commentCfg.setConfigurationType(GenCommentGenerator.class.getCanonicalName());
        context.setCommentGeneratorConfiguration(commentCfg);
        // 支持oracle获取注释#114
        context.getJdbcConnectionConfiguration().addProperty("remarksReporting", "true");
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String mappers = this.properties.getProperty("mappers");
        for (String mapper : mappers.split(",")) {
            this.mappers.add(mapper);
        }
    }

    /**
     * 生成的Mapper接口
     *
     * @param interfaze
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
                                   IntrospectedTable introspectedTable) {
        // 获取实体类
        FullyQualifiedJavaType entityType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        // import接口
        for (String mapper : mappers) {
            interfaze.addImportedType(new FullyQualifiedJavaType(mapper));
            interfaze.addSuperInterface(new FullyQualifiedJavaType(mapper + "<" + entityType.getShortName() + ">"));
        }
        // import实体类
        interfaze.addImportedType(entityType);
        return true;
    }

    /**
     * 拼装SQL语句生成Mapper接口映射文件
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        XmlElement rootElement = document.getRootElement();
        // 数据库表名
        String tableName = introspectedTable.getFullyQualifiedTableNameAtRuntime();
        // 主键
        IntrospectedColumn pkColumn = introspectedTable.getBaseColumns().get(0);

        // 公共字段
        StringBuilder columnSQL = new StringBuilder();
        // IF判断语句
        StringBuilder ifSQL = new StringBuilder();
        // 要插入的字段(排除自增主键)
        StringBuilder saveColumn = new StringBuilder("insert into ").append(tableName).append("\n");
        saveColumn.append("\t <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >\n");

        // 要保存的值
        StringBuilder saveValue = new StringBuilder();
        saveValue.append("    <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >\n");
        // 拼装更新字段
        StringBuilder updateSQL =
                new StringBuilder("update ").append(tableName).append(" set ").append(pkColumn.getActualColumnName())
                .append(" = #{item.").append(pkColumn.getJavaProperty()).append("}\n");
        // 拼装更新所有字段
        StringBuilder updateAllSQL =
                new StringBuilder("update ").append(tableName).append(" set ").append(pkColumn.getActualColumnName())
                .append(" = #{item.").append(pkColumn.getJavaProperty()).append("}\n");
        // 数据库字段名
        String columnName = null;
        // java字段名
        String javaProperty = null;

        for (IntrospectedColumn introspectedColumn : introspectedTable.getAllColumns()) {
            columnName = MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn);

            javaProperty = introspectedColumn.getJavaProperty();
            // 拼接字段
            columnSQL.append(columnName).append(",");
            // 拼接IF语句
            if (isNumber(introspectedColumn)) {
                ifSQL.append("          <if test=\"null != ").append(javaProperty).append(" and '' != ")
                        .append(javaProperty).append(" or 0 ==").append(javaProperty).append("\">");
            } else {
                ifSQL.append("          <if test=\"null != ").append(javaProperty).append(" and '' != ").append(javaProperty).append("\">");
            }

            ifSQL.append("and ").append(columnName).append(" = #{").append(javaProperty).append("}</if>\n");
            if (!pkColumn.getJavaProperty().equals(introspectedColumn.getJavaProperty())) {
                saveColumn.append("\t  <if test=\"null != item.").append(javaProperty).append("\"> ").append(columnName).append(",</if>\n");

                saveValue.append("\t  <if test=\"null != item.").append(javaProperty).append("\"> ").append("#{item.").append(javaProperty)
                        .append("},</if>\n");
            }
            // 拼接SQL
            if (!introspectedColumn.isAutoIncrement()) {

                updateAllSQL.append("\t  , ").append(columnName).append(" = #{item.").append(javaProperty).append(
                        "}\n");
                // 时间格式用now()作为值
                /*
                 * if(Types.TIMESTAMP == introspectedColumn.getJdbcType()){
                 * saveValue.append(", now()"); }else{ saveValue.append(
                 * ", #{item.").append(javaProperty).append("}"); }
                 */

                updateSQL.append("      <if test=\"null != item.").append(javaProperty).append("\">");
                updateSQL.append(", ").append(columnName).append(" = #{item.").append(javaProperty).append("}</if>\n");
            }
        }
        String columns = columnSQL.substring(0, columnSQL.length() - 1);
        rootElement.addElement(createSql("sql_columns", columns));

        String whereSQL = MessageFormat.format("<where>\n{0}\t</where>", ifSQL.toString());
        String mapSql = "\t  <foreach collection=\"map.keys\" item=\"k\" separator=\"and\">\n " +
                "\t      <if test=\"null != map[k]\">\n" +
                "\t          a.${k} = #{map[${k}]}\n" +
                "\t      </if>\n" +
                "\t  </foreach>\n";
        String whereMapSQL = MessageFormat.format("<where>\n{0}\t</where>", mapSql);
        rootElement.addElement(createSql("sql_where", whereSQL));
        rootElement.addElement(createSql("sql_map_where", whereMapSQL));

        rootElement.addElement(createSelect("selectById", tableName, pkColumn));
        rootElement.addElement(createSelect("selectCacheById", tableName, pkColumn));
        rootElement.addElement(createSelect("selectLockById", tableName, pkColumn));
        rootElement.addElement(createSelect("selectOne", tableName, null));
        rootElement.addElement(createSelect("findList", tableName, null));
        rootElement.addElement(createSelect("selectMap", tableName, null));
        rootElement.addElement(createCount("count", tableName, null));


        rootElement.addElement(createSql("sql_insert_columns", saveColumn.append("\t</trim>").toString()));
        rootElement.addElement(createSql("sql_insert_values", saveValue.append("\t</trim>").toString()));
        rootElement.addElement(createSave("insert", pkColumn));
        rootElement.addElement(createSave("batchInsert", null));

        updateSQL.append("\twhere ").append(pkColumn.getActualColumnName()).append(" = #{item.").append(pkColumn.getJavaProperty()).append("}");
        updateAllSQL.append("\twhere ").append(pkColumn.getActualColumnName()).append(" = #{item.").append(pkColumn.getJavaProperty()).append("}");
        rootElement.addElement(createSql("sql_update", updateSQL.toString()));
        rootElement.addElement(createSql("sql_update_all", updateAllSQL.toString()));

        rootElement.addElement(createUpdate("update"));
        rootElement.addElement(createUpdate("batchUpdate"));
        rootElement.addElement(createUpdate("updateAll"));
        rootElement.addElement(createUpdate("updateCompare"));

        rootElement.addElement(createDels(tableName, pkColumn, "delArray", "array"));
        rootElement.addElement(createDels(tableName, pkColumn, "delList", "list"));
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }


    public boolean isNumber(IntrospectedColumn introspectedColumn) {
        return introspectedColumn.getJdbcType() == Types.INTEGER || introspectedColumn.getJdbcType() == Types.NUMERIC
                || introspectedColumn.getJdbcType() == Types.BIGINT || introspectedColumn.getJdbcType() == Types.BIT
                || introspectedColumn.getJdbcType() == Types.TINYINT;
    }


    /**
     * 公共SQL
     *
     * @param id
     * @param sqlStr
     * @return
     */
    private XmlElement createSql(String id, String sqlStr) {
        XmlElement sql = new XmlElement("sql");
        sql.addAttribute(new Attribute("id", id));
        sql.addElement(new TextElement(sqlStr));
        return sql;
    }

    /**
     * 查询
     *
     * @param id
     * @param tableName
     * @param pkColumn
     * @return
     */
    private XmlElement createSelect(String id, String tableName, IntrospectedColumn pkColumn) {
        XmlElement select = new XmlElement("select");
        select.addAttribute(new Attribute("id", id));
        select.addAttribute(new Attribute("resultMap", "BaseResultMap"));

        StringBuilder selectStr = new StringBuilder("select <include refid=\"sql_columns\" /> from ").append(tableName);
        if (null != pkColumn) {
            selectStr.append(" where ").append(pkColumn.getActualColumnName()).append(" = #{").append(pkColumn.getJavaProperty()).append("}");
        } else {
            if (!"selectMap".equals(id)) {
                selectStr.append(" <include refid=\"sql_where\" />");
            } else {
                selectStr.append(" <include refid=\"sql_map_where\" />");
            }
        }
        if ("selectPage".equals(id)) {
            selectStr.append(" limit #{page.startRow}, #{page.pageSize}");
        }
        if ("selectLockById".equals(id)) {
            selectStr.append(" for update");
        }
        select.addElement(new TextElement(selectStr.toString()));
        return select;
    }

    /**
     * 查询个数
     *
     * @param id
     * @param tableName
     * @param pkColumn
     * @return
     */
    private XmlElement createCount(String id, String tableName, IntrospectedColumn pkColumn) {
        XmlElement select = new XmlElement("select");
        select.addAttribute(new Attribute("id", id));
        select.addAttribute(new Attribute("resultType", "java.lang.Integer"));

        StringBuilder selectStr = new StringBuilder("select count(1) from ").append(tableName);
        if (null != pkColumn) {
            selectStr.append(" where ").append(pkColumn.getActualColumnName()).append(" = #{").append(pkColumn.getJavaProperty()).append("}");
        } else {
            selectStr.append(" <include refid=\"sql_where\" />");
        }
        select.addElement(new TextElement(selectStr.toString()));
        return select;
    }

    /**
     * 保存
     *
     * @param id
     * @param pkColumn
     * @return
     */
    private XmlElement createSave(String id, IntrospectedColumn pkColumn) {
        XmlElement save = new XmlElement("insert");
        save.addAttribute(new Attribute("id", id));
        if (null != pkColumn) {
            save.addAttribute(new Attribute("keyProperty", "item." + pkColumn.getJavaProperty()));
            save.addAttribute(new Attribute("useGeneratedKeys", "true"));
            save.addElement(new TextElement("<include refid=\"sql_insert_columns\" /><include " +
                    "refid=\"sql_insert_values\" />"));
        } else {
            StringBuilder saveStr = new StringBuilder(
                    "<foreach collection=\"list\" index=\"index\" item=\"item\" open=\"\" separator=\";\" " +
                            "close=\"\">\n\t  ")
                    .append("<include refid=\"sql_insert_columns\" /><include refid=\"sql_insert_values\" " +
                            "/>\n\t</foreach>");
            save.addElement(new TextElement(saveStr.toString()));
        }
        return save;
    }

    /**
     * 更新
     *
     * @param id
     * @return
     */
    private XmlElement createUpdate(String id) {
        XmlElement update = new XmlElement("update");
        update.addAttribute(new Attribute("id", id));
        if ("update".equals(id)) {
            update.addElement(new TextElement("<include refid=\"sql_update\" />"));
        } else if ("batchUpdate".equals(id)) {
            update.addElement(new TextElement(
                    "<foreach collection=\"list\" index=\"index\" item=\"item\" open=\"\" separator=\";\" " +
                            "close=\"\">\n\t  <include refid=\"sql_update\" />\n\t</foreach>"));
        } else if ("updateAll".equals(id)) {
            update.addElement(new TextElement("<include refid=\"sql_update_all\" />"));
        }
        return update;
    }

    /**
     * 删除
     *
     * @param tableName
     * @param pkColumn
     * @param method
     * @param type
     * @return
     */
    private XmlElement createDels(String tableName, IntrospectedColumn pkColumn, String method, String type) {
        XmlElement delete = new XmlElement("delete");
        delete.addAttribute(new Attribute("id", method));
        StringBuilder deleteStr =
                new StringBuilder("delete from ").append(tableName).append(" where ").append(pkColumn.getActualColumnName())
                .append(" in\n\t")
                .append("<foreach collection=\"").append(type)
                .append("\" index=\"index\" item=\"item\" open=\"(\" separator=\",\" close=\")\">#{item}</foreach>");
        delete.addElement(new TextElement(deleteStr.toString()));
        return delete;
    }


    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        // 数据库表名
        String tableName = introspectedTable.getFullyQualifiedTableNameAtRuntime();


        topLevelClass.addImportedType("lombok.Getter");
        topLevelClass.addImportedType("lombok.Setter");
        topLevelClass.addImportedType("lombok.ToString");
        //添加domain的注解
        topLevelClass.addAnnotation("@Getter");
        topLevelClass.addAnnotation("@Setter");
        topLevelClass.addAnnotation("@ToString");

        if ("BaseEntity".equals(topLevelClass.getSuperClass().getShortName())) {
            topLevelClass.addImportedType("com.cms.scaffold.common.annotation.TableName");
            //添加domain的注解
            topLevelClass.addAnnotation("@TableName(name = \"" + tableName + "\")");
        }


        //添加domain的注释
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine("* " + introspectedTable.getRemarks());
        topLevelClass.addJavaDocLine("* @author: Mybatis Generator");
        topLevelClass.addJavaDocLine("* @date: " + DateUtil.now());
        topLevelClass.addJavaDocLine("*/");

        return true;

    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
                                              IntrospectedTable introspectedTable) {
        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelFieldGenerated(Field field,
                                       TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                       IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        if ("id".equals(field.getName()) || "addTime".equals(field.getName()) || "updateTime".equals(field.getName())) {
            return false;
        }
        return super.modelFieldGenerated(field, topLevelClass, introspectedColumn,
                introspectedTable, modelClassType);
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method,
                                              TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                              IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method,
                                              TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                              IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return super.modelRecordWithBLOBsClassGenerated(topLevelClass,
                introspectedTable);
    }

    /**
     * 以下设置为false,取消生成默认增删查改xml
     *
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @return boolean
     */
    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze,
                                                           IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientInsertMethodGenerated(Method method, Interface interfaze,
                                               IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientSelectAllMethodGenerated(Method method, Interface interfaze,
                                                  IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze,
                                                           IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, Interface interfaze,
                                                                       IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapSelectAllElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element,
                                                                        IntrospectedTable introspectedTable) {
        return false;
    }

}
