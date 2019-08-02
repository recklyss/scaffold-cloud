package com.cms.scaffold.route.operate.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.feign.sys.SysOperateFeign;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController extends BaseController {

    @Resource
    private SysOperateFeign sysOperateFeign;

    @PostMapping(value = "/login")
    public ResponseModel loginCheck(HttpServletRequest request, HttpServletResponse response, @RequestParam("username"
    ) String usernameOrEmail, @RequestParam("password") String password) {

        return success();
    }

    @PostMapping("/logout")
    public ResponseModel logout() {
        SecurityUtils.getSubject().logout();
        return success();
    }
}
