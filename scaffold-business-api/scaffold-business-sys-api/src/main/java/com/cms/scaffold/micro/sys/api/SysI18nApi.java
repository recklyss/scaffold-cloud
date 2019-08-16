package com.cms.scaffold.micro.sys.api;

import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.common.response.ResponsePageModel;
import com.cms.scaffold.micro.sys.ao.SysI18nAO;
import com.cms.scaffold.micro.sys.bo.SysI18nBO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zhangjiaheng
 */
public interface SysI18nApi {


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return
     */
    @RequestMapping(value = "/sysI18n/selectById", method = RequestMethod.GET)
    ResponseModel<SysI18nBO> selectById(@RequestParam(value = "id") Long id);

    /**
     * 根据条件查询列表
     *
     * @param sysI18nAO
     * @return
     */
    @RequestMapping(value = "/sysI18n/findList", method = RequestMethod.POST)
    ResponseModel<List<SysI18nBO>> findList(@RequestBody SysI18nAO sysI18nAO);

    /**
     * 根据条件查询分页
     *
     * @param ao
     * @return
     */
    @RequestMapping(value = "/sysI18n/findPage", method = RequestMethod.POST, consumes =
            MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponsePageModel<SysI18nBO> findPage(@RequestBody SysI18nAO ao);

    /**
     * 新增或者更新
     *
     * @param ao
     * @return
     */
    @RequestMapping(value = "/sysI18n/save", method = RequestMethod.POST, consumes =
            MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseModel save(@RequestBody SysI18nAO ao);
}
