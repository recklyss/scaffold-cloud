package com.cms.scaffold.micro.sys.service;

import com.cms.scaffold.common.base.BaseService;
import com.cms.scaffold.micro.sys.domain.SysDict;

import java.util.List;

public interface SysDictService extends BaseService<SysDict> {
    /**
     * 根据nid查找
     *
     * @param nid
     * @return
     */
    List<SysDict> findByNid(String nid);

    /**根据pid查询
     * @param parentId
     * @return
     */
    List<SysDict> findSysDictByPid(Long parentId);
}
