/**
 * @Title: BaseService.java
 * @Package com.cms.scaffold.common.service
 * TODO:TODO
 * @author zjh
 * @date 2017-6-24
 */
package com.cms.scaffold.common.base;

import com.cms.scaffold.common.response.ResponsePageModel;

import java.io.Serializable;
import java.util.List;


/**
 * TODO:Service支持类实现
 *
 * @author zjh
 * @date 2017-6-24
 */
public interface BaseService<T extends BaseEntity> {

    /**
     * 通过ID查询
     *
     * @param id
     * @return
     */
    T selectById(Serializable id);

    /**
     * 通过ID查询
     *
     * @param id
     * @return
     */
    T selectLockById(Serializable id);

    /**
     * 查询单条记录
     *
     * @param record
     * @return
     */
    T selectOne(T record);

    /**
     * 查询记录集合
     *
     * @param record
     * @return
     */
    List<T> findList(T record);

    /**
     * 分页查询
     *
     * @param record
     * @return
     */
    ResponsePageModel<T> findPage(T record);

    /**
     * 插入记录
     *
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * 批量保存
     *
     * @param list
     */
    int batchInsert(List<T> list);

    /**
     * 通用的修改方法
     *
     * @param record
     */
    int update(T record);

    /**
     * 通用的修改方法
     *
     * @param record
     */
    int update(T record, T oldRecord);

    /**
     * 批量更新
     *
     * @param list
     * @return
     */
    int batchUpdate(List<T> list);

    /**
     * 通用的全部修改方法
     *
     * @param record
     */
    int updateAll(T record);


    /**
     * 批量删除
     *
     * @param list
     * @return
     */
    int delList(List<T> list);

    /**
     * 批量删除方法
     *
     * @param ids
     */
    int delArray(Long[] ids);

    /**
     * 统计查询
     *
     * @param record 查询参数
     * @return 总记录条数
     */
    int count(T record);

    int batchUpdate(List<T> list, List<T> listOld);


}
