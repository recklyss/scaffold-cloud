package com.cms.scaffold.micro.sys.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.builder.Builder;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.micro.sys.ao.SysRoleMenuAO;
import com.cms.scaffold.micro.sys.bo.SysRoleMenuBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cms.scaffold.micro.sys.api.SysRoleMenuApi;
import com.cms.scaffold.micro.sys.domain.SysRoleMenu;
import com.cms.scaffold.micro.sys.service.SysRoleMenuService;

import java.util.List;

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

    @Override
    public ResponseModel deleteMenu(String menuIds, Long roleId) {
        sysRoleMenuService.deleteMenu(menuIds, roleId);
        return success();
    }

    @Override
    public ResponseModel addRoleMenu(@RequestBody List<SysRoleMenuAO> list) {
        final List<SysRoleMenu> sysRoleMenus = Builder.buildList(list, SysRoleMenu.class);
        sysRoleMenus.forEach(o -> {
            final SysRoleMenu sysRoleMenu = sysRoleMenuService.selectOne(o);
            if (null == sysRoleMenu) {
                sysRoleMenuService.insert(o);
            }
        });
        return success();
    }

    @Override
    public ResponseModel<SysRoleMenuBO> selectByRoleIdAndMenuId(Long roleId, Long menuId) {
        SysRoleMenu query = new SysRoleMenu();
        query.setMenuId(menuId);
        query.setRoleId(roleId);
        return successData(Builder.build(sysRoleMenuService.selectOne(query), SysRoleMenuBO.class));
    }
}
