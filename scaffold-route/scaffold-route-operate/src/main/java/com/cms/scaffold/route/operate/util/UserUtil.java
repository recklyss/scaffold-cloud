package com.cms.scaffold.route.operate.util;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cms.scaffold.micro.sys.bo.SysOperateBO;
import com.cms.scaffold.route.operate.constant.SysConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;

import java.util.Optional;

/**
 * Created by zjh on 2018/2/13.
 */
public class UserUtil {

    private static final Log logger = LogFactory.get(UserUtil.class);

    /**
     * 获取授权主要对象
     */
    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    public static Session getSession(){
        try{
            SecurityManager securityManager = ThreadContext.getSecurityManager();
            if(securityManager == null){
                return null;
            }

            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null){
                session = subject.getSession();
            }
            if (session != null){
                return session;
            }
        }catch (InvalidSessionException e){

        }
        return null;
    }

    /**
     * 从Session获取当前用户信息
     *
     * @return
     */
    public static SysOperateBO getOperatorFromSession() {
        if(getSession() != null){
            Object attribute = getSession().getAttribute(SysConstants.SESSION_ATTRIBUTE_KEY_OPERATOR);
            return attribute == null ? new SysOperateBO() : (SysOperateBO) attribute;
        }
        logger.warn("当前系统session无登陆用户");
        return  new SysOperateBO();
    }

    /**
     * @return 当前登陆用户的ID
     */
    public static Long getOperatorId() {
        return Optional.ofNullable(getOperatorFromSession()).orElse(new SysOperateBO()).getId();
    }


    /**
     * 获取当前登录者对象
     */
    public static Object getPrincipal(){
        Subject subject = SecurityUtils.getSubject();

        return subject.getPrincipal();
    }

}


