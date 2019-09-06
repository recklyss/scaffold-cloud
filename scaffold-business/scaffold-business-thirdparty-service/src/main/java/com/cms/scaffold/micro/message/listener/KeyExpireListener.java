package com.cms.scaffold.micro.message.listener;

import com.cms.scaffold.micro.message.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * 注册redis锁过期的监听器
 *
 * @author zhang
 */
@Slf4j
@Component
public class KeyExpireListener implements InitializingBean {

    private static final String KEY_EXPIRED_CHANNEL = "__key*__:expired";
    @Resource
    JedisPool jedisPool;
    @Resource
    MessageService messageService;
    @Resource
    ThreadPoolTaskExecutor taskExecutor;
    @Resource
    KeyExpirePubSub keyExpirePubSub;

    @Override
    public void afterPropertiesSet() {
        taskExecutor.execute(() -> {
            try {
                log.info("开始监听redis KEY过期事件");
                Jedis jedis = jedisPool.getResource();
                jedis.psubscribe(keyExpirePubSub, KEY_EXPIRED_CHANNEL);
            } catch (Exception e) {
                log.error("监听redis KEY过期事件出现错误" );
                e.printStackTrace();
            }
        });

    }


}
