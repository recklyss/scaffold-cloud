package com.cms.scaffold.micro.sys.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.builder.Builder;
import com.cms.scaffold.common.response.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.cms.scaffold.micro.sys.api.SysI18nApi;
import com.cms.scaffold.micro.sys.domain.SysI18n;
import com.cms.scaffold.micro.sys.service.SysI18nService;

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
}
