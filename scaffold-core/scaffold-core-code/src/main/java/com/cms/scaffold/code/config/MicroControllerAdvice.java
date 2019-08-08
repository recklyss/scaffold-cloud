package com.cms.scaffold.code.config;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cms.scaffold.common.exception.BusinessException;
import com.cms.scaffold.common.response.ResponseModel;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 微服务controller统一异常拦截器
 *
 * @author zhang
 */
@RestControllerAdvice(basePackages = {"com.cms.scaffold.micro.*.controller"})
public class MicroControllerAdvice {
    private static final Log logger = LogFactory.get(MicroControllerAdvice.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseModel businessException(BusinessException be) {
        logger.error("业务异常：{}", be);
        be.printStackTrace();
        return new ResponseModel<>(be.getCode(), be.getMessage(), be);
    }
}
