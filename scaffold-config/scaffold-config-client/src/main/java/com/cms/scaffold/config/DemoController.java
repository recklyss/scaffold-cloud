package com.cms.scaffold.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiaheng.zhang
 */
@RestController
@RefreshScope
public class DemoController {

    @Value("${porfile}")
    private String porfile;

    @GetMapping("/hello")
    public String hello(){
        return porfile;
    }
}
