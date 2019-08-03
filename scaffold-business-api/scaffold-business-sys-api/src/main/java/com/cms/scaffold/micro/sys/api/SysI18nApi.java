package com.cms.scaffold.micro.sys.api;

import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.micro.sys.ao.SysI18nAO;
import com.cms.scaffold.micro.sys.bo.SysI18nBO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @author zhangjiaheng
*/
public interface SysI18nApi{


    /**
    * @param id 主键
    * @return 根据主键查询
    */
    @RequestMapping(value = "/sysI18n/selectById", method = RequestMethod.GET)
    ResponseModel selectById(@RequestParam(value = "id") Long id);

    @RequestMapping(value = "/sysI18n/findList", method = RequestMethod.POST)
    ResponseModel<List<SysI18nBO>> findList(@RequestBody SysI18nAO sysI18nAO);
}
