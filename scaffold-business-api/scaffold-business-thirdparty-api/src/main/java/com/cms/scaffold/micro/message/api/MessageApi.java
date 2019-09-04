package com.cms.scaffold.micro.message.api;

import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.common.response.ResponsePageModel;
import com.cms.scaffold.micro.message.ao.MessageAO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhangjiaheng
 */
public interface MessageApi {


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 根据主键查询
     */
    @RequestMapping(value = "/message/selectById", method = RequestMethod.GET)
    ResponseModel selectById(@RequestParam("id") Long id);

    /**
     * 分页查询
     * @param message 查询参数
     * @return
     */
    @RequestMapping(value = "/message/findPage", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponsePageModel findPage(@RequestBody MessageAO message);
}
