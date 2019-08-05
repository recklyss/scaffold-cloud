package com.cms.scaffold.route.operate.controller.sys;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.feign.sys.SysDictFeign;
import com.cms.scaffold.micro.sys.bo.SysDictBO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author zhangjiaheng
 * @Description 字典管理
 **/
@Controller
@RequestMapping("/sys/sysDict")
public class SysDictController extends BaseController {

    private static final String ftlPath = "/sys/sysDict/";
    @Resource
    SysDictFeign sysDictFeign;

    @GetMapping("/sysDictIndex")
    public String sysDictIndex(){
        return ftlPath + "sysDictIndex";
    }

    @RequestMapping("/findSysDictByPid")
    @ResponseBody
    public List<SysDictBO> findSysDictByPid(Long parentId){
        return sysDictFeign.findSysDictByPid(parentId).getData();
    }
}
