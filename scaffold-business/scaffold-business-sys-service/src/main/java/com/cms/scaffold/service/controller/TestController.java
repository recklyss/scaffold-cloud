package com.cms.scaffold.service.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.base.ResponseModel;
import com.cms.scaffold.service.api.test.TestApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends BaseController implements TestApi {

    @Override
    public ResponseModel testService(Long id) {
        return successData("调用服务testService方法，参数：>>> " + id);
    }
}
