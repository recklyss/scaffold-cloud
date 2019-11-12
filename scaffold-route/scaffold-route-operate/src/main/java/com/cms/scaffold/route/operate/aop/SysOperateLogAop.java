package com.cms.scaffold.route.operate.aop;

import com.alibaba.fastjson.JSON;
import com.cms.scaffold.code.mq.model.sys.SysOperateLogModel;
import com.cms.scaffold.code.mq.model.sys.SysOperateLogMqModel;
import com.cms.scaffold.code.util.RocketMqSendUtil;
import com.cms.scaffold.route.operate.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * 拦截所有后台的操作日志记录到数据库
 *
 * @author zhang
 */
@Aspect
@Component
@Slf4j
public class SysOperateLogAop {

    @Resource
    ThreadPoolTaskExecutor taskExecutor;

    /**
     * 需要拦截的方法名
     */
    private static final String[] LOG_METHOD_NAMES =
            {"update", "insert", "save", "add", "remove", "change", "edit", "delete"};

    @Pointcut(value = "execution(* com.cms.scaffold.route.operate.controller.*.*.*(..))")
    public void pointCut() {
    }

    /**
     * 在结束之后插入后台操作记录
     */
    @AfterReturning(pointcut = "pointCut()", returning = "retValue")
    public void insertSysOperateLog(JoinPoint joinPoint, Object retValue) {
        if (retValue instanceof String || retValue instanceof ModelAndView) {
            return;
        }
        String methodName = joinPoint.getSignature().getName().toLowerCase();
        boolean match = Arrays.stream(LOG_METHOD_NAMES).anyMatch(methodName::contains);
        if (match) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            taskExecutor.execute(()->{
                HttpServletRequest request = requestAttributes.getRequest();
                String uri = request.getRequestURI();
                String classType = joinPoint.getTarget().getClass().getName();
                String param = null;
                Map<String, String[]> parameterMap = request.getParameterMap();
                if(parameterMap != null && parameterMap.size()>0 ){
                    param = JSON.toJSONString(parameterMap);
                }
                String returnParam = null;
                if(retValue!=null){
                    returnParam = JSON.toJSONString(retValue);
                }
                SysOperateLogModel operateLog = new SysOperateLogModel();
                operateLog.setClassName(classType);
                operateLog.setRequestUrl(uri);
                operateLog.setRequestParam(param);
                operateLog.setResponseParam(returnParam);
                operateLog.setRequestMethod(methodName);
                operateLog.setOperateId(UserUtil.getOperatorId());
                operateLog.setOperateName(UserUtil.getOperatorFromSession().getUserName());
                SysOperateLogMqModel model = new SysOperateLogMqModel(operateLog);
                log.info("准备发送MQ消息，tag:{}", model.getTag());
                RocketMqSendUtil.sendMq(Collections.singletonList(model));
            });

        }
    }
}
