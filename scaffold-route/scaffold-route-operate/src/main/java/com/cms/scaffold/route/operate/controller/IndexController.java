package com.cms.scaffold.route.operate.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.feign.sys.SysMenuFeign;
import com.cms.scaffold.micro.sys.bo.SysOperateBO;
import com.cms.scaffold.route.operate.util.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * @Author zhangjiaheng
 * @Description
 **/
@Controller
public class IndexController extends BaseController {
    @Resource
    SysMenuFeign sysMenuFeign;

    @GetMapping("/index")
    public String index(){
        SysOperateBO operate = UserUtil.getOperatorFromSession();
        return "index";
    }
}
