package com.cms.scaffold.common.base;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


/**
 * 支持类
 * 数据库实体类基类
 *
 * @author zjh
 */
@Getter
@Setter
public class BaseEntity implements Serializable {

    /**
     * id
     */
    protected Long id;

    /**
     * 插入时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 页数
     */
    private Integer page = 1;

    /**
     * 行数
     */
    private Integer rows = 20;

    /**
     * 创建人员
     **/
    private Long addOperate;

    /**
     * 更新人员
     **/
    private Long updateOperate;

    public void preInsert() {
        this.addTime = new Date();
        this.updateTime = new Date();
    }

    public void preUpdate() {
        this.updateTime = new Date();
    }
}
