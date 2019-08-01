package com.cms.scaffold.feign.sys;

import org.springframework.cloud.openfeign.FeignClient;
import com.cms.scaffold.micro.sys.api.SysI18nApi;

/**
* @author zhangjiaheng
*/
@FeignClient(value = "scaffold-sys-service")
public interface SysI18nFeign extends SysI18nApi {}
