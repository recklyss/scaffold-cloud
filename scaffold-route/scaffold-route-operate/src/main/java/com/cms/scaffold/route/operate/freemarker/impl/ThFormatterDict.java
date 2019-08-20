package com.cms.scaffold.route.operate.freemarker.impl;

import com.cms.scaffold.code.util.I18nTransformUtil;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.code.config.commonly.SpringContextHolder;
import com.cms.scaffold.feign.sys.SysDictFeign;
import com.cms.scaffold.micro.sys.bo.SysDictBO;
import com.cms.scaffold.route.operate.freemarker.factory.ThFormatterInterface;

import java.util.List;

/**
 * 字典表头工厂实现类
 *
 * @author zjh
 * @date 2018/7/2
 */
public class ThFormatterDict implements ThFormatterInterface {

    @Override
    public String buildFormatterHtml(String nid) {
        SysDictFeign sysDictFeign = SpringContextHolder.getBean(SysDictFeign.class);
        ResponseModel<List<SysDictBO>> sysDictModelList = sysDictFeign.findByPartnerNid(nid);
        List<SysDictBO> list = sysDictModelList.getData();
        I18nTransformUtil.transFormList(list, "name");
        StringBuilder dictHtml = new StringBuilder();
        dictHtml.append("formatter: function(value,row,index){ ");
        for (SysDictBO sysDict : list) {
            dictHtml.append("if(value == '" + sysDict.getValue() + "'){ return '" + sysDict.getName() + "';}");
        }
        dictHtml.append("}");

        return dictHtml.toString();
    }
}
