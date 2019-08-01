package com.cms.scaffold.plugin.crud;

import cn.hutool.core.date.DateUtil;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * Created by zhangjiahengpoping@gmail.com on 2018/2/8.
 */
public class CodeGenerateUtils {

    private static final Logger logger = LoggerFactory.getLogger(CodeGenerateUtils.class);

    private final String AUTHOR = "zhangjiahengpoping@gmail.com";
    private final String CURRENT_DATE = DateUtil.now();
    private final String changeTableName = PropertiesUtil.getValue("generator.domainObjectName");
    private final String packageName = PropertiesUtil.getValue("generator.packageName");
    private final String feignPackageName = PropertiesUtil.getValue("generator.feign.packageName");
    private final String feignServiceName = PropertiesUtil.getValue("feign.service.name");

    private final String apiFilePath = PropertiesUtil.getValue("generator.path") + "\\" +
            PropertiesUtil.getValue("generator.api.javaModelTargetProject")
            + PropertiesUtil.getValue("generator.packageNameProject");

    private final String feignFilePath = PropertiesUtil.getValue("generator.path") + "\\" +
            PropertiesUtil.getValue("generator.feign.javaModelTargetProject")
            + PropertiesUtil.getValue("generator.feign.packageNameProject");

    private final String implFilePath = PropertiesUtil.getValue("generator.path") + "\\" +
            PropertiesUtil.getValue("generator.javaClientTargetProject")
            + PropertiesUtil.getValue("generator.packageNameProject");


    public static void main(String[] args) throws Exception {
        CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils();
        codeGenerateUtils.generate();
    }


    public void generate() throws Exception {
        try {
            generateFile("Service.ftl", changeTableName + "Service", implFilePath + "/service/");
            generateFile("ServiceImpl.ftl", changeTableName + "ServiceImpl", implFilePath + "/service/impl/");
            generateFile("api.ftl", changeTableName + "Api", apiFilePath + "/api/");
            generateFile("feign.ftl", changeTableName + "Feign", feignFilePath + "/");
            generateFile("micro-controller.ftl", changeTableName + "Controller", implFilePath + "/controller/");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

        }
    }


    private void generateFile(String templateName, String fileName, String diskPath) throws Exception {

        final String suffix = ".java";
        final String path = diskPath + fileName + suffix;
        File diskPathFile = new File(diskPath);


        if (!diskPathFile.exists() && !diskPathFile.isDirectory()) {
            logger.info("文件【{}】不存在，新建... ...", diskPathFile);
            diskPathFile.mkdirs();
        }
        File mapperFile = new File(path);

        generateFileByTemplate(templateName, mapperFile);

    }

    private void generateFileByTemplate(final String templateName, File file) throws Exception {
        HashMap<String, Object> dataMap = new HashMap<>(20);
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(file);
        dataMap.put("table_name", changeTableName);
        dataMap.put("package_name", packageName);
        dataMap.put("feign_package_name", feignPackageName);
        dataMap.put("author", AUTHOR);
        dataMap.put("date", CURRENT_DATE);
        dataMap.put("feign_service_name", feignServiceName);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8), 10240);
        template.process(dataMap, out);
    }
}


