package com.cms.scaffold.route.operate.feigin;

import com.cms.scaffold.common.base.ResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "scaffold-sys-service")
public interface FeiginTest {

    @GetMapping("/service/test")
    ResponseModel testService();
}
