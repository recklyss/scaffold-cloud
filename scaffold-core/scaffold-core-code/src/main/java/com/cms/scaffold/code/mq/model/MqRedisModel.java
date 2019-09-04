package com.cms.scaffold.code.mq.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zjh
 */
@Getter
@Setter
public class MqRedisModel implements Serializable {

    /**
     * 消息ID
     */
    private String messageId;
    /**
     * 消息key
     */
    private String messageKey;
    /**
     * 操作位
     */
    private String operate;
    /**
     * 流水号
     */
    private String requestNo;
    /**
     * 状态
     */
    private Long status;
    /**
     * 请求参数
     */
    private String requestParam;
    /**
     * 返回参数
     */
    private String responseParam;
    /**
     * 子标签
     */
    private String tags;
}
