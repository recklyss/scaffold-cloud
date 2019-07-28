package com.cms.scaffold.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * sql结果类工具方法
 */
public class ResultSetUtils {
    /**
     * 有选择性地将resultSet转换成List<Map>
     *
     * @param rs
     * @param exclude 不需要复制的属性名数组
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> resultSetToMap(ResultSet rs, String[] exclude)
            throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (rs == null) {
            return list;
        }

        String formatStr = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);

        rs.beforeFirst();
        while (rs.next()) {
            ResultSetMetaData rsmd = rs.getMetaData();
            Map<String, Object> map = new HashMap<String, Object>();
            int colNum = rsmd.getColumnCount();
            String columnName = "";
            String columnClassName = "";
            Date date = null;
            for (int i = 1; i <= colNum; i++) {
                columnName = rsmd.getColumnName(i).toUpperCase();
                columnClassName = rsmd.getColumnClassName(i);

                boolean excld = false;
                for (int j = 0; exclude != null && j < exclude.length; j++) {
                    if (columnName.equalsIgnoreCase(exclude[j])) {
                        excld = true;
                        break;
                    }
                }

                if (excld) {
                    continue;
                }

                if ("java.sql.Timestamp".equals(columnClassName)) {
                    date = rs.getDate(i);
                    if (date == null) {
                        map.put(columnName.toUpperCase(), "");
                    } else {
                        map.put(columnName.toUpperCase(), sdf.format(date));
                    }
                } else {
                    map.put(columnName.toUpperCase(), rs.getString(i));
                }
            }

            list.add(map);
        }

        return list;
    }

    /**
     * resultSet转换成List<Map>
     *
     * @param rs
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> resultSetToMap(ResultSet rs) throws Exception {
        return resultSetToMap(rs, null);
    }



    /**
     * 值拷贝，不进行类型转换
     *
     * @param dest
     * @param map
     * @param exclude 不需要复制的属性名数组
     * @throws Exception
     */
    public static void copyProperties(Object dest, Map map, String[] exclude)
            throws Exception {
        Method[] destMethods = dest.getClass().getDeclaredMethods();
        String formatStr = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);

        for (int i = 0; destMethods != null && i < destMethods.length; i++) {
            Method destSetterMethod = null;
            if (destMethods[i].getName().startsWith("set")) {
                destSetterMethod = destMethods[i];
            }
            if (destSetterMethod == null) {
                continue;
            }

            // setter参数只能有一个
            if (destSetterMethod.getParameterTypes().length != 1) {
                continue;
            }

            String fieldName = destSetterMethod.getName().substring(3, 4).toLowerCase()
                    + destSetterMethod.getName().substring(4);
            boolean excld = false;
            for (int j = 0; exclude != null && j < exclude.length; j++) {
                if (fieldName.equalsIgnoreCase(exclude[j])) {
                    System.out.println();
                    excld = true;
                    break;
                }
            }
            if (excld) {
                continue;
            }
            StringBuffer key = new StringBuffer();
            for (int j = 0; j < fieldName.length(); j++) {
                char cTemp = fieldName.charAt(j);
                if ((int) cTemp >= 65 && (int) cTemp <= 90) {
                    key.append("_");
                }
                key.append(cTemp);
            }

            Object temp = map.get(key.toString().toUpperCase());
            //map里面有没有值不进行处理
            if (temp == null || "".equals(temp)) {
                continue;
            }

            Class paramTypes = destSetterMethod.getParameterTypes()[0];
            if ("java.util.Date".equals(paramTypes.getCanonicalName())
                    && "java.lang.String".equals(temp.getClass().getCanonicalName())) {
                Date date = null;
                try {
                    date = sdf.parse(temp.toString());
                } catch (Exception e) {
                    //解析出错，该字段不进行赋值
                    continue;
                }
                destSetterMethod.invoke(dest, date);
            } else {
                destSetterMethod.invoke(dest, temp);
            }
        }
    }

    /**
     * 值拷贝，不进行类型转换
     *
     * @param dest
     * @param map
     * @throws Exception
     */
    public static void copyProperties(Object dest, Map map) throws Exception {
        copyProperties(dest, map, null);
    }

    /**
     * 把yyyy-MM-dd HH:mm:ss 格式字符串 转换为yyyyMMdd
     *
     * @param timeStr
     * @return
     */
    public static String chageTimsStr(String timeStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        try {
            Date dateTemp = sdf.parse(timeStr);
            timeStr = sdf2.format(dateTemp);
        } catch (Exception e) {
        }
        return timeStr;
    }

    /**
     * 方法说明：转换map中key的类型，如APP_MODE 变为 appMode
     * <p>
     * Author：        dekey
     * Create Date：   2014-4-11 上午11:38:20
     * History:  2014-4-11 上午11:38:20   dekey   Created.
     *
     * @param map
     * @param clude 不需要装换的key的数组
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map ChangeMapKeyType(Map map, String[] clude) {
        Map coypMap = new HashMap<String, Object>();
        coypMap.putAll(map);
        Set<String> set = coypMap.keySet();
        if (clude != null && clude.length > 0) {
            Set cludeSet = new HashSet(Arrays.asList(clude));
            set.removeAll(cludeSet);
        }
        Iterator item = set.iterator();
        while (item.hasNext()) {
            String key = (String) item.next();
            Object value = coypMap.get(key);
            map.put(toCamelCase(key), value);
        }
        return map;
    }

    /**
     * 方法说明：全部转换map中key的类型
     * <p>
     * Author：        dekey
     * Create Date：   2014-4-11 上午11:38:20
     * History:  2014-4-11 上午11:38:20   dekey   Created.
     *
     * @param map
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map ChangeMapKeyType(Map map) {
        return ChangeMapKeyType(map, null);
    }

    /**
     * 方法说明： 把APP_MODE 变为 appMode  格式一定要正确
     * <p>
     * Author：        dekey
     * Create Date：   2014-4-11 上午11:04:41
     * History:  2014-4-11 上午11:04:41   dekey   Created.
     *
     * @param key
     * @return
     */
    public static String toCamelCase(String key) {
        if (key != null && !"".equals(key)) {
            key = key.toLowerCase();
            String[] strArray = key.split("_");
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < strArray.length; i++) {
                if (i == 0) {
                    buffer.append(strArray[i]);
                } else {
                    buffer.append(strArray[i].substring(0, 1).toUpperCase() + strArray[i].substring(1));
                }
            }
            key = buffer.toString();
        }
        return key;
    }

    /**
     * 方法说明： 正则表达式 把APP_MODE 变为 appMode
     * <p>
     * Author：        dekey
     * Create Date：   2014-4-11 上午10:47:37
     * History:  2014-4-11 上午10:47:37   dekey   Created.
     *
     * @param key
     * @return
     */
    public static String regexToCamelCase(String key) {
        key = key.toLowerCase();
        String regex = "(_+[a-z])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(key);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String replacement = matcher.group(1).replace("_", "").toUpperCase();
            matcher.appendReplacement(sb, replacement);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static <T> List<T> convertToList(ResultSet rs, Class<T> t) throws SQLException{
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
        int columnCount = md.getColumnCount();
        while (rs.next()) {
            Map<String, Object> rowData = new HashMap<String, Object>();
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(rowData);
        }
        List<T> resultList = JSONArray.parseArray(JSONObject.toJSONString(list),t);

        return resultList;
    }

}
