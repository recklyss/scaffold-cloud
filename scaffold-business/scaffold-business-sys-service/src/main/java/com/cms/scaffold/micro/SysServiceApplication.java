package com.cms.scaffold.micro;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhang
 */
@SpringBootApplication(scanBasePackages = {"com.cms.scaffold"})
@EnableDiscoveryClient
@MapperScan(basePackages = "com.cms.scaffold.micro.**.dao")
@EnableDistributedTransaction
public class SysServiceApplication {
    public static void main(String[] args) {
        System.setProperty("projectName", "scaffold-sys-service");
        SpringApplication.run(SysServiceApplication.class, args);
    }
}
