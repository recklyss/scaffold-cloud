package com.cms.scaffold.route.operate.controller.sys;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.feign.sys.SysMenuFeign;
import com.cms.scaffold.feign.sys.SysRoleMenuFeign;
import com.cms.scaffold.micro.sys.ao.SysRoleMenuAO;
import com.cms.scaffold.route.operate.shiro.MyShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author zhangjiaheng
 * @Description 角色授权管理
 **/
@Controller
@RequestMapping("/sys/sysRoleMenu")
public class SysRoleMenuController extends BaseController {
    private static final String ftlPath = "/sys/sysRoleMenu/";
    @Resource
    SysRoleMenuFeign sysRoleMenuFeign;
    @Resource
    SysMenuFeign sysMenuFeign;

    /**
     * 取消角色的菜单授权
     *
     * @param uuid   菜单ID英文逗号分隔
     * @param roleId 角色ID
     * @return
     */
    @RequestMapping(value = "/deleteMenu")
    @ResponseBody
    public ResponseModel deleteMenu(String uuid, Long roleId) {
        uuid = uuid.replaceAll("'", "");
        return sysRoleMenuFeign.deleteMenu(uuid, roleId);
    }

    /**
     * 给角色授权菜单
     *
     * @param uuid
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/addRoleMenu")
    @ResponseBody
    public ResponseModel addRoleMenu(String uuid, Long roleId) {
        uuid = uuid.replaceAll("'", "");
        final List<Long> menuIds = Arrays.stream(uuid.split(",")).map(Long::parseLong).collect(Collectors.toList());
        List<SysRoleMenuAO> list = new ArrayList<>();
        menuIds.forEach(o -> {
            final SysRoleMenuAO roleMenuAO = new SysRoleMenuAO();
            roleMenuAO.setMenuId(o);
            roleMenuAO.setRoleId(roleId);
            list.add(roleMenuAO);
        });
        ResponseModel resp = sysRoleMenuFeign.addRoleMenu(list);

        //重新刷新shrio中的url 和菜单权限
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroRealm realm = (MyShiroRealm) rsm.getRealms().iterator().next();
        realm.clearCachedAuthorization();
        return resp;
    }
}
