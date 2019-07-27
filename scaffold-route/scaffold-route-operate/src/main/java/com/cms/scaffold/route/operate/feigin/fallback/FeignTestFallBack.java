package com.cms.scaffold.route.operate.feigin.fallback;

import com.cms.scaffold.common.base.ResponseModel;
import com.cms.scaffold.route.operate.feigin.FeignTest;
import org.springframework.stereotype.Component;

@Component("feignTestFallBack")
public class FeignTestFallBack implements FeignTest {
    @Override
    public ResponseModel testService(Long id) {
        return new ResponseModel<>("调用失败！！！fall back");
    }
}
