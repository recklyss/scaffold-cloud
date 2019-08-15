package com.cms.scaffold.code.mq.core;

import cn.hutool.core.util.StrUtil;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import com.aliyun.openservices.ons.api.transaction.TransactionProducer;
import com.cms.scaffold.code.config.commonly.AliOnsMqConfig;
import com.cms.scaffold.code.config.commonly.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * 消息中间件:创建生产者类
 *
 * @author Administrator
 */
public class AliMqProducer {

    private static Logger logger = LoggerFactory.getLogger(AliMqProducer.class);
    /**
     * 无序Producer
     */
    private static Producer producer;
    /**
     * 顺序Producer
     */
    private static OrderProducer orderProducer;
    /**
     * 事务Producer
     */
    private static TransactionProducer transactionProducer;


    public static Producer getProducer() {
        if (producer == null) {
            synchronized (AliMqProducer.class) {
                if (producer == null) {
                    producer = getEvnProducer();
                    //在发送消息前，必须调用start方法来启动Producer，只需调用一次即可。
                    producer.start();
                    Runtime.getRuntime().addShutdownHook(new Thread() {
                        @Override
                        public void run() {
                            producer.shutdown();
                        }
                    });
                    return producer;
                }
            }
        }
        return producer;
    }


    /**
     * 创建生产者
     *
     * @return
     */
    private static Producer getEvnProducer() {
        AliOnsMqConfig aliOnsMqConfig = SpringContextHolder.getBean(AliOnsMqConfig.class);
        logger.info("----生成Producer---- config:{}", aliOnsMqConfig);
        Properties properties = new Properties();
        setProperties(aliOnsMqConfig, properties);
        producer = ONSFactory.createProducer(properties);
        return producer;
    }


    /**
     * 创建顺序生产者
     *
     * @return
     */
    private static OrderProducer getEvnOrderProducer() {
        AliOnsMqConfig aliOnsMqConfig = SpringContextHolder.getBean(AliOnsMqConfig.class);
        logger.info("----生成Producer---- config:{}", aliOnsMqConfig);
        Properties properties = new Properties();
        setProperties(aliOnsMqConfig, properties);
        orderProducer = ONSFactory.createOrderProducer(properties);
        return orderProducer;
    }

    private static void setProperties(AliOnsMqConfig aliOnsMqConfig, Properties properties) {
        if (StrUtil.isNotBlank(aliOnsMqConfig.getRocketMqProducerId())) {
            properties.put(PropertyKeyConst.ProducerId, aliOnsMqConfig.getRocketMqProducerId());
        }
        if (StrUtil.isNotBlank(aliOnsMqConfig.getRocketMqAccessKey())) {
            properties.put(PropertyKeyConst.AccessKey, aliOnsMqConfig.getRocketMqAccessKey());
        }
        if (StrUtil.isNotBlank(aliOnsMqConfig.getRocketMqSecretKey())) {
            properties.put(PropertyKeyConst.SecretKey, aliOnsMqConfig.getRocketMqSecretKey());
        }
        if (StrUtil.isNotBlank(aliOnsMqConfig.getRocketMqOnsAddr())) {
            properties.put(PropertyKeyConst.ONSAddr, aliOnsMqConfig.getRocketMqOnsAddr());
        }
        if (StrUtil.isNotBlank(aliOnsMqConfig.getRocketMqNameSrvAddr())) {
            properties.put(PropertyKeyConst.NAMESRV_ADDR, aliOnsMqConfig.getRocketMqNameSrvAddr());
        }
    }
}
