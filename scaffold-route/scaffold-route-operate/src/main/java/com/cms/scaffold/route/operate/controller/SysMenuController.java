package com.cms.scaffold.route.operate.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.base.ResponseModel;
import com.cms.scaffold.route.operate.feigin.SysMenuFeign;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/sys/sysMenu")
public class SysMenuController extends BaseController {
    @Resource
    SysMenuFeign sysMenuFeign;

    @GetMapping("/listMenuByPid")
    @ResponseBody
    public ResponseModel listMenuByPid(Long pid){
        return sysMenuFeign.listMenuByPid(pid);
    }
}
