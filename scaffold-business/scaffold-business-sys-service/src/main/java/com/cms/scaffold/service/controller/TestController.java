package com.cms.scaffold.service.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.base.ResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class TestController extends BaseController {

    @GetMapping("/test")
    public ResponseModel testService(){
        return successData("测试服务提供者");
    }
}
