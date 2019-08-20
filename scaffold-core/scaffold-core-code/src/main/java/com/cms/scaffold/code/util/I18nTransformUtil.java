package com.cms.scaffold.code.util;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.cms.scaffold.code.config.commonly.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author zhangjiaheng@gmail.com
 * @Description 国际化转换工具类
 **/
@Slf4j
public class I18nTransformUtil {

    /**
     * @param object    需要被转换的对象 需要有i18nNid属性 存储标识
     * @param fieldName 需要被转换的字段名
     * @param <T>       泛型
     * @return 原对象转换后
     */
    public static <T> T transForm(T object, String fieldName) {
        Object i18nId = ReflectUtil.getFieldValue(object, "i18nNid");
        MessageSource messageSource = SpringContextHolder.getBean("messageSource");
        if (Objects.nonNull(i18nId)) {
            String name = null;
            try {
                name = messageSource.getMessage(i18nId.toString(), null, LocaleContextHolder.getLocale());
                if (StrUtil.isNotBlank(name)) {
                    if(name.equals(i18nId)){
                        log.warn("i18n:{}对应的国际化配置在数据库不存在，请检查！", i18nId);
                    }else{
                        ReflectUtil.setFieldValue(object, fieldName, name);
                    }
                }else{
                    log.warn("获取的国际化属性{}不合法,i18nNid:{}，语言：{}", fieldName, i18nId, LocaleContextHolder.getLocale());
                }
            } catch (NoSuchMessageException e) {
                log.error("NoSuchMessageException=> 国际化属性{}不合法,i18nNid:{}，语言：{}", fieldName, i18nId, LocaleContextHolder.getLocale());
            }
        } else {
            log.warn("存在不合法的i18nId");
        }
        return object;
    }

    /**
     * 转换list
     */
    public static <T> List<T> transFormList(List<T> list, String fieldName) {
        if (list == null) {
            return null;
        }
        List<T> resultList = new ArrayList<>();

        for (T object : list) {
            resultList.add(transForm(object, fieldName));
        }
        return resultList;
    }

    /**
     * 根据model.name以及当前语言获取到对应的值
     * @param i18nName
     * @return
     */
    public static String transFormString(String i18nName){
        MessageSource messageSource = SpringContextHolder.getBean("messageSource");
        String source = messageSource.getMessage(i18nName, null, LocaleContextHolder.getLocale());
        if(source.equals(i18nName)){
            log.warn("i18n:{}对应的国际化配置在数据库不存在，请检查！", i18nName);
        }
        return source;
    }
}
