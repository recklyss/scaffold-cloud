package com.cms.scaffold.micro.sys.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.builder.Builder;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.micro.sys.bo.SysRoleOperateBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.cms.scaffold.micro.sys.api.SysRoleOperateApi;
import com.cms.scaffold.micro.sys.domain.SysRoleOperate;
import com.cms.scaffold.micro.sys.service.SysRoleOperateService;

/**
* @author zhangjiaheng
*/
@RestController
public class SysRoleOperateController extends BaseController implements SysRoleOperateApi {

    @Autowired
    SysRoleOperateService sysRoleOperateService;

    /**
    * @param id 主键
    * @return 根据主键查询
    */
    @Override
    public ResponseModel selectById(Long id) {
        final SysRoleOperate sysRoleOperate = sysRoleOperateService.selectById(id);
        return successData(Builder.build(sysRoleOperate, SysRoleOperateBO.class));
    }

    @Override
    public ResponseModel selectByOperateId(Long operateId) {
        SysRoleOperate roleOperate = sysRoleOperateService.selectByOperateId(operateId);
        return successData(Builder.build(roleOperate, SysRoleOperateBO.class));
    }
}
