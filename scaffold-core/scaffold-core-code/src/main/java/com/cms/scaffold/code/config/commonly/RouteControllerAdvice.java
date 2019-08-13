package com.cms.scaffold.code.config.commonly;

import com.cms.scaffold.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

/**
 * 路由服务controller统一异常拦截器
 * @author zhang
 */
@ControllerAdvice(annotations = Controller.class)
public class RouteControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(RouteControllerAdvice.class);

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        log.info("binder.getFieldDefaultPrefix {}",binder.getFieldDefaultPrefix());
        log.info("binder.getFieldMarkerPrefix {}",binder.getFieldMarkerPrefix());
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView runntimeException(BusinessException be) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("businessError");
        modelAndView.addObject("code", be.getCode());
        modelAndView.addObject("message", be.getMessage());
        return modelAndView;
    }
}
