package com.cms.scaffold.micro.sys.service.impl;

import com.cms.scaffold.micro.sys.dao.SysRoleOperateMapper;
import com.cms.scaffold.micro.sys.domain.SysRoleOperate;
import com.cms.scaffold.micro.sys.service.SysRoleOperateService;
import com.cms.scaffold.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author zhangjiaheng
*/
@Service
public class SysRoleOperateServiceImpl extends BaseServiceImpl<SysRoleOperateMapper, SysRoleOperate> implements SysRoleOperateService {
    @Override
    public SysRoleOperate selectByOperateId(Long operateId) {
        return dao.selectByOperateId(operateId);
    }
}
