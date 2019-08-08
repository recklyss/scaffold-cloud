package com.cms.scaffold.micro.sys.api;

import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.micro.sys.ao.SysOperateAO;
import com.cms.scaffold.micro.sys.bo.SysOperateBO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhang
 * 接口、实现分离
 */
public interface SysOperateApi {

    /**
     * @param username
     * @return 根据用户名查询的用户实体
     */
    @RequestMapping(value = "/sysOperate/findByUserName", method = RequestMethod.GET)
    ResponseModel<SysOperateBO> findByUserName(@RequestParam(value = "username") String username);

    /**
     * 插入一个用户
     *
     * @param sysOperate
     * @return
     */
    @RequestMapping(value = "/sysOperate/findByUserName", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseModel<SysOperateBO> insert(@RequestBody SysOperateAO sysOperate);
}
