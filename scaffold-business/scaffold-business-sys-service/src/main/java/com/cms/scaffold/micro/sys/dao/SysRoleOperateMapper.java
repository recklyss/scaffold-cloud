package com.cms.scaffold.micro.sys.dao;

import com.cms.scaffold.common.base.BaseMapper;
import com.cms.scaffold.micro.sys.domain.SysRoleOperate;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Administrator
 */
public interface SysRoleOperateMapper extends BaseMapper<SysRoleOperate> {

    @Select("select * from sys_role_operate where operate_id =#{operateId}")
    SysRoleOperate selectByOperateId(@Param("operateId") Long operateId);
}
