package com.cms.scaffold.code.util;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendResult;
import com.cms.scaffold.code.mq.core.AliMqProducer;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author zhangjiaheng
 * @Description 中间件工具类 具体的消息发送类
 **/
@Slf4j
public class RocketCoreUtil {

    /**
     * 消息发送 : 发送异常时返回空字符串
     *
     * @param message
     * @return
     */
    public static String sendRocketMsg(Message message) {
        SendResult sendResult;
        try {
            Producer producer = AliMqProducer.getProducer();
            sendResult = producer.send(message);
        } catch (Exception e) {
            log.error("消息发送失败:{}", e.getLocalizedMessage(), e);
            return "";
        }
        log.info("消息发送....messageId:{},messageKey:{}", sendResult.getMessageId(), message.getKey());

        return sendResult.getMessageId();
    }

    /**
     * 消息发送 : 发送异常时返回空字符串
     *
     * @param topic 主题
     * @param tags  标签
     * @param key   消息键值
     * @param param 参数字符串
     * @return 消息ID
     */
    public static String sendRocketMsg(String topic, String tags, String key, String param) {
        byte[] body = param.getBytes();
        Message message = new Message(topic, tags, body);
        message.setKey(key);
        SendResult sendResult;
        try {
            Producer producer = AliMqProducer.getProducer();
            sendResult = producer.send(message);
        } catch (Exception e) {
            log.info("消息失败", e);
            return "";
        }
        log.info("消息发送....messageId:{},messageKey:{}", sendResult.getMessageId(), key);
        return sendResult.getMessageId();
    }
}
