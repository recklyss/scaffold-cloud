package com.cms.scaffold.micro.sys.service;

import com.cms.scaffold.code.mq.model.sys.SysOperateLogModel;
import com.cms.scaffold.common.base.BaseService;
import com.cms.scaffold.micro.sys.domain.SysOperateLog;

/**
 * @author zhangjiaheng
 */
public interface SysOperateLogService extends BaseService<SysOperateLog> {
    /**
     * 来自MQ的插入
     *
     * @param sysOperateLogModel
     * @return
     */
    int insert(SysOperateLogModel sysOperateLogModel);
}
