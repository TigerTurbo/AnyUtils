package com.yshuoo.anyutils.time;


import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author yangshuo
 * @date 2019/6/25 16:07
 */
public class CalendarUtil {

    /**
     * 把 <tt>str</tt> 的时间日期字符串反序列化成<tt>Calendar</tt> 类型
     *
     * @param str
     * @return Calendar
     */
    public static Calendar toCalendar(String str, String format) throws Exception {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(str.concat("is not valid"));
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 格式化<tt>Calendar</tt> 类型为 <tt>String</tt>
     * Pattern: <tt>FORMAT</tt>
     *
     * @param calendar
     * @return String
     */
    public static String formatDateTime(Calendar calendar, String dateTime) {
        if (calendar == null) {
            return StringUtils.EMPTY;
        }
        TimeZone timeZone = calendar.getTimeZone();
        timeZone.setRawOffset(calendar.get(Calendar.ZONE_OFFSET));
        DateFormat dateFormat = new SimpleDateFormat(dateTime);
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(calendar.getTime());
    }

}
