package com.cms.scaffold.route.operate.shiro;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.feign.sys.SysMenuFeign;
import com.cms.scaffold.feign.sys.SysOperateFeign;
import com.cms.scaffold.micro.sys.bo.SysMenuBO;
import com.cms.scaffold.micro.sys.bo.SysOperateBO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

import static com.cms.scaffold.route.operate.constant.SysConstants.SESSION_ATTRIBUTE_KEY_OPERATOR;
import static com.cms.scaffold.route.operate.constant.SysConstants.SESSION_ATTRIBUTE_KEY_OPERATOR_ID;

/**
 * @author zhangjiaheng
 */
public class MyShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Resource
    SysMenuFeign sysMenuFeign;
    @Resource
    SysOperateFeign sysOperateFeign;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String username = (String) principalCollection.getPrimaryPrincipal();

        ResponseModel<SysOperateBO> sysOperateResponse = sysOperateFeign.findByUserName(username);
        final SysOperateBO sysOperate = sysOperateResponse.getData();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<SysMenuBO> menuList = sysMenuFeign.findByOperateId(sysOperate.getId()).getData();
        if (CollectionUtil.isNotEmpty(menuList)) {
            for (SysMenuBO menu : menuList) {
                if (StrUtil.isNotBlank(menu.getCode())) {
                    info.addStringPermission(menu.getCode());
                }
            }
        }
        return info;
    }


    /**认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        SysOperateBO operator = sysOperateFeign.findByUserName(username).getData();

        // 帐号锁定
        if (operator.getStatus() == null || operator.getStatus() == 1) {
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                username,
                operator.getPwd(),
                ByteSource.Util.bytes(username),
                getName()
        );
        //当验证都通过后，把用户信息放在session里
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(SESSION_ATTRIBUTE_KEY_OPERATOR, operator);
        session.setAttribute(SESSION_ATTRIBUTE_KEY_OPERATOR_ID, operator.getId());
        return authenticationInfo;

    }

    /**
     * 清理权限缓存 防止加一次重启一次
     */
    public void clearCachedAuthorization() {
        //清空权限缓存
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
