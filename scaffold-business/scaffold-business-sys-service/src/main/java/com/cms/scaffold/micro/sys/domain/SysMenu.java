package com.cms.scaffold.micro.sys.domain;

import com.cms.scaffold.common.annotation.TableName;
import com.cms.scaffold.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
* 
* @author: Mybatis Generator
* @date: 2019-03-28 19:25:53
*/
@Getter
@Setter
@TableName(name = "sys_menu")
public class SysMenu extends BaseEntity {
    /** 菜单名称**/
    private String name;

    /** 中文对应的国际化标识ID**/
    private String i18nNid;

    /** 父级ID**/
    private Long pid;

    /** 等级**/
    private Integer levelId;

    /** 链接地址**/
    private String url;

    /** 图标**/
    private String iconCls;

    /** 状态**/
    private Integer status;

    /** 排序**/
    private Integer sort;

    /** 代码**/
    private String code;

    /** 是否可展开**/
    private String state;

    /** 资源类型**/
    private String resourceType;

    /** 添加用户**/
    private String addUser;

    /** 修改用户**/
    private String updateUser;

    /** 备注**/
    private String remark;
}