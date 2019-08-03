package com.cms.scaffold.route.operate.response;

import com.cms.scaffold.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: zjh
 * @date: 2019-03-12 23:38
 **/
@Setter
@Getter
public class SysMenuResp extends BaseEntity {

    /**
     * 菜单名称
     **/
    private String name;

    /**
     * 中文对应的国际化标识ID
     **/
    private String i18nNid;

    /**
     * 父级ID
     **/
    private Long pid;

    /**
     * 等级
     **/
    private Integer levelId;

    /**
     * 链接地址
     **/
    private String url;

    /**
     * 图标
     **/
    private String iconCls;

    /**
     * 状态
     **/
    private Integer status;

    /**
     * 排序
     **/
    private Integer sort;

    /**
     * 代码
     **/
    private String code;

    /**
     * 是否可展开
     **/
    private String state;

    /**
     * 资源类型
     **/
    private String resourceType;

    /**
     * 修改用户
     **/
    private String updateUser;

    /**
     * 授权状态
     */
    private Integer autoStatus;
    /**
     * 菜单名称 别名 前端用到
     */
    private String text;

    /**
     * 菜单ID 别名
     */
    private Long uuid;

    public String getText(){
        return name;
    }

    public Long getUuid() {
        return id;
    }
}
