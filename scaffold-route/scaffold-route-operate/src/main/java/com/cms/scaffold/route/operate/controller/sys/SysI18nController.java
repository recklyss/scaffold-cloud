package com.cms.scaffold.route.operate.controller.sys;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.common.response.ResponsePageModel;
import com.cms.scaffold.feign.sys.SysI18nFeign;
import com.cms.scaffold.micro.sys.ao.SysI18nAO;
import com.cms.scaffold.micro.sys.bo.SysI18nBO;
import com.cms.scaffold.route.operate.service.sys.MyMessageSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author zhang
 * 国际化配置管理
 */
@Controller
@RequestMapping("/sys/sysI18n")
@Slf4j
public class SysI18nController extends BaseController {
    private static final String FTL_PATH = "/sys/sysI18n/";
    @Resource
    SysI18nFeign sysI18nFeign;

    @Resource
    MyMessageSource messageSource;

    @GetMapping("/sysI18nIndex")
    public String sysI18nIndex(){
        return FTL_PATH + "sysI18nIndex";
    }

    @GetMapping("/sysI18nEdit")
    public String editSysI18nIndex(Long id, Model model){
        SysI18nBO sysI18nBO = new SysI18nBO();
        if (null != id){
            sysI18nBO = sysI18nFeign.selectById(id).getData();
        }
        model.addAttribute("sysI18n", sysI18nBO);
        return FTL_PATH + "editSysI18nIndex";
    }

    @ResponseBody
    @RequestMapping("/listSysI18nPage")
    public ResponsePageModel<SysI18nBO> listSysI18nPage(SysI18nAO ao){
        return sysI18nFeign.findPage(ao);
    }

    @RequestMapping("/sysI18nSave")
    @ResponseBody
    public ResponseModel sysI18nSave(SysI18nAO ao){
        ResponseModel responseModel = sysI18nFeign.save(ao);
        messageSource.reload();
        return success();
    }

}
