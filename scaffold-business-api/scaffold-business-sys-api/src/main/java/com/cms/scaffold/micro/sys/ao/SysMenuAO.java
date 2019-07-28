package com.cms.scaffold.micro.sys.ao;

import com.cms.scaffold.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 增加AO模块
 * 用于查询参数的传递
 */
@Getter
@Setter
public class SysMenuAO extends BaseEntity {
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