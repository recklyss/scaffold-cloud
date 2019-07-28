package com.cms.scaffold.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @description:
 * @author: zhangjiahengpoping@gmail.com
 * @date: 2019-02-27 20:41
 **/
@Retention(RetentionPolicy.RUNTIME)
public @interface TableName {

    // 表名称
    String name();

    // 主键名称,默认id
    String id() default "id";

    // 主键类型

    Class idType() default String.class;

    // 映射的实体名称
    Class type() default Void.class;
}
