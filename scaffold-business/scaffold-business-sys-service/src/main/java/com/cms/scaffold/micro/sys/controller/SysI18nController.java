package com.cms.scaffold.micro.sys.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.builder.Builder;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.common.response.ResponsePageModel;
import com.cms.scaffold.micro.sys.ao.SysI18nAO;
import com.cms.scaffold.micro.sys.bo.SysI18nBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cms.scaffold.micro.sys.api.SysI18nApi;
import com.cms.scaffold.micro.sys.domain.SysI18n;
import com.cms.scaffold.micro.sys.service.SysI18nService;

import java.util.List;

/**
 * @author zhangjiaheng
 */
@RestController
public class SysI18nController extends BaseController implements SysI18nApi {

    @Autowired
    SysI18nService sysI18nService;

    /**
     * @param id 主键
     * @return 根据主键查询
     */
    @Override
    public ResponseModel selectById(Long id) {
        final SysI18n sysI18n = sysI18nService.selectById(id);
        return successData(Builder.build(sysI18n, SysI18n.class));
    }

    @Override
    public ResponseModel<List<SysI18nBO>> findList(@RequestBody SysI18nAO sysI18nAO) {
        List<SysI18n> list = sysI18nService.findList(Builder.build(sysI18nAO, SysI18n.class));
        return successData(Builder.buildList(list, SysI18nBO.class));

    }

    @Override
    public ResponsePageModel<SysI18nBO> findPage(@RequestBody SysI18nAO ao) {
        ResponsePageModel<SysI18n> page = sysI18nService.findPage(Builder.build(ao, SysI18n.class));
        return new ResponsePageModel<>(Builder.buildList(page.getRows(), SysI18nBO.class), page.getTotal());
    }

    @Override
    public ResponseModel save(@RequestBody SysI18nAO ao) {
        if (null == ao.getId()) {
            sysI18nService.insert(Builder.build(ao, SysI18n.class));
        } else {
            sysI18nService.update(Builder.build(ao, SysI18n.class));
        }
        return success();
    }
}
