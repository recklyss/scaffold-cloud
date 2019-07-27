package com.cms.scaffold.route.operate.feigin;

import com.cms.scaffold.route.operate.feigin.fallback.FeignTestFallBack;
import com.cms.scaffold.service.api.test.TestApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "scaffold-sys-service", fallback = FeignTestFallBack.class)
public interface FeignTest extends TestApi {}
