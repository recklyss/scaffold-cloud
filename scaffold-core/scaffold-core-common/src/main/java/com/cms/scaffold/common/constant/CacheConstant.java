package com.cms.scaffold.common.constant;

/**
 * @Author zhangjiaheng
 * @Description redis缓存key等 常量类
 **/
public class CacheConstant {
    /**
     * 是
     */
    public static final String YES = "1";

    /**
     * 否
     */
    public static final String NO = "0";

    /**
     * 订阅通道:MQ消费失败
     */
    public static final String PUBLISH_MQ_CONSUMER_FAIL_CHANNEL = "mqConsumerFailChannel";

    /**
     * 订阅通道:MQ消费成功
     */
    public static final String PUBLISH_MQ_CONSUMER_SUCCESS_CHANNEL = "mqConsumerSuccessChannel";
    /**
     * 消息中间件:防重复生产唯一KEY
     */
    public static final String MQ_PRODUCE_KEY = "mq:produce:key:%s";
    /**
     * 消息中间件:MQ缓存对象唯一KEY
     */
    public static final String MQ_REDISMODEL_KEY = "mq:redisModel:key:%s";
    /**
     * 消息中间件:MQ缓存对象唯一KEY
     */
    public static final String MQ_REDISMODEL_KEY_INDEX = "mq:redisModel:key:index:";

    /**
     * 消息中间件:MQ缓存对象对应的过期KEY
     */
    public static final String MQ_REDISMODEL_KEY_EXPIRE = "mq:redisModel:key:expire:%s";
    /**
     * 消息中间件:MQ缓存对象对应的过期KEY索引
     */
    public static final String MQ_REDISMODEL_EXPIRE_KEY_INDEX = "mq:redisModel:key:expire:";

    /**
     * 消息中间件:防重复消费唯一KEY
     */
    public static final String MQ_CONSUMER_KEY = "mq:consumer:key:%s";

    /**
     * 参数配置
     */
    public static final String KEY_PREFIX_CONFIG_CODE = "sys:config:code:";

    /**
     * 字典类型
     */
    public static final String SYS_DICT_NID = "sys:dict:nid";

    /**
     * 字典名称-key
     */
    public static final String SYS_DICT_NID_NAME = "sys:dict:nid:%s";
}
