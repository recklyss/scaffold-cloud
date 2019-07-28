package com.cms.scaffold.plugin.mybatis.factory;

import com.cms.scaffold.plugin.mybatis.wrapper.MyMapWrapper;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import java.util.Map;


/**
 * Map 类型结果转驼峰
 */
public class MapWrapperFactory implements ObjectWrapperFactory {

    @Override
    public boolean hasWrapperFor(Object object) {
        return object != null && object instanceof Map;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        return new MyMapWrapper(metaObject, (Map) object);
    }

}
