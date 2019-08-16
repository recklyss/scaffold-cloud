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

    /**
     * 根据pid查询
     *
     * @param parentId
     * @return
     */
    List<SysDict> findSysDictByPid(Long parentId);

    /**
     * == findByNid 但从缓存查找
     *
     * @param nid
     * @return
     */
    List<SysDict> findByPartnerNid(String nid);

    /**
     * 缓存字典到redis
     */
    void loadDictIntoRedis();

    /**
     * 新增或者更新
     *
     * @param sysDict
     * @return
     */
    int save(SysDict sysDict);

    /**
     * @param id
     * @return
     */
    String findFatherIds(Long id);
}
