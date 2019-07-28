package com.cms.scaffold.plugin.mybatis.generator;

import com.cms.scaffold.plugin.crud.CodeGenerateUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.util.messages.Messages;

import java.io.File;
import java.util.*;

public class GenMain {
    public static String propsFilePath;

    public static void genFile(String configFilePath) {
        //读取文件
        File configFile = new File(configFilePath);
        List<String> warnings = new ArrayList<String>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = null;
        //true:覆盖生成
        DefaultShellCallback callback = new DefaultShellCallback(false);
        MyBatisGenerator myBatisGenerator = null;
        try {
            config = cp.parseConfiguration(configFile);
            myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);

//            CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils();
//            codeGenerateUtils.generate();
            System.err.println("代码成功生成!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        if (args.length == 0) {
            genFile(GenMain.class.getResource("/generatorConfig.xml").getFile());
        } else {
            Map<String, String> arguments = parseCommandLine(args);
            if (arguments.containsKey("-?")) {
                usage();
                System.exit(0);
            } else if (!arguments.containsKey("-configfile")) {
                writeLine(Messages.getString("RuntimeError.0"));
            } else if (!arguments.containsKey("-propsFile")) {
                writeLine(Messages.getString("RuntimeError.0")+":::-propsFile");
            } else {
                List<String> warnings = new ArrayList();
                String configfile = (String) arguments.get("-configfile");
                System.out.println(configfile);
                String propsFile = (String) arguments.get("-propsFile");
                File configurationFile = new File(configfile);
                System.out.println(!configurationFile.exists());
                if ( !configurationFile.exists()) {
                    writeLine(Messages.getString("RuntimeError.1", configfile));
                    System.exit(0);
                }
                if ( !new File(propsFile).exists()) {
                    writeLine(Messages.getString("RuntimeError.1", propsFile));
                    System.exit(0);
                }
                propsFilePath = propsFile;
                String type = (String) arguments.get("-type");
                if (StringUtils.isBlank(type) || "common".equalsIgnoreCase(type)) {
                    genFile(configfile);
                } else if ("dict".equalsIgnoreCase(type)) {
                    try {
                        CodeGenerateUtils.main(null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    private static void usage() {
        String lines = Messages.getString("Usage.Lines");
        int iLines = Integer.parseInt(lines);

        for (int i = 0; i < iLines; ++i) {
            String key = "Usage." + i;
            writeLine(Messages.getString(key));
        }

    }

    private static void writeLine(String message) {
        System.out.println(message);
    }

    private static void writeLine() {
        System.out.println();
    }

    private static Map<String, String> parseCommandLine(String[] args) {
        List<String> errors = new ArrayList();
        Map<String, String> arguments = new HashMap();
        for (int i = 0; i < args.length; ++i) {
            System.out.println(args[i]);
        }
        for (int i = 0; i < args.length; ++i) {
            if ("-configfile".equalsIgnoreCase(args[i])) {
                if (i + 1 < args.length) {
                    arguments.put("-configfile", args[i + 1]);
                } else {
                    errors.add(Messages.getString("RuntimeError.19", "-configfile"));
                }
                ++i;
            }
            if ("-propsFile".equalsIgnoreCase(args[i])) {
                if (i + 1 < args.length) {
                    arguments.put("-propsFile", args[i + 1]);
                } else {
                    errors.add(Messages.getString("RuntimeError.19", "-propsFile"));
                }
                ++i;
            }
            if ("-type".equalsIgnoreCase(args[i])) {
                if (i + 1 < args.length) {
                    arguments.put("-type", args[i + 1]);
                } else {
                    errors.add(Messages.getString("RuntimeError.19", "-type"));
                }
                ++i;
            }

        }

        if (!errors.isEmpty()) {
            Iterator var5 = errors.iterator();

            while (var5.hasNext()) {
                String error = (String) var5.next();
                writeLine(error);
            }

            System.exit(-1);
        }

        return arguments;
    }
}
