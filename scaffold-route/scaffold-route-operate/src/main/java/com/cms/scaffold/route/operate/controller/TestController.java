package com.cms.scaffold.route.operate.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.base.ResponseModel;
import com.cms.scaffold.route.operate.feigin.FeiginTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController extends BaseController {
    @Autowired
    FeiginTest feiginTest;

    @GetMapping("/get")
    public ResponseModel getTest() {
        return feiginTest.testService();
    }
}

