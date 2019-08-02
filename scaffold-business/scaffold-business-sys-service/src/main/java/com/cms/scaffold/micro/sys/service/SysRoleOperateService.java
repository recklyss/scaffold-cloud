package com.cms.scaffold.micro.sys.service;

import com.cms.scaffold.common.base.BaseService;
import com.cms.scaffold.micro.sys.domain.SysRoleOperate;

/**
 * @author zhangjiaheng
 */
public interface SysRoleOperateService extends BaseService<SysRoleOperate> {
    /**
     * 根据操作员ID查询
     *
     * @param operateId
     * @return
     */
    SysRoleOperate selectByOperateId(Long operateId);
}
