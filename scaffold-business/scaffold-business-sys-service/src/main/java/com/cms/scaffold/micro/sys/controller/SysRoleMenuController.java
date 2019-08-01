package com.cms.scaffold.micro.sys.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.builder.Builder;
import com.cms.scaffold.common.response.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.cms.scaffold.micro.sys.api.SysRoleMenuApi;
import com.cms.scaffold.micro.sys.domain.SysRoleMenu;
import com.cms.scaffold.micro.sys.service.SysRoleMenuService;

/**
* @author zhangjiaheng
*/
@RestController
public class SysRoleMenuController extends BaseController implements SysRoleMenuApi {

    @Autowired
    SysRoleMenuService sysRoleMenuService;

    /**
    * @param id 主键
    * @return 根据主键查询
    */
    @Override
    public ResponseModel selectById(Long id) {
        final SysRoleMenu sysRoleMenu = sysRoleMenuService.selectById(id);
        return successData(Builder.build(sysRoleMenu, SysRoleMenu.class));
    }
}
