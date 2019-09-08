package com.cms.scaffold.micro;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author zhangjiaheng
 * @Description 第三方任务模块
 **/
@SpringBootApplication(scanBasePackages = {"com.cms.scaffold"}, exclude = FlywayAutoConfiguration.class)
@EnableDiscoveryClient
@MapperScan(basePackages = "com.cms.scaffold.micro.**.dao")
@EnableDistributedTransaction
public class ThirdPartyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThirdPartyApplication.class, args);
    }
}
