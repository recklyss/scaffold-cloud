package com.cms.scaffold.code.mq.core;

import cn.hutool.core.util.StrUtil;
import com.aliyun.openservices.ons.api.Message;
import com.cms.scaffold.code.mq.builder.MqModelFactoryBuilder;
import com.cms.scaffold.code.mq.model.MqBaseModel;
import com.cms.scaffold.code.mq.model.MqRedisModel;
import com.cms.scaffold.code.util.JedisUtil;
import com.cms.scaffold.code.util.RocketCoreUtil;
import com.cms.scaffold.common.constant.CacheConstant;
import com.cms.scaffold.common.dict.ExpireTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;

import java.util.List;

/**
 * 在事务提交之后发送MQ
 *
 * @author zjh
 */
@Slf4j
public class MqTransactionSynchronizationAdapter extends TransactionSynchronizationAdapter {

    private List<MqBaseModel> mqBaseModels;


    public MqTransactionSynchronizationAdapter(List<MqBaseModel> mqBaseModels) {
        this.mqBaseModels = mqBaseModels;
    }

    @Override
    public void afterCommit() {
        log.info("事务提交后发送消息");
        if (mqBaseModels == null || mqBaseModels.isEmpty()) {
            log.warn("传入消息model为空");
            return;
        }
        for (MqBaseModel mqBaseModel : mqBaseModels) {
            //参数校验
            if (null == mqBaseModel.getMessage()) {
                log.info("mqBaseModel:{},message为空", mqBaseModel);
                continue;
            }
            if (StrUtil.isBlank(mqBaseModel.getKey())) {
                log.info("mqBaseModel:{},key为空", mqBaseModel);
                continue;
            }
            if (StrUtil.isBlank(mqBaseModel.getRequestNo())) {
                log.info("mqBaseModel:{},requestNo为空", mqBaseModel);
                continue;
            }
            //发送MQ消息
            String messageId = RocketCoreUtil.sendRocketMsg(mqBaseModel.getMessage());
            long status = StrUtil.isNotBlank(messageId) ? 0 : -1;
            //构造中间消息内容
            Message message = new Message();
            message.setMsgID(messageId);
            message.setKey(mqBaseModel.getKey());
            MqRedisModel mqRedisModel = MqModelFactoryBuilder.buildMqRedisModel(mqBaseModel, status, message);
            //存储redis对象
            JedisUtil.setObject(String.format(CacheConstant.MQ_REDISMODEL_KEY, mqBaseModel.getKey()), mqRedisModel,
                    ExpireTime.ONE_DAY.getTime());
            JedisUtil.setnx(String.format(CacheConstant.MQ_REDISMODEL_KEY_EXPIRE, mqBaseModel.getKey()),
                    CacheConstant.YES, ExpireTime.ONE_HOUR.getTime());
        }


    }
}
