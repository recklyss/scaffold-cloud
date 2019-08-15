package com.cms.scaffold.code.config.commonly;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author zhangjiaheng
 * @Description
 **/
@Configuration
@PropertySource(ignoreResourceNotFound = true, value = "classpath:code.properties")
@Getter
@Setter
@ToString
public class AliOnsMqConfig {

    @Value("${rocket.mq.topic}")
    String rocketMqTopic;
    @Value("${rocket.mq.producer.id}")
    String rocketMqProducerId;
    @Value("${rocket.mq.access.key}")
    String rocketMqAccessKey;
    @Value("${rocket.mq.secret.key}")
    String rocketMqSecretKey;
    @Value("${rocket.mq.ons.addr}")
    String rocketMqOnsAddr;
    @Value("${rocket.mq.name.srv.addr}")
    String rocketMqNameSrvAddr;

}
