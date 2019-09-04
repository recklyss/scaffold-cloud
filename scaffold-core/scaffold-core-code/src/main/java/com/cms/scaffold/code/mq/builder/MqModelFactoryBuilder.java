package com.cms.scaffold.code.mq.builder;

import com.aliyun.openservices.ons.api.Message;
import com.cms.scaffold.code.mq.model.MqBaseModel;
import com.cms.scaffold.code.mq.model.MqRedisModel;

/**
 * @description: MQ模块工厂构造类
 * @author: zjh
 **/
public class MqModelFactoryBuilder {


    /**
     * 构建buildMqRedisModel类
     *
     * @param mqBaseModel
     * @param status
     * @param message
     * @return
     */
    public static MqRedisModel buildMqRedisModel(MqBaseModel mqBaseModel, Long status, Message message) {
        MqRedisModel mqRedisModel = new MqRedisModel();

        if (mqBaseModel != null) {
            mqRedisModel.setMessageKey(mqBaseModel.getKey());
            mqRedisModel.setOperate(mqBaseModel.getOperate());
            mqRedisModel.setRequestNo(mqBaseModel.getRequestNo());
            mqRedisModel.setTags(mqBaseModel.getTag());
            mqRedisModel.setRequestParam(new String(mqBaseModel.getMessage().getBody()));
        } else {
            mqRedisModel.setMessageKey(message.getKey());
            mqRedisModel.setTags(message.getTag());
        }

        mqRedisModel.setStatus(status);
        mqRedisModel.setMessageId(message.getMsgID());


        return mqRedisModel;
    }

}
