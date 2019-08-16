package com.cms.scaffold.route.operate.controller.sys;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.common.response.ResponsePageModel;
import com.cms.scaffold.feign.sys.SysRoleFeign;
import com.cms.scaffold.micro.sys.ao.SysRoleAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author zhangjiaheng
 * @Description 角色管理
 **/
@Controller
@RequestMapping("/sys/sysRole")
public class SysRoleController extends BaseController {
    private static final String ftlPath = "/sys/sysRole/";
    @Resource
    SysRoleFeign sysRoleFeign;

    @GetMapping("/sysRole")
    public String sysRoleIndex() {
        return ftlPath + "sysRoleIndex";
    }

    @GetMapping("/addRolePage")
    public String addRolePage(){
        return ftlPath + "addRolePage";
    }

    @RequestMapping("/listSysRolePage")
    @ResponseBody
    public ResponsePageModel listSysRolePage(SysRoleAO ao) {
        return sysRoleFeign.findPage(ao);
    }

    @ResponseBody
    @PostMapping("/saveRole")
    public ResponseModel saveRole(SysRoleAO sysRole){
        return sysRoleFeign.insert(sysRole);
    }

}
