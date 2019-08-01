package com.cms.scaffold.feign.sys;

import org.springframework.cloud.openfeign.FeignClient;
import com.cms.scaffold.micro.sys.api.SysRoleMenuApi;

/**
* @author zhangjiaheng
*/
@FeignClient(value = "scaffold-sys-service")
public interface SysRoleMenuFeign extends SysRoleMenuApi {}
