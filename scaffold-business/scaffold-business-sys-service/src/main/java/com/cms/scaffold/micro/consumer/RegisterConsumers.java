package com.cms.scaffold.micro.consumer;

import com.cms.scaffold.code.mq.core.AliMqConsumer;
import com.cms.scaffold.common.constant.MqConstant;
import com.cms.scaffold.micro.consumer.listener.SysOperateLogListener;
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
    private SysOperateLogListener sysOperateLogListener;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent c) {
        log.info("开始初始化sys-service MQ消费者监听器" );
        AliMqConsumer.getConsumer(MqConstant.ROCKETMQ_TAG_OPERATE_LOG, sysOperateLogListener);
        log.info("初始化sys-service MQ消费者监听器完成" );
    }
}
