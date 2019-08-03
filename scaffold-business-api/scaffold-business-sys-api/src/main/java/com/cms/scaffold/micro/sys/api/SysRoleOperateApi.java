package com.cms.scaffold.micro.sys.api;

import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.micro.sys.bo.SysRoleOperateBO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhangjiaheng
 */
public interface SysRoleOperateApi {


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return
     */
    @RequestMapping(value = "/sysRoleOperate/selectById", method = RequestMethod.GET)
    ResponseModel<SysRoleOperateBO> selectById(@RequestParam(value = "id") Long id);

    /**
     * 根据操作员ID查询
     *
     * @param operateId
     * @return
     */
    @RequestMapping(value = "/sysRoleOperate/selectByOperateId", method = RequestMethod.GET)
    ResponseModel<SysRoleOperateBO> selectByOperateId(@RequestParam(value = "operateId") Long operateId);
}
