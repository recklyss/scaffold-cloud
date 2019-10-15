package com.cms.scaffold.code.mq.model;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.Message;
import com.cms.scaffold.code.config.commonly.AliOnsMqConfig;
import com.cms.scaffold.code.config.commonly.SpringContextHolder;
import com.cms.scaffold.code.mq.base.MqBaseInterface;
import com.cms.scaffold.common.constant.MqConstant;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Properties;

/**
 * @Author zhangjiaheng
 * @Description MQ传输model基础类
 **/
@Getter
@Setter
public class MqBaseModel implements MqBaseInterface, Serializable {

    private static final AliOnsMqConfig ALI_ONS_MQ_CONFIG = SpringContextHolder.getBean(AliOnsMqConfig.class);

    /**
     * 消息主题
     */
    protected final static String TOPIC = ALI_ONS_MQ_CONFIG.getRocketMqTopic();

    /**
     * 对应操作实体类bean名
     */
    protected String operate = StrUtil.lowerFirst(this.getClass().getSimpleName());

    /**
     * 唯一标识
     */
    protected String key;

    /**
     * 消费消息的返回结果
     */
    @JSONField(serialize = false)
    protected Action action;

    /**
     * 类名
     */
    @JSONField(serialize = false)
    protected String springBean;
    /**
     * 方法名
     */
    @JSONField(serialize = false)
    protected String methodName;

    /**
     * model全限定路径类名
     */
    @JSONField(serialize = false)
    protected String modelClassName;

    /**
     * 子标题
     */
    @JSONField(serialize = false)
    protected String tag;

    /**
     * 请求流水号
     */
    protected String requestNo;

    protected Object[] objs;

    /**
     * 无参构造方法
     */
    public MqBaseModel() {
        super();
    }

    /**
     * 参构造方法
     *
     * @param tag
     * @param springBean
     * @param methodName
     */
    public MqBaseModel(String tag, String springBean, String methodName, String modelClassName) {
        this.tag = tag;
        this.springBean = springBean;
        this.methodName = methodName;
        this.modelClassName = modelClassName;
    }

    /**
     * 获取构造message
     *
     * @return
     */
    @JSONField(serialize = false)
    public Message getMessage() {
        Assert.notNull(getTag(), "tag cannot be null");
        Assert.notNull(getRequestNo(), "requestNo cannot be null");
        this.key = String.format(MqConstant.ROCKETMQ_MESSAGE_KEY, tag,
                StrUtil.lowerFirst(this.getClass().getSimpleName()), getRequestNo());
        Assert.notNull(getKey(), "key");
        Message message = new Message(TOPIC, getTag(), getKey(), this.toString().getBytes());
        Properties userProperties = new Properties();
        userProperties.setProperty(MqConstant.MODEL_CLASS_NAME, modelClassName);
        message.setUserProperties(userProperties);
        return message;
    }

    @Override
    public Object[] getObjs() {
        return new Object[0];
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
