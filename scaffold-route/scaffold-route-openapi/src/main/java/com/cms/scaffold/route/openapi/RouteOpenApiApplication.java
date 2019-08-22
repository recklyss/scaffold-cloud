package com.cms.scaffold.route.openapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author zhangjiaheng
 */
@SpringBootApplication(scanBasePackages = {
        "com.cms.scaffold.route.openapi",
        "com.cms.scaffold.code.config.commonly"
}, exclude = {DataSourceAutoConfiguration.class})
@EnableAsync
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.cms.scaffold.feign.*")
@EnableHystrix
@EnableAspectJAutoProxy
public class RouteOpenApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(RouteOpenApiApplication.class, args);
    }
}
