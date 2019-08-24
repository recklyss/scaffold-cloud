package com.cms.scaffold.route.operate.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
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

    private static final Log logger = LogFactory.get(LoginController.class);
    @Resource
    private SysOperateFeign sysOperateFeign;

    @RequestMapping(value = "/login")
    public String login() {
        final Object principal = UserUtil.getPrincipal();
        if (null != principal) {
            final Subject subject = SecurityUtils.getSubject();
            if (subject.isRemembered()) {
                final SysOperateBO operator = UserUtil.getOperatorFromSession();
                if (null != operator && null != operator.getId()) {
                    logger.info("当前登陆用户为：{}， 跳转到Index", operator.getRealName());
                    subject.getSession().setAttribute(SysConstants.SESSION_ATTRIBUTE_KEY_OPERATOR, operator);
                }
            }
            return "redirect:index";
        }
        return "login";
    }

    @PostMapping(value = "/login/check")
    @ResponseBody
    public ResponseModel loginCheck(@RequestParam("username") String username,
                                    @RequestParam("password") String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            logger.error("登陆失败：{}", e);
            return errorMessage("用户名或者密码错误", "操作失败");
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
