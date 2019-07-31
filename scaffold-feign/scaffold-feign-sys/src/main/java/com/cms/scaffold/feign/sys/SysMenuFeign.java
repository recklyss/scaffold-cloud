package com.cms.scaffold.feign.sys;

import com.cms.scaffold.micro.sys.api.SysMenuApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhangjiaheng
 */
@FeignClient(value = "scaffold-sys-service")
public interface SysMenuFeign extends SysMenuApi {}
