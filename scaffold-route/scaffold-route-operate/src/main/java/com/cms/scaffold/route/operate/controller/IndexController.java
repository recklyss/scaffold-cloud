package com.cms.scaffold.route.operate.controller;

import com.cms.scaffold.code.util.I18nTransformUtil;
import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.feign.sys.SysMenuFeign;
import com.cms.scaffold.micro.sys.bo.SysMenuBO;
import com.cms.scaffold.micro.sys.bo.SysOperateBO;
import com.cms.scaffold.route.operate.util.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author zhangjiaheng
 * @Description
 **/
@Controller
public class IndexController extends BaseController {
    @Resource
    SysMenuFeign sysMenuFeign;

    @GetMapping("/index")
    public String index(Model model){
        SysOperateBO operate = UserUtil.getOperatorFromSession();
        if (null == operate.getId()) {
            return "login";
        }
        final ResponseModel<List<SysMenuBO>> responseModel = sysMenuFeign.findByPidAndOperateId(1L, operate.getId());
        final List<SysMenuBO> data = responseModel.getData();
        I18nTransformUtil.transFormList(data, "name");
        model.addAttribute("menus", data);
        model.addAttribute("sysOperate", operate);
        return "index";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
