package com.cms.scaffold.txlcn;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhang
 */
@SpringBootApplication
@EnableTransactionManagerServer
@Slf4j
public class TxLcnApplication {

    public static void main(String[] args){
        // 测试提交
        SpringApplication.run(TxLcnApplication.class, args);
    }
}
