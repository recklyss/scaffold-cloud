package com.cms.scaffold.route.operate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author zhangjiaheng
 */
@SpringBootApplication(scanBasePackages = {"com.cms.scaffold.route.operate"})
@EnableAspectJAutoProxy
@EnableAsync
@EnableEurekaClient
public class RouteOperateApplication {
    public static void main(String[] args) {
        System.setProperty("projectName", "scaffold-route-operate");
        SpringApplication.run(RouteOperateApplication.class, args);
    }


}
