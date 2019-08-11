package com.cms.scaffold.route.operate.controller.sys;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.common.response.ResponsePageModel;
import com.cms.scaffold.feign.sys.SysOperateFeign;
import com.cms.scaffold.feign.sys.SysRoleFeign;
import com.cms.scaffold.feign.sys.SysRoleOperateFeign;
import com.cms.scaffold.micro.sys.ao.SysOperateAO;
import com.cms.scaffold.micro.sys.ao.SysRoleAO;
import com.cms.scaffold.micro.sys.bo.SysOperateBO;
import com.cms.scaffold.micro.sys.bo.SysRoleBO;
import com.cms.scaffold.micro.sys.bo.SysRoleOperateBO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String sysOperateIndex(){
        return ftlPath + "sysOperateIndex";
    }

    @GetMapping("/addOperatePage")
    public String addOperatePage(Model model){
        SysRoleAO ao = new SysRoleAO();
        ao.setStatus(1L);
        ResponseModel<List<SysRoleBO>> resp =  sysRoleFeign.findList(ao);
        model.addAttribute("list", resp.getData());
        return ftlPath + "addOperatePage";
    }

    @GetMapping("/editOperatePage")
    public String editOperatePage(Long id, Model model){
        ResponseModel<SysOperateBO> operateResp =  sysOperateFeign.selectById(id);
        SysRoleAO ao = new SysRoleAO();
        ao.setStatus(1L);
        ResponseModel<List<SysRoleBO>> resp =  sysRoleFeign.findList(ao);
        ResponseModel<SysRoleOperateBO> sysRoleOperateResp = sysRoleOperateFeign.selectByOperateId(id);
        model.addAttribute("list", resp.getData());
        model.addAttribute("sysOperate", operateResp.getData());
        model.addAttribute("sysRoleOperate", sysRoleOperateResp.getData());
        return ftlPath + "editOperatePage";
    }

    @GetMapping("/editOperatePwdPage")
    public String editOperatePwd(Long operateId, Model model) {
        model.addAttribute("sysOperateId", operateId);
        return ftlPath + "editOperatePwdPage";
    }

    @ResponseBody
    @PostMapping("/operateList")
    public ResponsePageModel<SysOperateBO> operateList(SysOperateAO request){
        return sysOperateFeign.findOperatePage(request);
    }

    @PostMapping("/updateOperatePwd")
    @ResponseBody
    public ResponseModel updateOperatePwd(Long id, String password){

        return success();
    }


}
