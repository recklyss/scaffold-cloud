package com.cms.scaffold.micro.sys.service;

import com.cms.scaffold.common.base.BaseService;
import com.cms.scaffold.micro.sys.domain.SysRoleMenu;

/**
 * @author zhangjiaheng
 */
public interface SysRoleMenuService extends BaseService<SysRoleMenu> {
    /**
     * 删除角色菜单授权
     *
     * @param menuIds
     * @param roleId
     * @return
     */
    int deleteMenu(String menuIds, Long roleId);
}
