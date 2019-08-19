package com.cms.scaffold.route.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author zhangjiaheng
 */
@SpringBootApplication(scanBasePackages = {"com.cms.scaffold.route.app"})
@EnableAspectJAutoProxy
@EnableAsync
@EnableDiscoveryClient
@EnableFeignClients
public class AppOperateApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppOperateApplication.class, args);
    }


}
