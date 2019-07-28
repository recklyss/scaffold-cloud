package com.cms.scaffold.plugin.crud;

import cn.hutool.core.date.DateUtil;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;

/**
 * Created by zhangjiahengpoping@gmail.com on 2018/2/8.
 */
public class CodeGenerateUtils {
    private final String AUTHOR = "zhangjiahengpoping@gmail.com";
    private final String CURRENT_DATE = DateUtil.now();
    private final String tableName = PropertiesUtil.getValue("generator.tableName");
    private final String URL = PropertiesUtil.getValue("generator.connectionUrl");
    private final String USER = PropertiesUtil.getValue("generator.connectionUserId");
    private final String PASSWORD = PropertiesUtil.getValue("generator.connectionPwd");
    private final String DRIVER = PropertiesUtil.getValue("generator.driverClass");
    private final String changeTableName = PropertiesUtil.getValue("generator.domainObjectName");
    private final String packageName = PropertiesUtil.getValue("generator.packageName");

    private final String apiFilePath = PropertiesUtil.getValue("generator.path") +"\\"+
            PropertiesUtil.getValue("generator.javaModelTargetProject") + PropertiesUtil.getValue("generator.packageNameProject");

    private final String implFilePath = PropertiesUtil.getValue("generator.path") +"\\"+
            PropertiesUtil.getValue("generator.javaClientTargetProject") + PropertiesUtil.getValue("generator.packageNameProject");




    public static void main(String[] args) throws Exception{
        CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils();
        codeGenerateUtils.generate();
    }


    public void generate() throws Exception{
        try {
            //生成service文件
            generateFile("Service.ftl",changeTableName+"Service",implFilePath+"/service/");
            //生成serviceImpl文件
            generateFile("ServiceImpl.ftl",changeTableName+"ServiceImpl",implFilePath + "/service/impl/");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{

        }
    }


    private void generateFile(String templateName,String fileName,String diskPath) throws Exception{

        final String suffix = ".java";
        final String path = diskPath + fileName + suffix;
        File diskPathFile = new File(diskPath);


        if (!diskPathFile.exists() && !diskPathFile.isDirectory()) {
            System.out.println(diskPathFile+"//不存在");
            diskPathFile.mkdirs();
        }
        File mapperFile = new File(path);

        generateFileByTemplate(templateName,mapperFile);

    }

    private void generateFileByTemplate(final String templateName,File file) throws Exception{
        HashMap dataMap = new HashMap<String,Object>();
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(file);
        dataMap.put("table_name",changeTableName);
        dataMap.put("package_name",packageName);
        dataMap.put("author",AUTHOR);
        dataMap.put("date",CURRENT_DATE);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        template.process(dataMap,out);
    }
}


