package com.cms.scaffold.plugin.dict;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by zhangjiahengpoping@gmail.com on 2018/4/15.
 */
@Getter
@Setter
public class SysDict {

    private Long id;
    /** 名称**/
    private String name;

    /** 唯一标识**/
    private String nid;

    /** 父级id**/
    private Long pid;

    /** 值**/
    private String value;

    /** 代码 **/
    private String code;

    /** java类型 **/
    private String javaType;

    /** 类型，1目录 2值**/
    private Integer type;

    /** 排序**/
    private Integer sort;

    /** 状态，1可用 2不可用**/
    private Integer status;

    /** 备注**/
    private String remark;

    private List<SysDict> list;
}
