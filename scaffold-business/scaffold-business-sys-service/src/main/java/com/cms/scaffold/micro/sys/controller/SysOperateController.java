package com.cms.scaffold.micro.sys.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.builder.Builder;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.common.response.ResponsePageModel;
import com.cms.scaffold.micro.sys.ao.SysOperateAO;
import com.cms.scaffold.micro.sys.api.SysOperateApi;
import com.cms.scaffold.micro.sys.bo.SysOperateBO;
import com.cms.scaffold.micro.sys.domain.SysOperate;
import com.cms.scaffold.micro.sys.domain.SysRoleOperate;
import com.cms.scaffold.micro.sys.service.SysOperateService;
import com.cms.scaffold.micro.sys.service.SysRoleOperateService;
import com.cms.scaffold.micro.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhang
 */
@RestController
public class SysOperateController extends BaseController<SysOperateBO> implements SysOperateApi {
    @Autowired
    SysOperateService sysOperateService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleOperateService sysRoleOperateService;

    @Override
    public ResponseModel<SysOperateBO> findByUserName(String username) {
        SysOperate query = new SysOperate();
        query.setUserName(username);
        SysOperate operate = sysOperateService.selectOne(query);
        return successData(Builder.build(operate, SysOperateBO.class));
    }

    @Override
    public ResponseModel<SysOperateBO> insert(@RequestBody SysOperateAO sysOperate) {
        final SysOperate operate = Builder.build(sysOperate, SysOperate.class);
        sysOperateService.insertOneAndRole(operate, sysOperate.getRoleId());
        return successData(Builder.build(operate, SysOperateBO.class));
    }

    @Override
    public ResponsePageModel<SysOperateBO> findOperatePage(@RequestBody SysOperateAO operateAo) {
        ResponsePageModel<SysOperate> page = sysOperateService.findPage(Builder.build(operateAo, SysOperate.class));
        List<SysOperateBO> sysOperateBoList = Builder.buildList(page.getRows(), SysOperateBO.class);
        sysOperateBoList.forEach(o->{
            SysRoleOperate sysRoleOperate = sysRoleOperateService.selectByOperateId(o.getId());
            o.setRoleName(sysRoleService.selectById(sysRoleOperate.getRoleId()).getName());
        });
        return new ResponsePageModel<>(sysOperateBoList, page.getTotal());
    }

    @Override
    public ResponseModel<SysOperateBO> selectById(Long id) {
        return successData(Builder.build(sysOperateService.selectById(id), SysOperateBO.class));
    }

    @Override
    public ResponseModel update(@RequestBody SysOperateAO ao) {
        sysOperateService.updateOperate(Builder.build(ao, SysOperate.class), ao.getRoleId());
        return success();
    }
}
