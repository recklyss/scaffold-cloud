package com.cms.scaffold.micro.sys.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.builder.Builder;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.micro.sys.api.SysMenuApi;
import com.cms.scaffold.micro.sys.bo.SysMenuBO;
import com.cms.scaffold.micro.sys.domain.SysMenu;
import com.cms.scaffold.micro.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhang
 * 菜单资源微服务接口
 * 接口地址在API中声明
 * 接口与实现分离
 */
@RestController
public class SysMenuController extends BaseController implements SysMenuApi {

    @Autowired
    SysMenuService sysMenuService;

    @Override
    public ResponseModel listMenuByPid(Long pid) {
        List<SysMenu> sysMenuList = sysMenuService.selectByPid(pid);
        List<SysMenuBO> bo = Builder.buildList(sysMenuList, SysMenuBO.class);
        return successData(bo);
    }

    /**
     * @param id 主键
     * @return 根据主键查询
     */
    @Override
    public ResponseModel selectById(Long id) {
        final SysMenu sysMenu = sysMenuService.selectById(id);
        return successData(Builder.build(sysMenu, SysMenuBO.class));
    }
}
