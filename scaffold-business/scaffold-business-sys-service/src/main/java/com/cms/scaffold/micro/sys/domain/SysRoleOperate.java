package com.cms.scaffold.micro.sys.domain;

import com.cms.scaffold.common.annotation.TableName;
import com.cms.scaffold.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
*
* @author: Mybatis Generator
* @date: 2019-08-02 10:56:36
*/
@Getter
@Setter
@ToString
@TableName(name = "sys_role_operate")
public class SysRoleOperate extends BaseEntity {
    private Long operateId;

    private Long roleId;
}
