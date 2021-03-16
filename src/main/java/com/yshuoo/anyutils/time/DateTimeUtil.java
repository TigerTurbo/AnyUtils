package com.yshuoo.anyutils.time;


import org.joda.time.DateTime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

import static com.yshuoo.anyutils.NumberConstant.ONE;
import static com.yshuoo.anyutils.NumberConstant.ZERO;

/**
 *
 * @author yangshuo
 * 2019/3/6
 */
public class DateTimeUtil {
    public static final int HUNDRED = 100;
    public static final long HUNDRED_MILLION = 100000000;
    public static final long MILLION = 1000000;
    private static final ZoneId UTC = ZoneId.of("UTC");
    public static int getIntegerDate(DateTime dateTime) {
        int y = dateTime.getYear();
        int m = dateTime.getMonthOfYear();
        int d = dateTime.getDayOfMonth();
        return y * HUNDRED * HUNDRED + m * HUNDRED + d;
    }

    public static int getIntegerDate(Calendar c) {
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH) + ONE;
        int d = c.get(Calendar.DAY_OF_MONTH);
        return y * HUNDRED * HUNDRED + m * HUNDRED + d;
    }

    /**
     * 8位int转dateTime
     *
     * @param date 8位int日期  20180408
     * @return DateTime
     */
    public static DateTime convertDateToDateTime(int date) {
        int days = date % HUNDRED;
        date /= HUNDRED;
        int months = date % HUNDRED;
        date /= HUNDRED;
        int years = date;
        return new DateTime(years, months, days, ZERO, ZERO);
    }


    public static int getIntegerDate(LocalDate c) {
        if (c == null) {
            return ZERO;
        }
        int y = c.getYear();
        int m = c.getMonthValue();
        int d = c.getDayOfMonth();
        return y * HUNDRED * HUNDRED + m * HUNDRED + d;
    }

    public static int getIntegerYearDate(DateTime dateTime) {
        int y = dateTime.getYear() % HUNDRED;
        int m = dateTime.getMonthOfYear();
        int d = dateTime.getDayOfMonth();
        return y * HUNDRED * HUNDRED + m * HUNDRED + d;
    }

    /**
     * yyyyMMddHHmm
     *
     * @param localDate localDate
     * @return
     */
    public static long getLongTime(LocalDateTime localDate) {
        return localDate.getYear() * HUNDRED_MILLION + localDate.getMonthValue() * MILLION
                + localDate.getDayOfMonth() * HUNDRED * HUNDRED
                + localDate.getHour() * HUNDRED + localDate.getMinute();
    }

    /**
     * 将long类型的timestamp转为LocalDateTime
     * @param timestamp
     * @return
     */
    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, UTC);
    }
}
