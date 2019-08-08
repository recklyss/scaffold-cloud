package com.cms.scaffold.micro.sys.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.builder.Builder;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.micro.sys.ao.SysOperateAO;
import com.cms.scaffold.micro.sys.api.SysOperateApi;
import com.cms.scaffold.micro.sys.bo.SysOperateBO;
import com.cms.scaffold.micro.sys.domain.SysOperate;
import com.cms.scaffold.micro.sys.service.SysOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysOperateController extends BaseController<SysOperateBO> implements SysOperateApi {
    @Autowired
    SysOperateService sysOperateService;

    @Override
    public ResponseModel<SysOperateBO> findByUserName(String username) {
        SysOperate query = new SysOperate();
        query.setUserName(username);
        SysOperate operate = sysOperateService.selectOne(query);
        return successData(Builder.build(operate, SysOperateBO.class));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseModel<SysOperateBO> insert(SysOperateAO sysOperate) {
        final SysOperate operate = Builder.build(sysOperate, SysOperate.class);
        sysOperateService.insert(operate);
        return successData(Builder.build(operate, SysOperateBO.class));
    }
}
