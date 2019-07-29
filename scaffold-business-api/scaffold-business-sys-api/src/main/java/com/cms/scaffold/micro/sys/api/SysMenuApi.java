package com.cms.scaffold.micro.sys.api;

import com.cms.scaffold.common.base.ResponseModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhang
 * 用于测试的API接口
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
}
