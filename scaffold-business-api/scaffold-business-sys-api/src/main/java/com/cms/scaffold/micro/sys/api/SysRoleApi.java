package com.cms.scaffold.micro.sys.api;

import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.common.response.ResponsePageModel;
import com.cms.scaffold.micro.sys.ao.SysRoleAO;
import com.cms.scaffold.micro.sys.bo.SysRoleBO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    /**
     * 分页查询
     *
     * @param sysRoleAo
     * @return
     */
    @RequestMapping(value = "/sysRole/findPage", method = RequestMethod.POST)
    ResponsePageModel findPage(@RequestBody SysRoleAO sysRoleAo);

    /**
     * 新增角色
     *
     * @param sysRole
     * @return
     */
    @RequestMapping(value = "/sysRole/insert", method = RequestMethod.POST)
    ResponseModel insert(@RequestBody SysRoleAO sysRole);

    /**
     * 根据ID查询
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/sysRole/selectById", method = RequestMethod.GET)
    ResponseModel<SysRoleBO> selectById(@RequestParam("roleId") Long roleId);
}
