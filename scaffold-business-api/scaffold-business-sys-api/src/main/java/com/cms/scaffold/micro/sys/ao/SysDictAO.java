package com.cms.scaffold.micro.sys.ao;

import com.cms.scaffold.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
*
* @author: Mybatis Generator
* @date: 2019-08-01 16:36:55
*/
@Getter
@Setter
@ToString
public class SysDictAO extends BaseEntity {

    /** 名称**/
    private String name;

    /** 中文对应的国际化标识NID**/
    private String i18nNid;

    /** 唯一标识**/
    private String nid;

    /** 父级id**/
    private Long pid;

    /** 值**/
    private String value;

    /** 类型  (详情见dict表basics_dict_type) **/
    private Long type;

    /** 代码**/
    private String code;

    /** java类型 (详情见dict表basics_java_type)**/
    private String javaType;

    /** 排序**/
    private Long sort;

    /** 状态，(详情见dict表basics_use_status) **/
    private Long status;

    /** 备注**/
    private String remark;

    /** 关联字典id**/
    private String relateId;
}
