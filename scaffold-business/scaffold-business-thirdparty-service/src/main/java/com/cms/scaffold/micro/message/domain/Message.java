package com.cms.scaffold.micro.message.domain;

import com.cms.scaffold.common.annotation.TableName;
import com.cms.scaffold.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 
* @author: Mybatis Generator
* @date: 2019-09-02 19:23:10
*/
@Getter
@Setter
@ToString
@TableName(name = "sys_message")
public class Message extends BaseEntity {
    /** 消息ID**/
    private String messageId;

    /** 消息KEY**/
    private String messageKey;

    /** 操作**/
    private String operate;

    /** 请求流水号**/
    private String requestNo;

    /** 1-消费正常 2-消费异常**/
    private Long status;

    /** 请求参数**/
    private String requestParam;

    /** 响应参数**/
    private String responseParam;

    /** 请求次数**/
    private Long triggerNum;

    /** 消息TAG**/
    private String tags;
}