package com.cms.scaffold.common.constant;

/**
 * @Author zhangjiaheng
 * @Description MQ用到的一些常量（key/tag等）
 **/
public class MqConstant {
    /**
     * 主题
     */
    public static final String ROCKETMQ_TOPIC_KEY_NAME = "rocket.mq.topic";

    /**
     * key = TAG:类名:业务标识
     */
    public static final String ROCKETMQ_MESSAGE_KEY = "%s:%s:%s";

    /**
     * model类名
     */
    public static final String MODEL_CLASS_NAME = "MODEL_CLASS_NAME";

    /**
     * 后台管理系统操作日志tag
     */
    public static final String ROCKETMQ_TAG_OPERATE_LOG = "sys_operate_log_message";
}
