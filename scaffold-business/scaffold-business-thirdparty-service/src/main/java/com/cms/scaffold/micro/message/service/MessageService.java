package com.cms.scaffold.micro.message.service;

import com.cms.scaffold.common.base.BaseService;
import com.cms.scaffold.micro.message.domain.Message;

/**
 * @author zhangjiaheng
 */
public interface MessageService extends BaseService<Message> {
    /**
     * 根据key查询
     *
     * @param key
     * @return
     */
    Message selectByKey(String key);
}
