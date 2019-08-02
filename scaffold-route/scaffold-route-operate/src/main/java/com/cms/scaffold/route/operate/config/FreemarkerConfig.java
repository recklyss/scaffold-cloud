package com.cms.scaffold.route.operate.config;


import com.cms.scaffold.route.operate.freemarker.TableThDirective;
import com.jagregory.shiro.freemarker.ShiroTags;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 *
 * @author zjh
 * @date 2018/4/16
 */
@Configuration
public class FreemarkerConfig {

    @Resource
    private freemarker.template.Configuration configuration;
    @Resource
    private TableThDirective tableThDirective;

    @PostConstruct
    public void setSharedVariable(){
        configuration.setSharedVariable("th",tableThDirective);
        configuration.setSharedVariable("shiro",new ShiroTags());
    }

}
