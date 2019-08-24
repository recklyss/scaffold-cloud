package com.cms.scaffold.micro.sys.service.impl;

import com.cms.scaffold.code.mq.model.sys.SysOperateLogModel;
import com.cms.scaffold.common.builder.Builder;
import com.cms.scaffold.micro.sys.dao.SysOperateLogMapper;
import com.cms.scaffold.micro.sys.domain.SysOperateLog;
import com.cms.scaffold.micro.sys.service.SysOperateLogService;
import com.cms.scaffold.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangjiaheng
 * 后台操作日志记录
 */
@Service
@Slf4j
public class SysOperateLogServiceImpl extends BaseServiceImpl<SysOperateLogMapper, SysOperateLog> implements SysOperateLogService {
    @Override
    public int insert(SysOperateLogModel sysOperateLogModel) {
        log.info("开始插入后台操作日志记录：类：{}，方法：{}， 参数：{}", sysOperateLogModel.getClassName(),
                sysOperateLogModel.getRequestMethod(),
                sysOperateLogModel.getRequestParam());
        SysOperateLog sysOperateLog = Builder.build(sysOperateLogModel, SysOperateLog.class);
        return dao.insert(sysOperateLog);
    }
}
