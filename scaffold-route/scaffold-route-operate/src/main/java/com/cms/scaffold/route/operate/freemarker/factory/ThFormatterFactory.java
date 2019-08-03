package com.cms.scaffold.route.operate.freemarker.factory;

import cn.hutool.core.util.StrUtil;
import com.cms.scaffold.route.operate.freemarker.impl.ThFormatterDict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 *
 * @author zjh
 * @date 2018/7/2
 */
public class ThFormatterFactory {

    /**
     * 反射用到的包路径
     */
    private static final String PACKAGE_PATH = "com.cms.scaffold.route.operate.freemarker.impl.";

    /**
     * 反射用到的类前缀
     */
    private static final String PRE_STRING = "ThFormatter";

    private static Logger logger = LoggerFactory.getLogger(ThFormatterFactory.class);

    public static ThFormatterInterface createThFormatter(String type) {
        if (StringUtils.isEmpty(type)) {
            return new ThFormatterDict();
        }
        //文件名
        String fileName = StrUtil.upperFirstAndAddPre(type, PRE_STRING);
        //类路径
        String className = PACKAGE_PATH + fileName;

        //生成表头格式实现类
        ThFormatterInterface thFormatterInterface = null;
        try {
            thFormatterInterface = (ThFormatterInterface) Class.forName(className).newInstance();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return thFormatterInterface;
    }
}
