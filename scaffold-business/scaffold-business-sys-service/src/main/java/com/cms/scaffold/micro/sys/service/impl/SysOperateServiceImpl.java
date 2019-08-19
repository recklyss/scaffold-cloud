package com.cms.scaffold.micro.sys.service.impl;

import com.cms.scaffold.common.base.BaseServiceImpl;
import com.cms.scaffold.micro.sys.dao.SysOperateMapper;
import com.cms.scaffold.micro.sys.domain.SysOperate;
import com.cms.scaffold.micro.sys.domain.SysRoleOperate;
import com.cms.scaffold.micro.sys.service.SysOperateService;
import com.cms.scaffold.micro.sys.service.SysRoleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhang
 */
@Service
public class SysOperateServiceImpl extends BaseServiceImpl<SysOperateMapper, SysOperate> implements SysOperateService {

    @Autowired
    private SysRoleOperateService sysRoleOperateService;

    @Override
    public int insertOneAndRole(SysOperate operate, Long roleId){
        int col = dao.insert(operate);
        SysRoleOperate sysRoleOperate = new SysRoleOperate();
        sysRoleOperate.setOperateId(operate.getId());
        sysRoleOperate.setRoleId(roleId);
        sysRoleOperateService.insert(sysRoleOperate);
        return col;
    }

    @Override
    public int updateOperate(SysOperate operate, Long roleId) {
        if (roleId != null) {
            final SysRoleOperate roleOperate = sysRoleOperateService.selectByOperateId(operate.getId());
            roleOperate.setRoleId(roleId);
            sysRoleOperateService.update(roleOperate);
        }
        return dao.update(operate);
    }
}
