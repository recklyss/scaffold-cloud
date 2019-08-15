package com.cms.scaffold.micro.consumer;

import com.cms.scaffold.micro.consumer.listener.SysServiceListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author zhangjiaheng
 * @Description 注册
 **/
@Component
@Slf4j
public class RegisterConsumers implements ApplicationListener<ContextRefreshedEvent> {
    @Resource
    private SysServiceListener sysServiceListener;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent c) {
        log.info("开始初始化sys-service MQ消费者监听器" );

        log.info("初始化sys-service MQ消费者监听器完成" );
    }
}
