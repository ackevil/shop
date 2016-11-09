package com.huituopin.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

/**
 * 
 * 
 * Depiction： 日期工具类
 */
@Entity
public class DateUtil {

    private static Log log = LogFactory.getLog(DateUtil.class);

    /**
     * 把java.util.Date对象格式化成指定格式的string类型
     * 
     * @param patternStr
     *            格式化后的格式
     * @param date
     * @return
     */
    public static String formatDate(String patternStr, Date date) {
        try {
            if (date == null) {
                return "";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(patternStr);
            return sdf.format(date);
        } catch (Exception e) {
            log.error("\r\n", e);
        }
        return "";
    }

    /**
     * 将制定的Date对象格式化为默认的yyyy-MM-dd格式
     * 
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        Assert.notNull(date, "时间对象不能为空");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * @param dateString
     *            20060100,20051231,20060101 yyyy-MM-dd/yyyyMMdd/yyyyMM
     * @return
     */
    public static Date parse(String dateString) {
        try {
            if (dateString.length() <= 6) {
                return new SimpleDateFormat("yyyyMM").parse(dateString);
            } else if (dateString.indexOf("-") < 0 && dateString.length() == 8) {
                return new SimpleDateFormat("yyyyMMdd").parse(dateString);
            } else if (dateString.indexOf("-") < 0 && dateString.length() == 14) {
                return new SimpleDateFormat("yyyyMMddHHmmss").parse(dateString);
            } else if (dateString.length() == 7 && dateString.indexOf("-") > 0) {
                return new SimpleDateFormat("yyyy-MM").parse(dateString);
            } else if (dateString.length() == 19 && dateString.indexOf("-") > 0 && dateString.indexOf(":") > 0) {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
            } else {
                return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断某日期是否为今天
     * 
     * @param date
     * @return
     */
    public static boolean isToday(Date date) {
        if (date == null) {
            return false;
        }
        Calendar a = Calendar.getInstance();
        Calendar b = Calendar.getInstance();
        b.setTime(date);
        return a.get(Calendar.DAY_OF_MONTH) == b.get(Calendar.DAY_OF_MONTH)
                && a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.YEAR) == b.get(Calendar.YEAR);
    }

    /**
     * 判断某日期是否为本周
     * 
     * @param date
     * @return
     */
    public static boolean isWeek(Date date) {
        if (date == null) {
            return false;
        }
        Calendar a = Calendar.getInstance();
        Calendar b = Calendar.getInstance();
        b.setTime(date);
        return a.get(Calendar.WEEK_OF_MONTH) == b.get(Calendar.WEEK_OF_MONTH)
                && a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.YEAR) == b.get(Calendar.YEAR);
    }

    /**
     * 当前时间增加指定分钟数后得到的时间
     * 
     * @param minuteAmount
     * @return
     */
    public static Date addMinutes(int minuteAmount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, minuteAmount);
        return calendar.getTime();
    }

    /**
     * 当前时间增加指定分钟数后得到的时间
     * 
     * @param minuteAmount
     * @return
     */
    public static Date addMinutes(Date d, int minuteAmount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.MINUTE, minuteAmount);
        return calendar.getTime();
    }

    /**
     * 当前时间增加指定天数后得到的时间
     * 
     * @param dayAmount
     * @return
     */
    public static Date addDays(int dayAmount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, dayAmount);
        return calendar.getTime();
    }

    /**
     * 由当前时间得本周第一天
     * 
     * @param args
     */
    public static Date dayOfWeek() {
        Calendar c = Calendar.getInstance();
        int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayofweek == 0)
            dayofweek = 7;
        c.add(Calendar.DATE, -dayofweek + 1);
        return c.getTime();
    }

    /**
     * 由当前时间得本周最后一天
     * 
     * @param args
     */
    public static Date endOfWeek() {
        Calendar c = Calendar.getInstance();
        int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayofweek == 0)
            dayofweek = 7;
        c.add(Calendar.DATE, -dayofweek + 7);
        return c.getTime();
    }

    /**
     * 由当前时间得本月第一天
     * 
     * @author tangjingyuan
     * @info 本月的首日日期，获取的时钟不精确
     * @param args
     */
    public static Date dayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(GregorianCalendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 由当前时间得本月第一天
     * 
     * @author tangjingyuan
     * @info 本月的首日日期，获取的时钟不精确
     * @param args
     */
    public static Long OnedayOfMonth() {
        long monTime = DateUtil.parse(DateUtil.formatDate("yyyyMMdd", DateUtil.dayOfMonth()) + "000000").getTime(); // 本月第一天
        return monTime;
    }

    /**
     * 由当前时间得本月最后一天
     * 
     * @param args
     */
    public static Date endOfMonth() {
        Calendar calendar = Calendar.getInstance();
        // 设置时间,当前时间不用设置
        // calendar.setTime(new Date());
        // 设置日期为本月最大日期
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getTime();
    }

    /**
     * 由当前时间得今年第一天
     * 
     * @param args
     */
    public static Date dayOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(GregorianCalendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        System.out.println(addMinutes(50));
    }
}
