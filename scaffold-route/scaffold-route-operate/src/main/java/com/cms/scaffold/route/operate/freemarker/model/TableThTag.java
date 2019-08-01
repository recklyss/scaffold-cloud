package com.cms.scaffold.route.operate.freemarker.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author zjh
 * @date 2018/4/16
 */
@Getter
@Setter
public class TableThTag {
    /**
     * 列
     */
    private String field;
    /**
     * 标题
     */
    private String title;
    /**
     * 宽度
     */
    private String width;
    /**
     * 字段分类
     */
    private String nid;

    /**
     * 类型
     */
    private String type;

    /**
    * 国际化对应的标识 如 sys.name
    * */
    private String i18n;
}

