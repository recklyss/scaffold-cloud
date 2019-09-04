package com.cms.scaffold.micro.message.listener;

import com.cms.scaffold.micro.message.service.MessageService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * 注册redis锁过期的监听器
 *
 * @author zhang
 */
public class KeyExpireListener implements InitializingBean {

    @Resource
    JedisPool jedisPool;
    @Resource
    MessageService messageService;
    @Autowired
    ThreadPoolTaskExecutor taskExecutor;

    @Override
    public void afterPropertiesSet() throws Exception {

    }


}
