package com.cms.scaffold.code.mq.listener;

import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.cms.scaffold.code.config.commonly.SpringContextHolder;
import com.cms.scaffold.code.mq.base.MqBaseInterface;
import com.cms.scaffold.code.mq.builder.MqModelFactoryBuilder;
import com.cms.scaffold.code.mq.model.MqBaseModel;
import com.cms.scaffold.code.mq.model.MqRedisModel;
import com.cms.scaffold.code.util.JedisUtil;
import com.cms.scaffold.common.constant.CacheConstant;
import com.cms.scaffold.common.constant.MqConstant;
import com.cms.scaffold.common.dict.ExpireTime;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author zhangjiaheng
 * @Description 基类消息监听
 **/
@Slf4j
public class BaseMqMessageListener implements MessageListener {
    @Override
    public Action consume(Message message, ConsumeContext context) {
        String modelClassName = message.getUserProperties(MqConstant.MODEL_CLASS_NAME);
        String body = new String(message.getBody());
        log.info("开始消费tag:{}, key:{}, msgId:{}, body:{}", message.getTag(), message.getKey(), message.getMsgID(), body);
        MqBaseModel model = JSONObject.parseObject(body, MqBaseModel.class);
        final String mqConsumerKey = String.format(CacheConstant.MQ_CONSUMER_KEY, model.getKey());
        if (JedisUtil.incr(mqConsumerKey, 1L) > 1L) {
            log.info("消息【{}】在消费", model.getKey());
            JedisUtil.decr(mqConsumerKey, 1L);
            // 消费成功，继续消费下一条消息
            return Action.CommitMessage;
        }
        JedisUtil.expire(mqConsumerKey, ExpireTime.ONE_HOUR.getTime());
        // mq状态
        long status = 0L;
        MqBaseInterface baseInterface = null;
        MqRedisModel redisModel = null;
        try {
            // baseInterface = (MqBaseInterface) JSONObject.parseObject(body,Class.forName(modelClassName));
            // 以上代码 拿到的反序列化后的对象 objs[]为空 导致执行方法的时候 如果没有该方法的无参重载 则报错找不到方法
            baseInterface = (MqBaseInterface) JSONObject.parseObject(body,Class.forName(modelClassName));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("构造MQ接口异常>>> {}", model.getOperate());
            status = 2;
        }

        try {
            if (null != baseInterface) {
                final Object bean = SpringContextHolder.getBean(baseInterface.getSpringBean());
                ReflectUtil.invoke(bean, baseInterface.getMethodName(), baseInterface.getObjs());
                status = 1L;
            }
        } catch (Exception e) {
            log.error("消费异常：【{}】", e.getLocalizedMessage());
            log.error("消费异常tag:{}, key:{}, message:{}", message.getTag(), message.getKey(), e.getMessage(), e);
            if (Action.ReconsumeLater.equals(model.getAction())) {
                JedisUtil.decr(mqConsumerKey, 1L);
                JedisUtil.expire(mqConsumerKey, ExpireTime.ONE_HOUR.getTime());
                return Action.ReconsumeLater;
            }
            status = 2L;
            return Action.CommitMessage;
        } finally {
            redisModel = MqModelFactoryBuilder.buildMqRedisModel(baseInterface != null ?
                    (MqBaseModel) baseInterface : null, status, message);
            JedisUtil.setObject(String.format(CacheConstant.MQ_REDISMODEL_KEY, model.getKey()), redisModel,
                    ExpireTime.ONE_DAY.getTime());
            JedisUtil.setnx(String.format(CacheConstant.MQ_REDISMODEL_KEY_EXPIRE, model.getKey()), CacheConstant.YES,
                    ExpireTime.HALF_A_MIN.getTime());
        }
        return Action.CommitMessage;
    }
}
