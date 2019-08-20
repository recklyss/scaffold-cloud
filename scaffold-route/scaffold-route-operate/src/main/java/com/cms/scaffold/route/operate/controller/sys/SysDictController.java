package com.cms.scaffold.route.operate.controller.sys;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cms.scaffold.code.util.I18nTransformUtil;
import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.base.BaseStatusCode;
import com.cms.scaffold.common.builder.Builder;
import com.cms.scaffold.common.exception.BusinessException;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.feign.sys.SysDictFeign;
import com.cms.scaffold.micro.sys.ao.SysDictAO;
import com.cms.scaffold.micro.sys.bo.SysDictBO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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

    @RequestMapping("/sysDictEdit")
    public String sysDictEdit(SysDictAO dict, Model model){
        SysDictBO tempSysDict = null;
        SysDictBO partnerDict = null;

        if (dict.getId() == null) {
            tempSysDict = Builder.build(dict, SysDictBO.class);
            if (dict.getPid() == null) {
                throw new BusinessException(BaseStatusCode.FAIL.getCode(), "需要选择上级目录");
            }
            partnerDict = sysDictFeign.selectById(dict.getPid()).getData();
        } else {
            tempSysDict = sysDictFeign.selectById(dict.getId()).getData();
            partnerDict = sysDictFeign.selectById(tempSysDict.getPid()).getData();
            if (StrUtil.isNotBlank(partnerDict.getNid())) {
                tempSysDict.setNid(tempSysDict.getNid().replace(partnerDict.getNid() + "_", ""));
            }
        }
        model.addAttribute("sysDict", tempSysDict);
        model.addAttribute("partnerDict", partnerDict);
        return ftlPath + "sysDictEdit";
    }

    @RequestMapping("/findSysDictByPid")
    @ResponseBody
    public List<SysDictBO> findSysDictByPid(Long parentId){
        List<SysDictBO> data = sysDictFeign.findSysDictByPid(parentId).getData();
        I18nTransformUtil.transFormList(data, "name");
        return data;
    }

    @RequestMapping("/sysDictSave")
    @ResponseBody
    public ResponseModel sysDictSaveValid(SysDictAO dict) {
        return sysDictFeign.save(dict);
    }

    @RequestMapping(value = "/findDictValues")
    @ResponseBody
    public JSONArray findDictValues(String selectName, String q) {
        JSONArray jsonArray = new JSONArray();
        if (StrUtil.isNotBlank(selectName)) {
            List<SysDictBO> sysDicts = sysDictFeign.findByPartnerNid(selectName).getData();
            // 转换国际化字段
            I18nTransformUtil.transFormList(sysDicts, "name");
            if (CollectionUtils.isNotEmpty(sysDicts)) {
                if (StrUtil.isNotBlank(q)) {
                    sysDicts = sysDicts.stream().filter(sysDict -> sysDict.getName().contains(q)).collect(Collectors.toList());
                }
                sysDicts.forEach(sysDict -> {
                    JSONObject object = new JSONObject();
                    object.put("text", sysDict.getName());
                    object.put("value", sysDict.getValue());
                    jsonArray.add(object);
                });
            }
        }
        return jsonArray;
    }
    /**
     * 根据id查询所有父类id
     *
     * @param id
     * @return
     */
    @RequestMapping("/findFatherIds")
    @ResponseBody
    public String findFatherIds(Long id) {
        return sysDictFeign.findFatherIds(id).getData();
    }

}
