package com.cms.scaffold.micro.sys.domain;

import com.cms.scaffold.common.annotation.TableName;
import com.cms.scaffold.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
* 
* @author: Mybatis Generator
* @date: 2019-07-28 18:54:02
*/
@Getter
@Setter
@TableName(name = "sys_operate")
public class SysOperate extends BaseEntity {
    /** 用户名**/
    private String userName;

    private String realName;

    /** 密码**/
    private String pwd;

    /** 手机号**/
    private String mobilePhone;

    /** 商户id**/
    private Long partnerId;

    /** 最近登录ip**/
    private String loginIp;

    /** 最近登录时间**/
    private Date loginTime;

    /** 状态**/
    private Long status;
}