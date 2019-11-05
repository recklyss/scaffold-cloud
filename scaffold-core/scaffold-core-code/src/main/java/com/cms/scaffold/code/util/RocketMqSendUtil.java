package com.cms.scaffold.code.util;

import cn.hutool.core.util.StrUtil;
import com.cms.scaffold.code.mq.core.MqNoTransactionSynchronizationAdapter;
import com.cms.scaffold.code.mq.core.MqTransactionSynchronizationAdapter;
import com.cms.scaffold.code.mq.model.MqBaseModel;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

/**
 * @description:
 * @author: jiaheng
 * @date: 2018/10/24 17:54
 */
public class RocketMqSendUtil {

    public static void sendMq(List<MqBaseModel> mqBaseModels){
        // 判断是否存在事务，为空则说明不存在
        String currentTransactionName  = TransactionSynchronizationManager.getCurrentTransactionName();
        boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        if (StrUtil.isBlank(currentTransactionName) || readOnly){
            MqNoTransactionSynchronizationAdapter.commit(mqBaseModels);
        } else {
            TransactionSynchronizationManager.registerSynchronization(new MqTransactionSynchronizationAdapter(mqBaseModels));
        }
    }
}
