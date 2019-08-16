package com.cms.scaffold.micro.sys.api;

import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.micro.sys.ao.SysDictAO;
import com.cms.scaffold.micro.sys.bo.SysDictBO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zhang
 */
public interface SysDictApi {

    /**
     * 根据pid查询子级菜单
     *
     * @param nid
     * @return
     */
    @RequestMapping(value = "/sysDict/findByNid", method = RequestMethod.GET)
    ResponseModel<List<SysDictBO>> findByNid(@RequestParam(value = "nid") String nid);

    /**
     * 根据pid查询
     *
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/sysDict/findSysDictByPid", method = RequestMethod.GET)
    ResponseModel<List<SysDictBO>> findSysDictByPid(@RequestParam("parentId") Long parentId);

    /**
     * 根据pid查询子级菜单
     *
     * @param nid
     * @return
     */
    @RequestMapping(value = "/sysDict/findByPartnerNid", method = RequestMethod.GET)
    ResponseModel<List<SysDictBO>> findByPartnerNid(@RequestParam("nid") String nid);

    @RequestMapping(value = "/sysDict/save", method = RequestMethod.POST, consumes =
            MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseModel save(@RequestBody SysDictAO dict);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/sysDict/selectById", method = RequestMethod.GET)
    ResponseModel<SysDictBO> selectById(@RequestParam("id") Long id);

    @RequestMapping(value = "/sysDict/findFatherIds", method = RequestMethod.GET)
    ResponseModel<String> findFatherIds(@RequestParam("id")Long id);
}
