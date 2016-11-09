package com.huituopin.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public class ParseJsonToObj {

    public static Object parse(Object obj, String str) {
        JSONObject object = JSONObject.fromObject(str);
        List fieldsInfo = getFiledsInfo(obj);

        for (int i = 0; i < fieldsInfo.size(); i++) {
            Map field = (Map) fieldsInfo.get(i);
            String fieldName = (String) field.get("name");

            if (!object.has(StringUtil.ToUpperCase(fieldName))) {
                continue;
            }
            String fieldNameVaule = object.getString(StringUtil.ToUpperCase(fieldName));
            Class c = ((Class) field.get("type"));
            String typeName = c.getName();
            if (Integer.class.getName().equals(typeName)) {
                Integer intResult = new Integer(0);
                if (!StringUtil.isEmpty(fieldNameVaule)) {
                    intResult = Integer.parseInt(fieldNameVaule);
                }
                setFieldValueByName(obj, fieldName, c, intResult);
            }
            if (Double.class.getName().equals(typeName)) {
                Double doubleResult = new Double(0);
                if (!StringUtil.isEmpty(fieldNameVaule)) {
                    doubleResult = Double.parseDouble(fieldNameVaule);
                }
                setFieldValueByName(obj, fieldName, c, doubleResult);
            }
            if (BigDecimal.class.getName().equals(typeName)) {
                BigDecimal bd = null;
                if (!StringUtil.isEmpty(fieldNameVaule)) {
                    bd = new BigDecimal(fieldNameVaule);
                } else {
                    bd = new BigDecimal(0);
                }
                setFieldValueByName(obj, fieldName, c, bd);
            }
            if (Timestamp.class.getName().equals(typeName)) {
                Timestamp timestamp = null;
                if (!StringUtil.isEmpty(fieldNameVaule)) {
                    String[] times = fieldNameVaule.split("-");
                    timestamp = new Timestamp(Integer.parseInt(times[0]) - 1900, Integer.parseInt(times[1]) - 1,
                            Integer.parseInt(times[2]), 0, 0, 0, 0);

                } else {
                    timestamp = new Timestamp(0);
                }
                setFieldValueByName(obj, fieldName, c, timestamp);
            }
            if (String.class.getName().equals(typeName)) {
                setFieldValueByName(obj, fieldName, c, fieldNameVaule);
            }
            if (Double.class.getName().equals(typeName)) {
                double dou = 0;
                if (!StringUtil.isEmpty(fieldNameVaule)) {
                    dou = Double.parseDouble(fieldNameVaule);
                }
                setFieldValueByName(obj, fieldName, c, dou);
            }
        }

        return obj;
    }

    /**
     * 根据属性名设置属性值
     * */
    private static void setFieldValueByName(Object obj, String fieldName, Class c, Object parm) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String setter = "set" + firstLetter + fieldName.substring(1);
            Method method = obj.getClass().getDeclaredMethod(setter, c);
            method.invoke(obj, new Object[] { parm });
        } catch (Exception e) {
        }
    }

    /**
     * 根据属性名获取属性值
     * */
    private static Object getFieldValueByName(Object obj, String fieldName) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = obj.getClass().getDeclaredMethod(getter, new Class[] {});
            return (Object) method.invoke(obj, new Object[] {});
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取属性名数组
     * */
    private String[] getFiledName(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * 获取属性类型(type)，属性名(name)的map组成的list
     * */
    public static List getFiledsInfo(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        List list = new ArrayList();
        Map infoMap = null;
        for (int i = 0; i < fields.length; i++) {
            infoMap = new HashMap();
            infoMap.put("type", fields[i].getType());
            infoMap.put("name", fields[i].getName());
            infoMap.put("value", getFieldValueByName(o, fields[i].getName()));
            list.add(infoMap);
        }
        return list;
    }
}
