package com.cms.scaffold.micro;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author zhangjiaheng
 * @Description 定时任务模块
 **/
@SpringBootApplication(scanBasePackages = {"com.cms.scaffold.micro"})
@EnableDiscoveryClient
@MapperScan(basePackages = "com.cms.scaffold.micro.**.dao")
public class JobApplication {
    public static void main(String[] args) {
        System.setProperty("projectName", "scaffold-job-service");
        SpringApplication.run(JobApplication.class, args);
    }
}
