package com.cms.scaffold.service.api.test;

import com.cms.scaffold.common.base.ResponseModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhang
 * 用于测试的API接口
 * 接口、实现分离
 */
public interface TestApi{

    @RequestMapping(value = "/service/test", method = RequestMethod.GET)
    ResponseModel testService(@RequestParam("id") Long id);
}
