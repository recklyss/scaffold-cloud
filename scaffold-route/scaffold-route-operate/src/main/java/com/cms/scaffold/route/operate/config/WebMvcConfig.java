package com.cms.scaffold.route.operate.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.util.Locale;

/**
 *
 * @author zjh
 * @date 2018/2/8
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * cookie 语言 存在时间
     */
    private static final int LOCAL_COOKIE_TIME = 3600;

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver slr = new CookieLocaleResolver();
        slr.setDefaultLocale(Locale.CHINA);
        slr.setCookieMaxAge(LOCAL_COOKIE_TIME);
        return slr;

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:login");
    }

    /**
     * 文件上传临时路径
     */
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String location  = System.getProperty("user.dir") +"/home/tmp";
        File tmpFile   =new File (location);
        if(!tmpFile.exists()){
            tmpFile.mkdirs();
        }
        factory.setLocation(location);
        return factory.createMultipartConfig();
    }
}
