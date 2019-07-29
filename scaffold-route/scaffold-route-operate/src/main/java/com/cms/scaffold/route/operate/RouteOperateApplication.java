package com.cms.scaffold.route.operate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author zhangjiaheng
 */
@SpringBootApplication(scanBasePackages = {"com.cms.scaffold.route.operate"}, exclude =
        {DataSourceAutoConfiguration.class})
@EnableAsync
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@EnableAspectJAutoProxy
public class RouteOperateApplication {
    public static void main(String[] args) {
        SpringApplication.run(RouteOperateApplication.class, args);
    }


}
