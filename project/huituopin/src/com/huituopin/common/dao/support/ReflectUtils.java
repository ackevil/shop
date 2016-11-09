package com.huituopin.common.dao.support;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.Entity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Generics的util类.
 * 
 * @author springside
 * 
 */
@Entity
public class ReflectUtils {
    private static final Log log = LogFactory.getLog(ReflectUtils.class);

    private ReflectUtils() {
    }

    public static Class getClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型. 如public BookManager extends
     * GenricManager<Book>
     * 
     * @param clazz
     *            clazz The class to introspect
     * @param index
     *            the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or <code>Object.class</code> if
     *         cannot be determined
     */
    public static Class getSuperClassGenricType(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }
}
