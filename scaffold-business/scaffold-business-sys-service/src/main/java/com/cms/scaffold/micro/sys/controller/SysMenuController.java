package com.cms.scaffold.micro.sys.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.builder.Builder;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.micro.sys.ao.SysMenuAO;
import com.cms.scaffold.micro.sys.api.SysMenuApi;
import com.cms.scaffold.micro.sys.bo.SysMenuBO;
import com.cms.scaffold.micro.sys.domain.SysMenu;
import com.cms.scaffold.micro.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseModel<List<SysMenuBO>> listMenuByPid(Long pid) {
        List<SysMenu> sysMenuList = sysMenuService.selectByPid(pid);
        List<SysMenuBO> bo = Builder.buildList(sysMenuList, SysMenuBO.class);
        return successData(bo);
    }

    /**
     * @param id 主键
     * @return 根据主键查询
     */
    @Override
    public ResponseModel<SysMenuBO> selectById(Long id) {
        final SysMenu sysMenu = sysMenuService.selectById(id);
        return successData(Builder.build(sysMenu, SysMenuBO.class));
    }

    @Override
    public ResponseModel<List<SysMenuBO>> findAll() {
        List<SysMenu> sysMenus = sysMenuService.findList(new SysMenu());
        return successData(Builder.buildList(sysMenus, SysMenuBO.class));
    }

    @Override
    public ResponseModel<List<SysMenuBO>> findByOperateId(Long id) {
        final List<SysMenu> sysMenu = sysMenuService.findByOperateId(id);
        return successData(Builder.buildList(sysMenu, SysMenuBO.class));
    }

    @Override
    public ResponseModel<List<SysMenuBO>> findByPidAndOperateId(Long pId, Long operateId) {
        final List<SysMenu> sysMenus = sysMenuService.findByPidAndOperateId(pId, operateId);
        return successData(Builder.buildList(sysMenus, SysMenuBO.class));
    }

    @Override
    public ResponseModel saveOrUpdate(@RequestBody SysMenuAO ao) {
        SysMenu menu = Builder.build(ao, SysMenu.class);
        sysMenuService.saveOrUpdate(menu);
        return success();
    }

    @Override
    public ResponseModel<String> findFatherIds(Long id) {
        String pIds = sysMenuService.findFatherIds(id);
        return successData(pIds);
    }
}
