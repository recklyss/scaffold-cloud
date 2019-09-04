package com.cms.scaffold.micro.message.service.impl;

import com.cms.scaffold.common.base.BaseServiceImpl;
import com.cms.scaffold.micro.message.dao.MessageMapper;
import com.cms.scaffold.micro.message.domain.Message;
import com.cms.scaffold.micro.message.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* @author zhangjiaheng
*/
@Service
@Slf4j
public class MessageServiceImpl extends BaseServiceImpl<MessageMapper, Message> implements MessageService {
    @Override
    public Message selectByKey(String key) {
        return dao.selectByKey(key);
    }
}
