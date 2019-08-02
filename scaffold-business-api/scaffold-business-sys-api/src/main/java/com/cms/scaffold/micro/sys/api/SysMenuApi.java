package com.cms.scaffold.micro.sys.api;

import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.micro.sys.bo.SysMenuBO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zhang
 * 接口、实现分离
 */
public interface SysMenuApi {

    /**
     * 根据pid查询子级菜单
     *
     * @param pid
     * @return
     */
    @RequestMapping(value = "/sysMenu/listMenuByPid", method = RequestMethod.GET)
    ResponseModel listMenuByPid(@RequestParam("pid") Long pid);

    /**
     * @param id 主键
     * @return 根据主键查询
     */
    @RequestMapping(value = "/sysMenu/selectById", method = RequestMethod.GET)
    ResponseModel selectById(@RequestParam("id") Long id);

    /**
     * @return 所有菜单数据
     */
    @RequestMapping(value = "/sysMenu/findAll", method = RequestMethod.GET)
    ResponseModel<List<SysMenuBO>> findAll();

    /**
     * @param id
     * @return 根据操作员查询他拥有权限的菜单
     */
    @RequestMapping(value = "/sysMenu/findByOperateId", method = RequestMethod.GET)
    ResponseModel<List<SysMenuBO>> findByOperateId(@RequestParam("id") Long id);
}
