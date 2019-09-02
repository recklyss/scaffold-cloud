package com.cms.scaffold.feign.message;

import com.cms.scaffold.micro.message.api.MessageApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
* @author zhangjiaheng
*/
@FeignClient(value = "scaffold-sys-service")
public interface MessageFeign extends MessageApi {}
