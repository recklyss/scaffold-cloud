package com.cms.scaffold.route.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author zhangjiaheng
 */
@SpringBootApplication(scanBasePackages = {"com.cms.scaffold.route.app"})
@EnableAspectJAutoProxy
@EnableAsync
@EnableEurekaClient
@EnableFeignClients
public class AppOperateApplication {
    public static void main(String[] args) {
        System.setProperty("projectName", "scaffold-route-operate");
        SpringApplication.run(AppOperateApplication.class, args);
    }


}
