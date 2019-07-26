package com.cms.scaffold.route.operate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author zhangjiaheng
 */
@SpringBootApplication(scanBasePackages = {"com.cms.scaffold.route.operate"})
@EnableAspectJAutoProxy
@EnableAsync
public class RouteOperateApplication {
    public static void main(String[] args) {
        System.setProperty("projectName", "scaffold-route-operate");
        SpringApplication.run(RouteOperateApplication.class, args);
    }


}
