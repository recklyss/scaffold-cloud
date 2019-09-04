package com.cms.scaffold.micro.message.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.builder.Builder;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.common.response.ResponsePageModel;
import com.cms.scaffold.micro.message.ao.MessageAO;
import com.cms.scaffold.micro.message.api.MessageApi;
import com.cms.scaffold.micro.message.domain.Message;
import com.cms.scaffold.micro.message.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
* @author zhangjiaheng
*/
@RestController
@Slf4j
public class MessageController extends BaseController implements MessageApi {

    @Autowired
    MessageService messageService;

    /**
    * @param id 主键
    * @return 根据主键查询
    */
    @Override
    public ResponseModel selectById(Long id) {
        log.info("根据ID主键查询>>> id:[{}]", id);
        final Message message = messageService.selectById(id);
        return successData(Builder.build(message, Message.class));
    }

    @Override
    public ResponsePageModel findPage(@RequestBody MessageAO message) {
        return messageService.findPage(Builder.build(message, Message.class));
    }
}
