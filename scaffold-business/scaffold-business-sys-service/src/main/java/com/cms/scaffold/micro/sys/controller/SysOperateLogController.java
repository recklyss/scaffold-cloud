package com.cms.scaffold.micro.sys.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.builder.Builder;
import com.cms.scaffold.common.response.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.cms.scaffold.micro.sys.api.SysOperateLogApi;
import com.cms.scaffold.micro.sys.domain.SysOperateLog;
import com.cms.scaffold.micro.sys.service.SysOperateLogService;
import lombok.extern.slf4j.Slf4j;

/**
* @author zhangjiaheng
*/
@RestController
@Slf4j
public class SysOperateLogController extends BaseController implements SysOperateLogApi {

    @Autowired
    SysOperateLogService sysOperateLogService;

    /**
    * @param id 主键
    * @return 根据主键查询
    */
    @Override
    public ResponseModel selectById(Long id) {
        log.info("根据ID主键查询>>> id:[{}]", id);
        final SysOperateLog sysOperateLog = sysOperateLogService.selectById(id);
        return successData(Builder.build(sysOperateLog, SysOperateLog.class));
    }
}
