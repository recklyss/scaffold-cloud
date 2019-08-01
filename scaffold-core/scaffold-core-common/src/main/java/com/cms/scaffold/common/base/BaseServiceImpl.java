package com.cms.scaffold.common.base;

import com.cms.scaffold.common.response.ResponsePageModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;


/**
 * @author Administrator
 */
public class BaseServiceImpl<D extends BaseMapper<T>, T extends BaseEntity> implements BaseService<T> {

    @Autowired
    protected D dao;


    protected Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Override
    public T selectById(Serializable id) {
        return dao.selectById(id);
    }

    @Override
    public T selectLockById(Serializable id) {
        return dao.selectLockById(id);

    }

    @Override
    public T selectOne(T record) {
        return dao.selectOne(record);
    }

    @Override
    public List<T> findList(T record) {
        return dao.findList(record);
    }

    @Override
    public ResponsePageModel<T> findPage(T record) {
        PageHelper.startPage(record.getPage(), record.getRows());
        List<T> list = dao.findList(record);
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return new ResponsePageModel<>(list, pageInfo.getTotal());
    }

    @Override
    public int insert(T record) {
        int i = dao.insert(record);
        record.setId(record.getId());
        return i;
    }

    @Override
    public int batchInsert(List<T> list) {
        return dao.batchInsert(list);
    }

    @Override
    public int update(T record) {
        return dao.update(record);
    }

    @Override
    public int batchUpdate(List<T> list) {
        return dao.batchUpdate(list);
    }

    @Override
    public int updateAll(T record) {
        return dao.updateAll(record);
    }

    @Override
    public int delList(List<T> list) {
        return dao.delList(list);
    }

    @Override
    public int delArray(Long[] ids) {
        return dao.delArray(ids);
    }

    @Override
    public int count(T record) {
        return dao.count(record);
    }

    @Override
    public int update(T record, T oldRecord) {
        return dao.update(record, oldRecord);
    }

    @Override
    public int batchUpdate(List<T> list, List<T> listOld) {
        return dao.batchUpdate(list, listOld);
    }
}
