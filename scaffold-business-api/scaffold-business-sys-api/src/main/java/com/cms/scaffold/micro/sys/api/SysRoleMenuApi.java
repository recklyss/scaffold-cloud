package com.cms.scaffold.micro.sys.api;

import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.micro.sys.ao.SysRoleMenuAO;
import com.cms.scaffold.micro.sys.bo.SysRoleMenuBO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zhangjiaheng
 */
public interface SysRoleMenuApi {


    /**
     * @param id 主键
     * @return 根据主键查询
     */
    @RequestMapping(value = "/sysRoleMenu/selectById", method = RequestMethod.GET)
    ResponseModel selectById(@RequestParam(value = "id") Long id);

    /**
     * 取消授权
     *
     * @param menuIds
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/sysRoleMenu/deleteMenu", method = RequestMethod.POST)
    ResponseModel deleteMenu(@RequestParam("menuIds") String menuIds, @RequestParam("roleId") Long roleId);

    /**
     * 给角色授权菜单
     *
     * @param list
     * @return
     */
    @RequestMapping(value = "/sysRoleMenu/addRoleMenu", method = RequestMethod.POST, consumes =
            MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseModel addRoleMenu(@RequestBody List<SysRoleMenuAO> list);

    /**
     * 根据角色ID和菜单ID查询
     *
     * @param roleId
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/sysRoleMenu/selectByRoleIdAndMenuId", method = RequestMethod.GET)
    ResponseModel<SysRoleMenuBO> selectByRoleIdAndMenuId(@RequestParam("roleId") Long roleId, @RequestParam("menuId") Long menuId);
}
