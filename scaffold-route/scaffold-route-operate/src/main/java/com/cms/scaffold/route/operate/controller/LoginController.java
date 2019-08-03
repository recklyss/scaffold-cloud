package com.cms.scaffold.route.operate.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.feign.sys.SysOperateFeign;
import com.cms.scaffold.micro.sys.bo.SysOperateBO;
import com.cms.scaffold.route.operate.constant.SysConstants;
import com.cms.scaffold.route.operate.util.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 登录登出控制
 *
 * @author zjh
 */
@Controller
public class LoginController extends BaseController {

    @Resource
    private SysOperateFeign sysOperateFeign;

    @RequestMapping(value = "/loginPage")
    public String loginPage() {
        final Object principal = UserUtil.getPrincipal();
        if (null != principal) {
            final Subject subject = SecurityUtils.getSubject();
            if (subject.isRemembered()) {
                final SysOperateBO operator = UserUtil.getOperatorFromSession();
                if (null != operator) {
                    subject.getSession().setAttribute(SysConstants.SESSION_ATTRIBUTE_KEY_OPERATOR, operator);
                }
            }
            return "index";
        }
        return "login";
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public ResponseModel loginCheck(@RequestParam("username") String username,
                                    @RequestParam("password") String password) {
        final ResponseModel<SysOperateBO> responseModel = sysOperateFeign.findByUserName(username);
        final SysOperateBO operate = responseModel.getData();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            subject.getSession().setAttribute(SysConstants.SESSION_ATTRIBUTE_KEY_OPERATOR, operate);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return error();
        }
        return success();
    }

    @PostMapping("/logout")
    @ResponseBody
    public ResponseModel logout() {
        SecurityUtils.getSubject().logout();
        return success();
    }
}
