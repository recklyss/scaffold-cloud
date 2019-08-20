package com.cms.scaffold.route.operate.config;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.feign.sys.SysMenuFeign;
import com.cms.scaffold.micro.sys.bo.SysMenuBO;
import com.cms.scaffold.route.operate.shiro.Md5CredentialsMatcher;
import com.cms.scaffold.route.operate.shiro.MySessionManager;
import com.cms.scaffold.route.operate.shiro.MyShiroRealm;
import com.cms.scaffold.route.operate.shiro.ShiroService;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zhangjiaheng
 * @Description shiro配置类
 **/
@Configuration
public class ShiroConfig {

    /**
     * 默认permission字符串
     */
    public static final String PERMISSION_STRING = "perms[\"{0}\"]";

    @Resource
    SysMenuFeign sysMenuFeign;

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(new Md5CredentialsMatcher());
        return myShiroRealm;
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        return new MySessionManager();
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        Map<String, Filter> filters = new LinkedHashMap<>();
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/home");
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> filterChainDefinitionManager = new LinkedHashMap<String, String>();
        ResponseModel<List<SysMenuBO>> responseModel = sysMenuFeign.findAll();
        List<SysMenuBO> menuList = responseModel.getData();
        if (CollectionUtil.isNotEmpty(menuList)) {
            for (SysMenuBO menu : menuList) {
                if (StrUtil.isNotEmpty(menu.getUrl())) {
                    ShiroService.formatUrl2Code(filterChainDefinitionManager, menu, PERMISSION_STRING);
                }
            }
        }
        filterChainDefinitionManager.put("/login", "anon");
        filterChainDefinitionManager.put("/logout", "logout");
        filterChainDefinitionManager.put("/login/check", "anon");
        filterChainDefinitionManager.put("/static/**", "anon");
        filterChainDefinitionManager.put("/notify/**", "anon");
        filterChainDefinitionManager.put("/lang/**", "anon");

        filterChainDefinitionManager.put("/*/login", "anon");
        filterChainDefinitionManager.put("/*/logout", "logout");
        filterChainDefinitionManager.put("/*/login/check", "anon");
        filterChainDefinitionManager.put("/*/static/**", "anon");
        filterChainDefinitionManager.put("/*/notify/**", "anon");
        filterChainDefinitionManager.put("/*/lang/**", "anon");

        filterChainDefinitionManager.put("/**", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);

        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
