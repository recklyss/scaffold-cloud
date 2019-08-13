package com.cms.scaffold.code.aspect;

import cn.hutool.core.util.StrUtil;
import com.cms.scaffold.common.base.BaseEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切面类
 * 保存更新记录时，同时更新添加时间和更新时间
 *
 * @author zhangjiahengpoping@gmail.com
 * @date 2018/3/20
 */
@Aspect
@Component
@Order(1)
public class PreInsertAspect {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("   execution(* com.cms.scaffold.micro.*.dao.*.insert*(..)) " +
            "|| execution( * com.cms.scaffold.common.base.BaseMapper.insert*(..))")
    public void insert(JoinPoint joinPoint){

        BaseEntity baseEntity = null;
        try {
            //获取目标方法的参数信息
            Object[] obj = joinPoint.getArgs();
            if(obj!=null && obj.length > 0 && obj[0] instanceof BaseEntity){
                baseEntity = (BaseEntity)obj[0];
                if (baseEntity.getAddTime() == null){
                    baseEntity.preInsert();
                }
            }
        }catch (Exception ex){
            logger.info(ex.getMessage(),ex);
        }

    }

}
