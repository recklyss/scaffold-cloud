package com.cms.scaffold.plugin.crud;

import com.cms.scaffold.plugin.mybatis.generator.GenMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

public final class PropertiesUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);

    /**
     * 资源属性
     */
    private static Properties properties;

    /**
     * 私有构造方法
     */
    private PropertiesUtil() {
    }

    static {
        properties = new Properties();
        try {
            // 读取配置文件
            System.out.println("---------初始化---------");
            if (GenMain.propsFilePath == null) {
                properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties"));
            } else {
                properties.load(new FileInputStream(GenMain.propsFilePath));
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("读取配置文件出错，请确认properties文件已经放在目录下。");
        }
    }

    /**
     * 获取配置信息
     *
     * @param key 键
     * @return 配置信息
     */
    public static String getValue(String key) {
        String value = properties.getProperty(key);
        if (value == null || "".equals(value)) {
            LOGGER.warn("没有获取指定key的值，请确认资源文件中是否存在【" + key + "】");
        }
        if (value.contains("${")) {
            String oldC = value.substring(value.indexOf("${"), value.indexOf("}")+1);
            String key2 = value.substring(value.indexOf("${") + 2, value.indexOf("}"));
            value = value.replace(oldC, getValue(key2));
        }
        return value;
    }

    /**
     * 获取配置信息
     *
     * @param key   键
     * @param param 参数
     * @return 配置信息
     */
    public static String getValue(String key, Object[] param) {
        String value = getValue(key);
        return MessageFormat.format(value, param);
    }

}
