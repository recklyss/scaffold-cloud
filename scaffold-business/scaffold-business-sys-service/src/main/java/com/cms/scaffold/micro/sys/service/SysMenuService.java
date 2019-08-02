package com.cms.scaffold.micro.sys.service;

import com.cms.scaffold.common.base.BaseService;
import com.cms.scaffold.micro.sys.domain.SysMenu;

import java.util.List;

public interface SysMenuService extends BaseService<SysMenu>{
    /**
     * @param pid
     * @return 根据Pid查询下一级子菜单
     */
    List<SysMenu> selectByPid(Long pid);

    /**
     * 根据操作员ID查询其有权限的菜单
     * @param id
     * @return
     */
    SysMenu findByOperateId(Long id);
}
