package com.cms.scaffold.micro.sys.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.builder.Builder;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.micro.sys.api.SysDictApi;
import com.cms.scaffold.micro.sys.bo.SysDictBO;
import com.cms.scaffold.micro.sys.domain.SysDict;
import com.cms.scaffold.micro.sys.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
