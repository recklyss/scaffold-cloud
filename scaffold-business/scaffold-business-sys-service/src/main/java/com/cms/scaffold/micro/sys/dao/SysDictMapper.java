package com.cms.scaffold.micro.sys.dao;

import com.cms.scaffold.common.base.BaseMapper;
import com.cms.scaffold.micro.sys.domain.SysDict;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysDictMapper extends BaseMapper<SysDict> {
    @Select("SELECT pid FROM sys_dict WHERE id = #{id}")
    Long findPid(@Param("id") Long id);

    @Select("select * from sys_dict where id in(select menu_id  from sys_role_menu  where role_id = (select  role_id from  " +
            "sys_role_operate  where operate_id =#{id})) and pid =#{pid};")
    List<SysDict> findSysDictByPidAndId(@Param("pid") Long pid, @Param("id") Long id);

    @Select("select * from sys_dict where pid=#{pid} order by sort asc")
    List<SysDict> listByPid(Long pid);

    List<SysDict> findByPartnerNid(@Param("nid") String nid);

    @Select("select * from sys_dict where nid = #{nid} and type=2 and status = 1 order by sort")
    List<SysDict> findByNid(@Param("nid") String nid);
}
