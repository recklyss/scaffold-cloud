package com.cms.scaffold.code.mq.core;

import cn.hutool.core.util.StrUtil;
import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.order.MessageOrderListener;
import com.aliyun.openservices.ons.api.order.OrderConsumer;
import com.cms.scaffold.code.config.commonly.AliOnsMqConfig;
import com.cms.scaffold.code.config.commonly.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


/**
 * @description: 消息中间件:创建消费者类
 * @author: jiaheng
 * @date: 2018-09-01 22:05
 **/
public class AliMqConsumer {


    private static Logger logger = LoggerFactory.getLogger(AliMqConsumer.class);

    public static Consumer getConsumer(String tags, MessageListener listener) {
        Consumer consumer = null;
        //选择开发环境
        consumer = getEvnConsumer(tags, listener);
        //在发送消息前，必须调用start方法来启动Producer，只需调用一次即可。
        consumer.start();
        Runtime.getRuntime().addShutdownHook(new ShutdownHook(consumer));
        logger.info("----consumer start----");
        return consumer;

    }

    /**
     * 创建Consumer
     *
     * @param tags
     * @param listener
     * @return
     */
    private static Consumer getEvnConsumer(String tags, MessageListener listener) {
        AliOnsMqConfig aliOnsMqConfig = SpringContextHolder.getBean(AliOnsMqConfig.class);
        String topic = aliOnsMqConfig.getRocketMqTopic();
        logger.info("topic:{}", topic);
        Properties properties = new Properties();
        setProperties(aliOnsMqConfig, properties, tags);
        logger.info("----生成Consumer----ConsumerId:{}, config:{}", properties.getProperty(PropertyKeyConst.ConsumerId)
                , aliOnsMqConfig);
        Consumer consumer = ONSFactory.createConsumer(properties);
        consumer.subscribe(topic, tags, listener);
        logger.info("consumer:{}", consumer);
        return consumer;
    }


    /**
     * 创建顺序Consumer
     *
     * @param tags
     * @param listener
     * @return
     */
    private static OrderConsumer getEvnOrderedConsumerConsumer(String tags, MessageOrderListener listener) {
        AliOnsMqConfig aliOnsMqConfig = SpringContextHolder.getBean(AliOnsMqConfig.class);
        String topic = aliOnsMqConfig.getRocketMqTopic();
        Properties properties = new Properties();
        setProperties(aliOnsMqConfig, properties, tags);
        OrderConsumer consumer = ONSFactory.createOrderedConsumer(properties);
        consumer.subscribe(topic, tags, listener);
        return consumer;
    }

    private static void setProperties(AliOnsMqConfig aliOnsMqConfig, Properties properties, String tags) {
        properties.put(PropertyKeyConst.ConsumerId, aliOnsMqConfig.getRocketMqTopic() + "_" + tags);
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
