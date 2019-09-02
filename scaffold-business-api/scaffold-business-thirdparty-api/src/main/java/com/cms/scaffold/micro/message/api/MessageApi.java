package com.cms.scaffold.micro.message.api;

import com.cms.scaffold.common.response.ResponseModel;
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
}
