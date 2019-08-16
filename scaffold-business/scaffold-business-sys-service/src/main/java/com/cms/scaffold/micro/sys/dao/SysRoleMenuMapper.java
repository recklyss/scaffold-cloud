package com.cms.scaffold.micro.sys.dao;

import com.cms.scaffold.common.base.BaseMapper;
import com.cms.scaffold.micro.sys.domain.SysRoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Administrator
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
    @Select("select * from sys_role_menu where role_id =#{roleId}")
    List<SysRoleMenu> findByRoleId(Long ruleId);

    @Select("select * from sys_role_menu where role_id=#{roleId} and menu_id=#{menuId}")
    SysRoleMenu queryByRoleIdAndMenuId(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    @Delete("delete from sys_role_menu where  menu_id = #{menuId} and role_id=#{roleId};")
    int deleteMenu(@Param("menuId") String menuId, @Param("roleId") Long roleId);
}
