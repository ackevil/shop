package com.huituopin.common.utils;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Entity;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

@Entity
public class JsonUtil {

    /**
     * 把JSONObject 转换成指定的对象【慎用】（转换前提JSON key必须与实体类属性名相同，否则不可用）
     * 
     * @param t
     *            转换对象
     * @param json
     *            jsonObject
     * @return 转换对象
     * @throws InstantiationException
     */
    public static <T> T parseJsonToObject(T t, JSONObject json) throws InstantiationException {
        try {
            for (Field field : t.getClass().getDeclaredFields()) {
                boolean isacc = field.isAccessible();
                if (!json.containsKey(field.getName())) {
                    continue;
                }
                field.setAccessible(true);
                Object objectValue = json.get(field.getName());
                if (objectValue instanceof JSONNull) {
                    objectValue = null;
                    field.set(t, null);
                } else {
                    if (field.getType().getSimpleName().equals("Date")) { // 类型为日期时特殊处理
                        Date date = DateUtil.parse(json.getString(field.getName()));
                        field.set(t, date);
                    } else {
                        field.set(t, json.get(field.getName()));
                    }
                }

                field.setAccessible(isacc);
            }

        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 转换带日期的对象为json字 符串
     * 
     * @param obj
     * @param format
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static String parseObjectToJsonHaveDate(Object obj, String format) throws IllegalArgumentException,
            IllegalAccessException {

        JsonConfig jsonConfig = new JsonConfig();

        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor(format));
        jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new JsonDateValueProcessor(format));
        jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new JsonDateValueProcessor(format));
        String jsonString = JSONObject.fromObject(obj, jsonConfig).toString();

        return jsonString;
    }

    @Entity
	public static class JsonDateValueProcessor implements JsonValueProcessor {
        private String format = "yyyy-MM-dd";

        public JsonDateValueProcessor() {
            super();
        }

        public JsonDateValueProcessor(String format) {
            super();
            this.format = format;
        }

        @Override
        public Object processArrayValue(Object paramObject, JsonConfig paramJsonConfig) {
            return process(paramObject);
        }

        @Override
        public Object processObjectValue(String paramString, Object paramObject, JsonConfig paramJsonConfig) {
            return process(paramObject);
        }

        private Object process(Object value) {

            if (value instanceof Date) {
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
                return sdf.format(value);
            } else if (value instanceof java.sql.Date) {
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
                return sdf.format(value);
            } else if (value instanceof Timestamp) {
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
                return sdf.format(value);
            }
            return value == null ? "" : value.toString();
        }

    }

    public static void main(String[] args) {

    }

}
