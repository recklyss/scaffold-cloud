package com.cms.scaffold.micro.sys.api;

import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.micro.sys.bo.SysDictBO;
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
     */
    @RequestMapping(value = "/sysMenu/findByNid", method = RequestMethod.GET)
    ResponseModel<List<SysDictBO>> findByNid(@RequestParam("nid") String nid);
}
