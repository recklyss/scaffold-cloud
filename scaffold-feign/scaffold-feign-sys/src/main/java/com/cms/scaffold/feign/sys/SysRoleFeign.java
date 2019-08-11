package com.cms.scaffold.feign.sys;

import com.cms.scaffold.micro.sys.api.SysRoleApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Administrator
 */
@FeignClient(value = "scaffold-sys-service")
public interface SysRoleFeign extends SysRoleApi {
}
