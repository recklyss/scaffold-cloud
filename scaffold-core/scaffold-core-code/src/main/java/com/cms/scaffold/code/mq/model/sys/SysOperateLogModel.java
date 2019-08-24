package com.cms.scaffold.code.mq.model.sys;

import com.cms.scaffold.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhang
 */
@Getter
@Setter
@ToString
public class SysOperateLogModel extends BaseEntity {
    /** 追踪日志id**/
    private String traceId;

    /** 类名**/
    private String className;

    /** 请求的地址**/
    private String requestUrl;

    /** 请求的方法名**/
    private String requestMethod;

    /** 操作人ID**/
    private Long operateId;

    /** 操作人姓名**/
    private String operateName;

    /** 请求的参数**/
    private String requestParam;

    /** 返回参数**/
    private String responseParam;
}
