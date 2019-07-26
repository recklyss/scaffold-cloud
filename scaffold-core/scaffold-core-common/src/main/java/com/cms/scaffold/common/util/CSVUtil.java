package com.cms.scaffold.common.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zhangjiaheng@gmail.com
 * @date 2018-07-11
 * csv文件生成工具类
 */
public class CSVUtil {

    static Logger logger = LoggerFactory.getLogger(CSVUtil.class);

    /**
     * 指定读取的默认编码格式UTF-8
     * csv文件用excel打开乱码，需要用nodepad++转换成ANSI
     */
    private static final String DEAULT_CHARSET = "UTF-8";


    /**
     * 使用数据生成csv文本，并返回其字节，不生成文件
     * @param head 表头字段说明
     * @param dataList 数据集合
     * @return
     */
    public static byte[] createCsvTextByte(List<String> head, List<List<Object>> dataList) {
        StringBuilder strb = new StringBuilder();
        List<Object> headList = new ArrayList<>(head);
        strb.append(appendLine(headList));
        for (int i = 0; i < dataList.size(); i++) {
            strb.append(appendLine(dataList.get(i)));
        }
        return strb.toString().getBytes();
    }

    /**
     * 写入一行csv格式文本，返回字符串
     * @param lineList
     * @return 拼接好的一行数据 数据和逗号分隔 最后为换行
     */
    public static StringBuilder appendLine(List<Object> lineList){
        StringBuilder strb = new StringBuilder();
        if(lineList.isEmpty()){
            return strb;
        }
        for (int i = 0; i < lineList.size()-1; i++) {
            strb.append(lineList.get(i)).append(",");
        }
        strb.append(lineList.get(lineList.size()-1)).append("\n");
        return strb;
    }

    /**
     * @param head              表头 String集合
     * @param dataList          数据集合
     * @param outPutPath        生成文件路径
     * @param filename          带.csv后缀文件名
     * @return 创建成功的csv文件
     */
    public static void createCsvFile(List<String> head, List<List<Object>> dataList,
                                     String outPutPath, String filename) throws Exception {
        File csvFile = null;
        if(null == outPutPath || "".equals(outPutPath.trim())){
            outPutPath = Class.class.getClass().getResource("/").getPath().substring(1)+"csv";
        }
        String filepath = outPutPath + File.separator + filename;
        logger.info("生成的文件路径：" + filepath);
        csvFile = new File(filepath);
        File parent = csvFile.getParentFile();
        if (null == parent || !parent.exists()) {
            parent.mkdirs();
        }
        // 创建文件
        csvFile.createNewFile();
        // 使用csv工具类将数据写入文件
        CsvWriterSettings csvWriterSettings = new CsvWriterSettings();
        CsvWriter csvWriter = new CsvWriter(new FileWriter(csvFile), csvWriterSettings);
        csvWriter.writeHeaders(head);
        csvWriter.writeRowsAndClose(dataList);
    }



     /**
     * 将查出来的列表按照指定的字段 转换为导出需要的数据集合格式
     * @param fieldNames 指定需要导出的列的字段名
     * @param BeanList 从数据库中查出的实体列表
     * @param convertorMap 字段转义map
     * @return 按照字段名生成的数据集合
     */
    public static List<List<Object>> changeBeanlistToDatalist(String[] fieldNames, List<Object> BeanList, Map convertorMap) throws Exception {
        List<List<Object>> dataList = new ArrayList<>();
        for (int i = 0; i < BeanList.size(); i++) {
            Object bean = BeanList.get(i);
            Map<String ,Object> beanMap = PropertyUtils.describe(bean);
            List<Object> bean2data = new ArrayList<>(fieldNames.length);
            for (int j = 0; j < fieldNames.length; j++) {
                // fileName是字段名字
                String fieldName = fieldNames[j];
                // 这个拿出来的是该字段的值
                Object data = beanMap.get(fieldName);
                boolean isDate = false;
                if(data instanceof Date){
                    isDate = true;
                    // 将时间类型的转换成 yyyy-MM-dd HH:mm:ss格式的文本
                    data = DateUtil.format((Date) data, "yyyy-MM-dd HH:mm:ss");
                }
                if(null != convertorMap && null != data){
                    Map nidMap = (Map)convertorMap.get(fieldName);
                    if(null != nidMap){
                        // 这里 data如果需要转义 需要用convertorMap进行处理
                        data = nidMap.get(data.toString());
                    }
                }
                if(null != data && !"".equals(data)){
                    // 如果data值是数字组成 并且长度大于12 加上table制表符 避免流水号订单号等被转成数值显示不完全
                    final boolean flag =
                            !isDate && (!NumberUtil.isNumber(data.toString()) || data.toString().length() > 12);
                    if(flag){
                        data = data.toString() + "\t";
                    }
                    // csv格式避免单元格中有英文逗号，用双引号引起来，避免存在双引号，则将原先的双引号转成两个双引号
                    data = "\"" + data.toString().replaceAll("\"","\"\"") + "\"";
                }
                bean2data.add(null == data ? "" : data);
            }
            dataList.add(bean2data);
        }
        return dataList;
    }

    /**
     * 将String集合转换为数组
     *
     * @param head
     * @return String 数组
     */
    private static String[] toStringArray(List<String> head) {
        String[] headStrAry = new String[head.size()];
        for (int i = 0; i< head.size(); i++) {
            headStrAry[i] = head.get(i);
        }
        return headStrAry;
    }

    /**
     * 默认编码读取csv文件
     *
     * @param file 需要读取的csv文件
     * @return
     */
    public static List<String[]> readCSV(File file) {
        return readCSV(file, DEAULT_CHARSET);
    }

    /**
     * @param file    需要读取的csv文件
     * @param charSet 指定读取的编码格式
     * @return list集合，内容为数据的字符串数组
     */
    public static List<String[]> readCSV(File file, String charSet) {
        List<String[]> allRows = null;
        try {
            InputStream in = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(in, charSet);
            CsvParserSettings settings = new CsvParserSettings();
            settings.getFormat().setLineSeparator("\n");
            CsvParser parser = new CsvParser(settings);
            //读取数据到列表
            allRows = parser.parseAll(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allRows;
    }

    /**
     * @param dataList
     * @return 返回数据记录组成字符串之后的字节,即一行数据逗号隔开的字符串，的字节数组
     */
    public static byte[] dataList2ByteAry(List<List<Object>> dataList) {
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < dataList.size(); i++) {
            strb.append(appendLine(dataList.get(i)));
        }
        return strb.toString().getBytes();
    }
}
