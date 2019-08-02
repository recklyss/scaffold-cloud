package com.cms.scaffold.route.operate.controller.sys;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.feign.sys.SysMenuFeign;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author zhangjiaheng
 */
@Controller
@RequestMapping("/sys/sysMenu")
public class SysMenuController extends BaseController {

    private static final String ftlPath = "/sys/sysMenu/";

    @Resource
    SysMenuFeign sysMenuFeign;

    @GetMapping("/sysMenuIndex")
    public String sysMenuIndex(){
        return ftlPath + "sysMenuIndex";
    }

    @GetMapping("/listMenuByPid")
    @ResponseBody
    public ResponseModel listMenuByPid(Long pid){
        return sysMenuFeign.listMenuByPid(pid);
    }

    @GetMapping("/selectById")
    @ResponseBody
    public ResponseModel selectById(Long id){
        return sysMenuFeign.selectById(id);
    }
}
