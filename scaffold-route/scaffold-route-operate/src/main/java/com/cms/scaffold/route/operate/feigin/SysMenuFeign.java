package com.cms.scaffold.route.operate.feigin;

import com.cms.scaffold.micro.sys.api.SysMenuApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "scaffold-sys-service")
public interface SysMenuFeign extends SysMenuApi {}
