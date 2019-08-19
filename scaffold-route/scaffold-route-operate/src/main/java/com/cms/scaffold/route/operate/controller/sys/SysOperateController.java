package com.cms.scaffold.route.operate.controller.sys;

import cn.hutool.core.util.StrUtil;
import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.common.response.ResponsePageModel;
import com.cms.scaffold.common.util.MD5Util;
import com.cms.scaffold.feign.sys.SysOperateFeign;
import com.cms.scaffold.feign.sys.SysRoleFeign;
import com.cms.scaffold.feign.sys.SysRoleOperateFeign;
import com.cms.scaffold.micro.sys.ao.SysOperateAO;
import com.cms.scaffold.micro.sys.ao.SysRoleAO;
import com.cms.scaffold.micro.sys.bo.SysOperateBO;
import com.cms.scaffold.micro.sys.bo.SysRoleBO;
import com.cms.scaffold.micro.sys.bo.SysRoleOperateBO;
import com.cms.scaffold.route.operate.constant.SysConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhang
 */
@Controller
@RequestMapping("/sys/sysOperate")
public class SysOperateController extends BaseController {
    private static final String ftlPath = "/sys/sysOperate/";
    @Resource
    SysOperateFeign sysOperateFeign;
    @Resource
    SysRoleFeign sysRoleFeign;
    @Resource
    SysRoleOperateFeign sysRoleOperateFeign;

    @GetMapping("/sysOperateIndex")
    public String sysOperateIndex() {
        return ftlPath + "sysOperateIndex";
    }

    @GetMapping("/addOperatePage")
    public String addOperatePage(Model model) {
        SysRoleAO ao = new SysRoleAO();
        ao.setStatus(1L);
        ResponseModel<List<SysRoleBO>> resp = sysRoleFeign.findList(ao);
        model.addAttribute("list", resp.getData());
        return ftlPath + "addOperatePage";
    }

    @GetMapping("/editOperatePage")
    public String editOperatePage(Long id, Model model) {
        ResponseModel<SysOperateBO> operateResp = sysOperateFeign.selectById(id);
        SysRoleAO ao = new SysRoleAO();
        ao.setStatus(1L);
        ResponseModel<List<SysRoleBO>> resp = sysRoleFeign.findList(ao);
        ResponseModel<SysRoleOperateBO> sysRoleOperateResp = sysRoleOperateFeign.selectByOperateId(id);
        model.addAttribute("list", resp.getData());
        model.addAttribute("sysOperate", operateResp.getData());
        model.addAttribute("sysRoleOperate", sysRoleOperateResp.getData());
        return ftlPath + "editOperatePage";
    }

    @ResponseBody
    @PostMapping("/saveOperate")
    public ResponseModel addOperate(SysOperateAO request){
        request.setPwd(MD5Util.encode(request.getPwd()).toUpperCase());
        return sysOperateFeign.insert(request);
    }

    @ResponseBody
    @PostMapping("/updateOperate")
    public ResponseModel updateOperate(SysOperateAO request){
        request.setPwd(null);
        return sysOperateFeign.update(request);
    }

    @GetMapping("/editOperatePwdPage")
    public String editOperatePwd(Long operateId, Model model) {
        model.addAttribute("sysOperateId", operateId);
        return ftlPath + "editOperatePwdPage";
    }

    @ResponseBody
    @PostMapping("/operateList")
    public ResponsePageModel<SysOperateBO> operateList(SysOperateAO request) {
        return sysOperateFeign.findOperatePage(request);
    }

    @PostMapping("/updateOperatePwd")
    @ResponseBody
    public ResponseModel updateOperatePwd(Long id, String oldPassword1, String oldPassword2, String password) {
        if (!StrUtil.equals(oldPassword1, oldPassword2)) {
            return errorMessage("两次输入的密码不一致");
        }
        SysOperateBO sysOperateBO = sysOperateFeign.selectById(id).getData();
        if (!StrUtil.equals(MD5Util.encode(oldPassword1).toUpperCase(), sysOperateBO.getPwd())) {
            return errorMessage("原密码输入错误");
        }
        SysOperateAO ao = new SysOperateAO();
        ao.setId(id);
        ao.setPwd(MD5Util.encode(password).toUpperCase());
        return sysOperateFeign.update(ao);
    }

    @ResponseBody
    @RequestMapping("/resetPwd")
    public ResponseModel resetPwd(Long id){
        SysOperateAO ao = new SysOperateAO();
        ao.setId(id);
        ao.setPwd(MD5Util.encode(SysConstants.DEFAULT_PASSWORD).toUpperCase());
        return sysOperateFeign.update(ao);
    }


}
