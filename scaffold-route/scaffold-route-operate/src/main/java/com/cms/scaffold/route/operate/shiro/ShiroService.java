package com.cms.scaffold.route.operate.shiro;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cms.scaffold.feign.sys.SysMenuFeign;
import com.cms.scaffold.micro.sys.bo.SysMenuBO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhang
 */
@Service
public class ShiroService {

    private static final Log logger = LogFactory.get(ShiroService.class);

    public static final String PERMISSION_STRING = "perms[\"{0}\"]";

    @Autowired
    ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Resource
    SysMenuFeign sysMenuFeign;

    /**
     * 初始化权限
     */
    public Map<String, String> loadFilterChainDefinitions() {
        // 权限控制map.从数据库获取
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        List<SysMenuBO> menuList = sysMenuFeign.findAll().getData();

        if (menuList != null && !menuList.isEmpty()) {
            for (SysMenuBO menu : menuList) {
                if (!StringUtils.isEmpty(menu.getUrl())) {
                    formatUrl2Code(filterChainDefinitionMap, menu, PERMISSION_STRING);
                }
            }
        }
        return filterChainDefinitionMap;
    }

    public static void formatUrl2Code(Map<String, String> filterChainDefinitionMap, SysMenuBO menu, String permissionString) {
        if (menu.getUrl().indexOf("/") == 0) {
            filterChainDefinitionMap.put(menu.getUrl(),
                    MessageFormat.format(permissionString, menu.getUrl().replaceFirst("/", "").replaceAll("/", ":")));
        } else {
            filterChainDefinitionMap.put(menu.getUrl(),
                    MessageFormat.format(permissionString, menu.getUrl().replaceAll("/", ":")));
        }
    }


    /**
     * 重新加载权限
     */
    public void updatePermission() {

        synchronized (shiroFilterFactoryBean) {

            AbstractShiroFilter shiroFilter = null;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean
                        .getObject();
            } catch (Exception e) {
                throw new RuntimeException(
                        "get ShiroFilter from shiroFilterFactoryBean error!");
            }

            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
                    .getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
                    .getFilterChainManager();

            // 清空老的权限控制
            manager.getFilterChains().clear();

            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean
                    .setFilterChainDefinitionMap(loadFilterChainDefinitions());
            // 重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean
                    .getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim()
                        .replace(" ", "");
                manager.createChain(url, chainDefinition);
            }

            logger.info("更新权限成功！！");
        }
    }


}
