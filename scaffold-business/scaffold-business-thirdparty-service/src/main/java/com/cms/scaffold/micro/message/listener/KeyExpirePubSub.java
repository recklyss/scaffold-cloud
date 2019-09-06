package com.cms.scaffold.micro.message.listener;

import cn.hutool.core.util.StrUtil;
import com.cms.scaffold.code.mq.model.MqRedisModel;
import com.cms.scaffold.code.util.JedisUtil;
import com.cms.scaffold.common.builder.Builder;
import com.cms.scaffold.common.constant.CacheConstant;
import com.cms.scaffold.micro.message.domain.Message;
import com.cms.scaffold.micro.message.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPubSub;

/**
 * redis Key失效事件通知 发布订阅
 *
 * @author zhang
 */
@Slf4j
@Component
@Scope("prototype")
public class KeyExpirePubSub extends JedisPubSub {

    public static final int MQ_FAIL = 2;
    @Autowired
    MessageService messageService;

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        try {
            log.info("监听到redis通道[{}]的消息：【{}】,pattern = [{}]", channel, message, pattern);
            if (StrUtil.isNotBlank(message)) {
                if (message.contains(CacheConstant.MQ_REDISMODEL_EXPIRE_KEY_INDEX)) {
                    String key = message.replaceAll(CacheConstant.MQ_REDISMODEL_EXPIRE_KEY_INDEX, "");
                    mqRedisModelExpireKeyExpire(key);
                } else if (message.contains(CacheConstant.MQ_REDISMODEL_KEY_INDEX)) {
                    String key = message.replaceAll(CacheConstant.MQ_REDISMODEL_KEY_INDEX, "");
                    mqRedisModelKeyExpire(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * mqRedisModelExpireKey的过期事件
     *
     * @param key mq:redisModel:key:expire:
     */
    public void mqRedisModelExpireKeyExpire(String key) {
        if (StrUtil.isBlank(key)) {
            return;
        }
        MqRedisModel redisModel = ((MqRedisModel) JedisUtil.getObject(String.format(CacheConstant.MQ_REDISMODEL_KEY, key)));
        if (redisModel == null) {
            log.error("从redis中取出的mqRedisModel为空, key = [{}]", key);
            return;
        }
        Message message = messageService.selectByKey(key);
        if (message == null) {
            message = Builder.build(redisModel, Message.class);
            messageService.insert(message);
        } else {
            Long status = redisModel.getStatus();
            message.setStatus(status);
            if (status == MQ_FAIL) {
                message.setTriggerNum(message.getTriggerNum() + 1);
            }
            messageService.update(message);
        }
        JedisUtil.del(String.format(CacheConstant.MQ_REDISMODEL_KEY, key));
    }

    public void mqRedisModelKeyExpire(String key) {
        if (StrUtil.isNotBlank(key)) {
            Message message = messageService.selectByKey(key);
            if (message != null && message.getStatus().equals(0L)) {
                message.setStatus(3L);
                messageService.update(message);
            }
        }
    }


}
