package com.cms.scaffold.micro.sys.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.builder.Builder;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.micro.sys.ao.SysDictAO;
import com.cms.scaffold.micro.sys.api.SysDictApi;
import com.cms.scaffold.micro.sys.bo.SysDictBO;
import com.cms.scaffold.micro.sys.domain.SysDict;
import com.cms.scaffold.micro.sys.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangjiaheng
 */
@RestController
public class SysDictController extends BaseController implements SysDictApi {
    @Autowired
    SysDictService sysDictService;
    @Override
    public ResponseModel<List<SysDictBO>> findByNid(String nid) {
        List<SysDict> sysDictList = sysDictService.findByNid(nid);
        return successData(Builder.buildList(sysDictList, SysDictBO.class));
    }

    @Override
    public ResponseModel<List<SysDictBO>> findSysDictByPid(Long parentId) {
        List<SysDict> list = sysDictService.findSysDictByPid(parentId);
        return successData(Builder.buildList(list, SysDictBO.class));
    }

    @Override
    public ResponseModel<List<SysDictBO>> findByPartnerNid(String nid) {
        final List<SysDict> sysDictList = sysDictService.findByPartnerNid(nid);
        return successData(Builder.buildList(sysDictList, SysDictBO.class));
    }

    @Override
    public ResponseModel<SysDictBO> save(@RequestBody SysDictAO dict) {
        final SysDict build = Builder.build(dict, SysDict.class);
        sysDictService.save(build);
        return successData(build);
    }

    @Override
    public ResponseModel<SysDictBO> selectById(Long id) {
        return successData(Builder.build(sysDictService.selectById(id), SysDictBO.class));
    }

    @Override
    public ResponseModel<String> findFatherIds(Long id) {
        final String fatherIds = sysDictService.findFatherIds(id);
        return successData(fatherIds);
    }
}
