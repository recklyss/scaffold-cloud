package com.cms.scaffold.micro.sys.service;

import com.cms.scaffold.common.base.BaseService;
import com.cms.scaffold.micro.sys.domain.SysOperate;

/**
 * @author zhang
 */
public interface SysOperateService extends BaseService<SysOperate> {
    /**
     * 新增操作员
     *
     * @param operate
     * @param roleId  角色ID
     * @return
     */
    int insertOneAndRole(SysOperate operate, Long roleId);

    /**
     * 更新操作员
     *
     * @param operate
     * @param roleId
     * @return
     */
    int updateOperate(SysOperate operate, Long roleId);
}
