package com.cms.scaffold.micro.sys.ao;

import com.cms.scaffold.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
*
* @author: Mybatis Generator
* @date: 2019-07-29 19:36:45
*/
@Getter
@Setter
@ToString
public class SysRoleAO extends BaseEntity {
    /** 角色名**/
    private String name;

    private String remark;

    private Long status;
}
