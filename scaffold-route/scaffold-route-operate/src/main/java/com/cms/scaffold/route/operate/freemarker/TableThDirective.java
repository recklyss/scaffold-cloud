package com.cms.scaffold.route.operate.freemarker;

import cn.hutool.core.util.StrUtil;
import com.cms.scaffold.code.util.I18nTransformUtil;
import com.cms.scaffold.route.operate.freemarker.factory.ThFormatterFactory;
import com.cms.scaffold.route.operate.freemarker.factory.ThFormatterInterface;
import com.cms.scaffold.route.operate.freemarker.model.TableThTag;
import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 *
 * @author zjh
 * @date 2018/4/16
 */

@Component
@org.springframework.context.annotation.Configuration
@Slf4j
public class TableThDirective implements TemplateDirectiveModel {


    /**
     * select下拉框FreeMarker自定义指令
     * @param environment
     * @param map
     * @param templateModels
     * @param templateDirectiveBody
     * @throws TemplateException
     * @throws IOException
     */
    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels,
                        TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException{
        TableThTag tableThTag = new TableThTag();

        //校验参数
        try {
            BeanUtils.populate(tableThTag,map);
            if(StringUtils.isEmpty(tableThTag.getNid()) && StringUtils.isEmpty(tableThTag.getType())){
                throw new IllegalArgumentException("nid,type必须有一个不为空");
            }
        } catch (Exception e) {
            log.error("数据转化异常",e);
        }


        StringBuilder html  =  new StringBuilder();
        String title = tableThTag.getTitle();
        String i18n = tableThTag.getI18n();
        if(StrUtil.isNotBlank(i18n)){
            String tmpTitle = I18nTransformUtil.transFormString(i18n);
            if(StrUtil.isNotBlank(tmpTitle)){
                title = tmpTitle;
            }
        }
        html.append("\t\t\t\t\t<th data-options=\"field:'").append(tableThTag.getField()).append("'").append(",title:'").append(title).append("',");
        if(!StringUtils.isEmpty(tableThTag.getWidth())){
            html.append("width:'").append(tableThTag.getWidth()).append("',");
        }

        ThFormatterInterface thFormatterInterface = ThFormatterFactory.createThFormatter(tableThTag.getType());
        if(thFormatterInterface != null){
            html.append("dict:'").append(tableThTag.getNid()).append("',");
            String dictHtml = thFormatterInterface.buildFormatterHtml(tableThTag.getNid());
            html.append(dictHtml);
        }

        html.append("\"></th>\n");
        // 执行真正指令的执行部分:
        Writer out = environment.getOut();
        out.write(html.toString());

        if(templateDirectiveBody !=null){
            templateDirectiveBody.render(environment.getOut());
        }

    }


    public static BeansWrapper getBeansWrapper(){
        BeansWrapper beansWrapper =
                new BeansWrapperBuilder(Configuration.VERSION_2_3_21).build();
        return beansWrapper;
    }

}
