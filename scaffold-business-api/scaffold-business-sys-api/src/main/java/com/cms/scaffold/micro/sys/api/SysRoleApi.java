package com.cms.scaffold.micro.sys.api;

import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.micro.sys.ao.SysRoleAO;
import com.cms.scaffold.micro.sys.bo.SysRoleBO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author zhang
 */
public interface SysRoleApi {

    /**
     * 查询列表
     *
     * @param ao
     * @return
     */
    @RequestMapping(value = "/sysRole/findList", method = RequestMethod.POST)
    ResponseModel<List<SysRoleBO>> findList(@RequestBody SysRoleAO ao);
}
