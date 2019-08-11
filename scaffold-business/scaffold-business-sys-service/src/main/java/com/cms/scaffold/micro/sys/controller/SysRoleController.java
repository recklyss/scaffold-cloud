package com.cms.scaffold.micro.sys.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.builder.Builder;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.micro.sys.ao.SysRoleAO;
import com.cms.scaffold.micro.sys.api.SysRoleApi;
import com.cms.scaffold.micro.sys.bo.SysRoleBO;
import com.cms.scaffold.micro.sys.domain.SysRole;
import com.cms.scaffold.micro.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhang
 */
@RestController
public class SysRoleController extends BaseController<SysRoleBO> implements SysRoleApi {
    @Autowired
    SysRoleService sysRoleService;
    @Override
    public ResponseModel<List<SysRoleBO>> findList(SysRoleAO ao) {
        List<SysRole> sysRoles = sysRoleService.findList(Builder.build(ao, SysRole.class));
        List<SysRoleBO> sysRoleBOS = Builder.buildList(sysRoles, SysRoleBO.class);
        return successData(sysRoleBOS);
    }
}
