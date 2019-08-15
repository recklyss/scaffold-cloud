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
     * 子标题
     */
    @JSONField(serialize = false)
    protected String tag;

    /**
     * 请求流水号
     */
    protected String requestNo;

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
    public MqBaseModel(String tag, String springBean, String methodName) {
        this.tag = tag;
        this.springBean = springBean;
        this.methodName = methodName;
    }

    /**
     * 获取构造message
     *
     * @return
     */
    @JSONField(serialize = false)
    public Message getMessage() {
        Assert.notNull(getTag(), "tag");
        Assert.notNull(getRequestNo(), "requestNo");
        this.key = String.format(MqConstant.ROCKETMQ_MESSAGE_KEY, tag,
                StrUtil.lowerFirst(this.getClass().getSimpleName()), getRequestNo());
        Assert.notNull(getKey(), "key");
        Message message = new Message(TOPIC, getTag(), getKey(), this.toString().getBytes());

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
