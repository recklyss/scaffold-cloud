package com.cms.scaffold.route.operate.shiro;


import com.cms.scaffold.common.util.MD5Util;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 重写密码MD5加密比较
 *
 * @author zhang
 * @date 2017-7-15
 */
public class Md5CredentialsMatcher extends SimpleCredentialsMatcher {

    /**
     * 判断登陆密码是否相同
     *
     * @param token
     * @param info
     * @date 2017-7-15
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Object tokenCredentials = getCredentials(token);
        Object accountCredentials = getCredentials(info);
        final String s = new String((char[]) tokenCredentials);
        return equals(MD5Util.encode(s).toUpperCase(), accountCredentials);
    }


}
