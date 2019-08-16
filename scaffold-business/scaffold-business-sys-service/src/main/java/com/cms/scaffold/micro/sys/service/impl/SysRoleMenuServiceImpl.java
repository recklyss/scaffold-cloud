package com.cms.scaffold.micro.sys.service.impl;

import com.cms.scaffold.common.base.BaseServiceImpl;
import com.cms.scaffold.micro.sys.dao.SysRoleMenuMapper;
import com.cms.scaffold.micro.sys.domain.SysRoleMenu;
import com.cms.scaffold.micro.sys.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

/**
* @author zhangjiaheng
*/
@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    @Override
    public int deleteMenu(String menuIds, Long roleId) {
        int cnt = 0;
        String[] menuIdAry = menuIds.split(",");
        for (int i = 0; i < menuIdAry.length; i++) {
            cnt += dao.deleteMenu(menuIdAry[i], roleId);
        }
        return cnt;
    }
}
