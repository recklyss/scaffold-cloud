package com.cms.scaffold.plugin.dict;

import java.util.List;

/**
 * Created by zhangjiahengpoping@gmail.com on 2018/4/15.
 */
public class SysDict {

    private Long id;
    /** 名称**/
    private String name;

    /** 唯一标识**/
    private String nid;

    /** 父级id**/
    private Long pid;

    /** 值**/
    private String value;

    /** 代码 **/
    private String code;

    /** java类型 **/
    private String javaType;

    /** 类型，1目录 2值**/
    private Integer type;

    /** 排序**/
    private Integer sort;

    /** 状态，1可用 2不可用**/
    private Integer status;

    /** 备注**/
    private String remark;

    private List<SysDict> list;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<SysDict> getList() {
        return list;
    }

    public void setList(List<SysDict> list) {
        this.list = list;
    }
}
