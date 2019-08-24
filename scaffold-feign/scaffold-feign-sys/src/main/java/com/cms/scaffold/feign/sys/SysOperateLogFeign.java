package com.cms.scaffold.feign.sys;

import org.springframework.cloud.openfeign.FeignClient;
import com.cms.scaffold.micro.sys.api.SysOperateLogApi;

/**
* @author zhangjiaheng
*/
@FeignClient(value = "scaffold-sys-service")
public interface SysOperateLogFeign extends SysOperateLogApi {}
