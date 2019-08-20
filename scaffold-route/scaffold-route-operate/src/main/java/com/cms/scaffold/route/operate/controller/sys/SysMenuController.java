package com.cms.scaffold.route.operate.controller.sys;

import com.cms.scaffold.code.util.I18nTransformUtil;
import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.builder.Builder;
import com.cms.scaffold.common.constant.BasicsConstant;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.feign.sys.SysMenuFeign;
import com.cms.scaffold.feign.sys.SysOperateFeign;
import com.cms.scaffold.feign.sys.SysRoleFeign;
import com.cms.scaffold.feign.sys.SysRoleMenuFeign;
import com.cms.scaffold.micro.sys.ao.SysMenuAO;
import com.cms.scaffold.micro.sys.bo.SysMenuBO;
import com.cms.scaffold.micro.sys.bo.SysRoleBO;
import com.cms.scaffold.micro.sys.bo.SysRoleMenuBO;
import com.cms.scaffold.route.operate.response.SysMenuResp;
import com.cms.scaffold.route.operate.shiro.ShiroService;
import com.cms.scaffold.route.operate.util.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjiaheng
 */
@Controller
@RequestMapping("/sys/sysMenu")
public class SysMenuController extends BaseController {

    private static final String ftlPath = "/sys/sysMenu/";

    @Resource
    SysMenuFeign sysMenuFeign;
    @Resource
    SysOperateFeign sysOperateFeign;
    @Resource
    SysRoleFeign sysRoleFeign;
    @Resource
    ShiroService shiroService;
    @Resource
    SysRoleMenuFeign sysRoleMenuFeign;

    @GetMapping("/sysMenuIndex")
    public String sysMenuIndex() {
        return ftlPath + "sysMenuIndex";
    }

    @GetMapping("/sysMenuEdit")
    public String SysMenuEdit(Model model, SysMenuAO sysMenu) {
        SysMenuResp sysMenuResp;
        if (null == sysMenu.getId()) {
            sysMenuResp = Builder.build(sysMenu, SysMenuResp.class);
        } else {
            ResponseModel<SysMenuBO> resp = sysMenuFeign.selectById(sysMenu.getId());
            sysMenuResp = Builder.build(resp.getData(), SysMenuResp.class);
        }
        model.addAttribute("sysMenu", sysMenuResp);
        return ftlPath + "sysMenuEdit";
    }

    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public ResponseModel saveOrUpdate(SysMenuAO menu) {
        ResponseModel resp = sysMenuFeign.saveOrUpdate(menu);
        shiroService.updatePermission();
        return resp;
    }

    @RequestMapping("/findSysMenuByPid")
    @ResponseBody
    public List<SysMenuBO> listMenuByPid(Long parentId) {
        List<SysMenuBO> data = sysMenuFeign.listMenuByPid(parentId).getData();
        I18nTransformUtil.transFormList(data, "name");
        return data;
    }

    @GetMapping("/selectById")
    @ResponseBody
    public ResponseModel selectById(Long id) {
        return sysMenuFeign.selectById(id);
    }

    @RequestMapping("/findFatherIds")
    @ResponseBody
    public String findFatherIds(Long id) {
        ResponseModel<String> resp = sysMenuFeign.findFatherIds(id);
        return resp.getData();
    }

    @GetMapping("/rightMenus")
    @ResponseBody
    public List<SysMenuResp> rightMenus(Long pid) {
        List<SysMenuBO> menuBOS = sysMenuFeign.findByPidAndOperateId(pid, UserUtil.getOperatorId()).getData();
        I18nTransformUtil.transFormList(menuBOS, "name");
        return Builder.buildList(menuBOS, SysMenuResp.class);
    }

    @RequestMapping(value = "/allMenus")
    @ResponseBody
    public List<SysMenuResp> allMenus(Long id, Long roleId) {
        if (id == null) {
            id = 0L;
        }
        if (roleId == null) {
            return new ArrayList<>();
        }
        List<SysMenuBO> voList = sysMenuFeign.listMenuByPid(id).getData();
        List<SysMenuResp> sysMenuRespList = Builder.buildList(voList, SysMenuResp.class);
        I18nTransformUtil.transFormList(sysMenuRespList, "name");
        final SysRoleBO sysRole = sysRoleFeign.selectById(roleId).getData();
        for (SysMenuResp sysMenu : sysMenuRespList) {
            SysRoleMenuBO sysRoleMenu = sysRoleMenuFeign.selectByRoleIdAndMenuId(sysRole.getId(), sysMenu.getId()).getData();
            if (sysRoleMenu != null) {
                sysMenu.setAutoStatus(BasicsConstant.BASICS_USE_STATUS_ENABLE);
            } else {
                sysMenu.setAutoStatus(BasicsConstant.BASICS_USE_STATUS_DISABLE);
            }
        }
        return sysMenuRespList;
    }
}
