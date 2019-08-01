package com.cms.scaffold.micro.sys.domain;

import com.cms.scaffold.common.annotation.TableName;
import com.cms.scaffold.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
*
* @author: Mybatis Generator
* @date: 2019-08-01 19:57:21
*/
@Getter
@Setter
@ToString
@TableName(name = "sys_role_menu")
public class SysRoleMenu extends BaseEntity {
    /** 角色id**/
    private Long roleId;

    /** 菜单id**/
    private Long menuId;
}