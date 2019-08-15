
package com.cms.scaffold.common.builder;

import cn.hutool.core.util.ReflectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 属性拷贝工具类
 *
 * @author Administrator
 */
public class Builder<T, M> {

    private static Logger logger = LoggerFactory.getLogger(Builder.class);

    public static <T, M> List<T> buildList(List<M> list, Class<T> clazz) {

        return buildList(list, clazz, null);
    }

    public static <T, M> T build(M object, Class<T> clazz) {
        return build(object, clazz, null);
    }

    public static <T, M> List<T> buildList(List<M> list, Class<T> clazz, Map map) {
        if (list == null) {
            return null;
        }
        List<T> resultList = new ArrayList<>();

        for (M object : list) {
            resultList.add(build(object, clazz, map));
        }
        return resultList;
    }

    public static <T, M> T build(M object, Class<T> clazz, Map map) {
        try {
            T t = clazz.newInstance();
            if (object == null) {
                return null;
            }
            // 使用cglib.BeanCopier完成对象属性的拷贝
            final BeanCopier copier = BeanCopier.create(object.getClass(), clazz, false);
            copier.copy(object, t, null);

            //判断是否需要进行转义
            if (map == null) {
                return t;
            }

            //进行转义
            Iterator<Map.Entry<String, Map>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Map> entry = it.next();
                //需转义字段名
                String key = entry.getKey();
                //转义map
                Map valueMap = entry.getValue();
                //获取源转义字段数据
                Object destValue = ReflectUtil.getFieldValue(t, key);
                //设置值
                ReflectUtil.setFieldValue(t, key, valueMap.get(String.valueOf(destValue)));
            }

            return t;
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            //do nothing
        }
        return null;
    }

}
