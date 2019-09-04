package com.cms.scaffold.micro.message.dao;

import com.cms.scaffold.common.base.BaseMapper;
import com.cms.scaffold.micro.message.domain.Message;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhang
 */
public interface MessageMapper extends BaseMapper<Message> {
    /**
     * 根据key查询
     *
     * @param key
     * @return
     */
    @Select("select * from sys_message where key = #{key}")
    Message selectByKey(@Param("key") String key);
}