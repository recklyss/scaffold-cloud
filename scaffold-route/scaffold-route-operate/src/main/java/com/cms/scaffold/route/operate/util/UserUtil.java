package com.cms.scaffold.route.operate.util;

import com.cms.scaffold.micro.sys.bo.SysOperateBO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;

/**
 * Created by zjh on 2018/2/13.
 */
public class UserUtil {


    private static final String SESSION_ATTRIBUTE_KEY_OPERATOR = "session_user";

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
            Object attribute = getSession().getAttribute(SESSION_ATTRIBUTE_KEY_OPERATOR);
            return attribute == null ? null : (SysOperateBO) attribute;
        }
        return  null;
    }


    /**
     * 获取当前登录者对象
     */
    public static Object getPrincipal(){
        Subject subject = SecurityUtils.getSubject();

        return subject.getPrincipal();
    }

}


