package com.cms.scaffold.micro.sys.api;

import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.micro.sys.ao.SysMenuAO;
import com.cms.scaffold.micro.sys.bo.SysMenuBO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
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
    ResponseModel<List<SysMenuBO>> listMenuByPid(@RequestParam(value = "pid") Long pid);

    /**
     * @param id 主键
     * @return 根据主键查询
     */
    @RequestMapping(value = "/sysMenu/selectById", method = RequestMethod.GET)
    ResponseModel<SysMenuBO> selectById(@RequestParam(value = "id") Long id);

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
    ResponseModel<List<SysMenuBO>> findByOperateId(@RequestParam(value = "id") Long id);

    /**
     * 根据父级ID和操作员ID查询
     *
     * @param pId
     * @param operateId
     * @return
     */
    @RequestMapping(value = "/sysMenu/findByPidAndOperateId", method = RequestMethod.GET)
    ResponseModel<List<SysMenuBO>> findByPidAndOperateId(@RequestParam(value = "pId") Long pId,
                                                         @RequestParam(value = "operateId") Long operateId);

    /**
     * 保存或者更新
     *
     * @param menu
     * @return
     */
    @RequestMapping(value = "/sysMenu/saveOrUpdate", method = RequestMethod.POST, consumes =
            MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseModel saveOrUpdate(@RequestBody SysMenuAO menu);

    /**
     * 查询所有父级ID
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/sysMenu/findFatherIds", method = RequestMethod.GET)
    ResponseModel<String> findFatherIds(@RequestParam(value = "id") Long id);
}
