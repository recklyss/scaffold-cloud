package com.cms.scaffold.micro.message.listener;

import cn.hutool.core.util.StrUtil;
import com.cms.scaffold.common.constant.CacheConstant;
import redis.clients.jedis.JedisPubSub;

/**
 * redis Key失效事件通知 发布订阅
 *
 * @author zhang
 */
public class KeyExpirePubSub extends JedisPubSub {

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        try {
            if (StrUtil.isNotBlank(message)) {
                if(message.contains(CacheConstant.MQ_REDISMODEL_EXPIRE_KEY_INDEX)){

                }else if (message.contains(CacheConstant.MQ_REDISMODEL_KEY_INDEX)){

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
