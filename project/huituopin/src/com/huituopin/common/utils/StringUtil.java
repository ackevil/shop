package com.huituopin.common.utils;

import javax.persistence.Entity;

@Entity
public class StringUtil {

    public static boolean isEmpty(String str) {

        if (str != null && !"".equals(str)) {
            return false;
        } else {
            return true;
        }
    }

    // 将字符串首字符从小写变大写，只适用于英文
    public static String ToUpperCase(String str) {
        String firstLetter = str.substring(0, 1).toUpperCase();
        return firstLetter + str.substring(1);
    }

    // 将字符串首字符从大写变小写，只适用于英文
    public static String ToLowerCase(String str) {
        String firstLetter = str.substring(0, 1).toLowerCase();
        return firstLetter + str.substring(1);
    }
}
