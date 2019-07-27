package com.cms.scaffold.route.operate.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.base.ResponseModel;
import com.cms.scaffold.route.operate.feigin.FeignTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController extends BaseController {
    @Resource
    FeignTest feignTest;

    @GetMapping("/get")
    public ResponseModel getTest(Long id) {
        return feignTest.testService(id);
    }
}

