/**  
 * @Title: CustomSqlSessionFactoryBean.java
 * @Package com.cms.scaffold.mybatis
 * TODO:TODO
 * @author zjh
 * @date 2017-6-22
 */
package com.cms.scaffold.plugin.mybatis;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO:Mybatis 自定义SqlSessionFactoryBean扫描通配符
 * @author zjh
 * @date 2017-6-22
 */
public class CustomSqlSessionFactoryBean extends SqlSessionFactoryBean{

	
	static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * 重写setTypeAliasesPackage方法
	 */
	@Override
	public void setTypeAliasesPackage(String typeAliasesPackage) {
		ResourcePatternResolver resolver = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();
		MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(
				resolver);
		String[] typeAliasesPackages = typeAliasesPackage.split(";");
		
		typeAliasesPackage = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
				+ ClassUtils.convertClassNameToResourcePath(typeAliasesPackage)
				+ "/" + DEFAULT_RESOURCE_PATTERN;

		// 将加载多个绝对匹配的所有Resource
		// 将首先通过ClassLoader.getResource("META-INF")加载非模式路径部分
		// 然后进行遍历模式匹配
		try {
			Set<String> result = new HashSet<String>();
			Resource[] resources = null;
			for(int i  = 0; i<typeAliasesPackages.length; i++){
				typeAliasesPackages[i] = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
						+ ClassUtils.convertClassNameToResourcePath(typeAliasesPackages[i])
						+ "/" + DEFAULT_RESOURCE_PATTERN;
				//数组累加
				resources = (Resource[]) ArrayUtils.addAll(resources, resolver.getResources(typeAliasesPackages[i]));
				
			}
			
			if (resources != null && resources.length > 0) {
				MetadataReader metadataReader = null;
				for (Resource resource : resources) {
					if (resource.isReadable()) {
						metadataReader = metadataReaderFactory
								.getMetadataReader(resource);
						try {
							result.add(Class
									.forName(
											metadataReader.getClassMetadata()
													.getClassName())
									.getPackage().getName());
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				}
			}
			if (result.size() > 0) {
				super.setTypeAliasesPackage(StringUtils.join(result.toArray(),
						","));
			} else {
				logger.warn("参数typeAliasesPackage:" + typeAliasesPackage
						+ "，未找到任何包");
			}
			// logger.info("d");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
