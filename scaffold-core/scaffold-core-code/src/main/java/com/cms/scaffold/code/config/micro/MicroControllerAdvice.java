package com.cms.scaffold.code.config.micro;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cms.scaffold.common.base.BaseStatusCode;
import com.cms.scaffold.common.exception.BusinessException;
import com.cms.scaffold.common.response.ResponseModel;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 微服务controller统一异常拦截器
 *
 * @author zhang
 */
@RestControllerAdvice(annotations = RestController.class)
public class MicroControllerAdvice {
    private static final Log logger = LogFactory.get(MicroControllerAdvice.class);

    /**
     * 拦截全局业务异常
     *
     * @param be
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseModel businessException(BusinessException be) {
        logger.error("业务异常：{}", be);
        be.printStackTrace();
        return new ResponseModel<>(be.getCode(), be.getLocalizedMessage(), be.getMessage(), be.getStackTrace()[0]);
    }

    /**
     * 拦截未知错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseModel runntimeException(RuntimeException e) {
        logger.error("未知异常：{}", e);
        e.printStackTrace();
        return new ResponseModel<>(BaseStatusCode.UNKNOW_ERROR.getCode(), BaseStatusCode.UNKNOW_ERROR.getMessage(),
                e.getLocalizedMessage(), e.getStackTrace()[0]);
    }
}
