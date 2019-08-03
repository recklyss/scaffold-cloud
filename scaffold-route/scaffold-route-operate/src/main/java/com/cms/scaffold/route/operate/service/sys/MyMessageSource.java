package com.cms.scaffold.route.operate.service.sys;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cms.scaffold.common.response.ResponseModel;
import com.cms.scaffold.feign.sys.SysI18nFeign;
import com.cms.scaffold.micro.sys.ao.SysI18nAO;
import com.cms.scaffold.micro.sys.bo.SysI18nBO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 替换掉内置的messageSource
 * 关联Spring.ftl
 * @author zhang
 */
@Service("messageSource")
public class MyMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

    private static final Log logger = LogFactory.get(MyMessageSource.class);

    ResourceLoader resourceLoader;
    @Resource
    SysI18nFeign sysI18nFeign;

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        this.reload();
    }

    /**
     * 重新将数据库中的国际化配置加载
     */
    public void reload() {
        LOCAL_CACHE.clear();
        LOCAL_CACHE.putAll(loadAllMessageResourcesFromDB());
    }

    /**
     *  存储国际化配置
     */
    private static final Map<String, Map<String, String>> LOCAL_CACHE = new ConcurrentHashMap<>(5112);

    /**
     * 从数据库中获取所有国际化配置 这边可以根据自己数据库表结构进行相应的业务实现
     * 对应的语言能够取出来对应的值就行了 无需一定要按照这个方法来
     */
    public Map<String, Map<String, String>> loadAllMessageResourcesFromDB() {
        ResponseModel<List<SysI18nBO>> resp = sysI18nFeign.findList(new SysI18nAO());
        List<SysI18nBO> list = resp.getData();
        if (CollectionUtils.isNotEmpty(list)) {
            final Map<String, String> zhCnMessageResources = new HashMap<>(list.size());
            final Map<String, String> enUsMessageResources = new HashMap<>(list.size());
            for (SysI18nBO bo : list) {
                String name = bo.getModel() + "." + bo.getName();
                String zhText = bo.getZhCn();
                String enText = bo.getEnUs();
                zhCnMessageResources.put(name, zhText);
                enUsMessageResources.put(name, enText);
            }
            LOCAL_CACHE.put("zh", zhCnMessageResources);
            LOCAL_CACHE.put("en", enUsMessageResources);
        }
        return MapUtils.EMPTY_MAP;
    }

    /**
     * 从缓存中取出国际化配置对应的数据 或者从父级获取
     *
     * @param code
     * @param locale
     * @return
     */
    public String getSourceFromCache(String code, Locale locale) {
        String language = locale.getLanguage();
        Map<String, String> props = LOCAL_CACHE.get(language);
        if (null != props && props.containsKey(code)) {
            return props.get(code);
        } else {
            try {
                if (null != this.getParentMessageSource()) {
                    return this.getParentMessageSource().getMessage(code, null, locale);
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
            return code;
        }
    }

    // 下面三个重写的方法是比较重要的
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = Optional.ofNullable(resourceLoader).orElse(new DefaultResourceLoader());
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String msg = getSourceFromCache(code, locale);
        MessageFormat messageFormat = new MessageFormat(msg, locale);
        return messageFormat;
    }

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        return getSourceFromCache(code, locale);
    }
}
