package com.cms.scaffold.txlcn;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhang
 */
@SpringBootApplication
@EnableTransactionManagerServer
public class TxLcnApplication {

    public static void main(String[] args){
        SpringApplication.run(TxLcnApplication.class, args);
    }
}
