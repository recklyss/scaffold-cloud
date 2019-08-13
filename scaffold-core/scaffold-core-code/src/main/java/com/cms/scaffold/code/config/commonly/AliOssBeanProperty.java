package com.cms.scaffold.code.config.commonly;

import com.aliyun.oss.OSSClient;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author zhangjiaheng
 * @Description
 **/
@Component
@PropertySource(ignoreResourceNotFound = true, value = "classpath:code.properties")
@Getter
@Setter
public class AliOssBeanProperty {
    private static final Logger logger = LoggerFactory.getLogger(AliOssBeanProperty.class);
    /**
     * 阿里云API的内或外网域名
     */
    @Value("${spring.aliOSS.endpoint}")
    private String endpoint;
    /**
     * 阿里云API的密钥Access Key ID
     */
    @Value("${spring.aliOSS.accessKeyId}")
    private String accessKeyId;
    /**
     * 阿里云API的密钥Access Key Secret
     */
    @Value("${spring.aliOSS.accessKeySecret}")
    private String accessKeySecret;
    /**
     * 阿里云API的bucket名称
     */
    @Value("${spring.aliOSS.bucketName}")
    private String bucketName;
    /**
     * 系统环境
     */
    @Value("${spring.aliOSS.env}")
    private String env;

    @Bean
    public OSSClient createOSSClient() {
        logger.info("创建OSSClient！！");
        return new OSSClient(endpoint,accessKeyId, accessKeySecret);
    }
}
