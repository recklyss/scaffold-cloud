package com.cms.scaffold.micro.sys.service;

import com.cms.scaffold.common.base.BaseService;
import com.cms.scaffold.micro.sys.domain.SysMenu;

import java.util.List;

public interface SysMenuService extends BaseService<SysMenu> {
    /**
     * @param pid
     * @return 根据Pid查询下一级子菜单
     */
    List<SysMenu> selectByPid(Long pid);

    /**
     * 根据操作员ID查询其有权限的菜单
     *
     * @param id
     * @return
     */
    List<SysMenu> findByOperateId(Long id);

    /**
     * 根据菜单ID和操作员ID查询
     *
     * @param pId
     * @param operateId
     * @return
     */
    List<SysMenu> findByPidAndOperateId(Long pId, Long operateId);

    /**
     * 更新或者新增菜单
     *
     * @param menu
     * @return
     */
    int saveOrUpdate(SysMenu menu);

    /**
     * 查询父级Ids
     *
     * @param id
     * @return
     */
    String findFatherIds(Long id);
}
