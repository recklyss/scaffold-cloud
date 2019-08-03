package com.cms.scaffold.micro.sys.dao;

import com.cms.scaffold.common.base.BaseMapper;
import com.cms.scaffold.micro.sys.domain.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Administrator
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    @Select("SELECT pid FROM sys_menu WHERE id = #{id}")
    Long findPids(@Param("id") Long id);

    @Select("select * from sys_menu where id in(select menu_id  from sys_role_menu  where role_id = (select  role_id from  sys_role_operate  where operate_id =#{id})) and pid =#{pid} and status=1 order by sort ")
    List<SysMenu> findByPidAndOperateId(@Param("pid") Long pid, @Param("id") Long id);

    @Select("select * from sys_menu where pid=#{pid}")
    List<SysMenu> selectByPid(Long pid);

    @Select("select * from sys_menu where id in(select menu_id  from sys_role_menu  where role_id = (select  role_id from  sys_role_operate  where operate_id =#{id}))")
    List<SysMenu> findByOperateId(@Param("id") Long id);

    @Select("select pid from sys_menu where id=#{id}")
    Long findPid(@Param("id") Long id);
}
