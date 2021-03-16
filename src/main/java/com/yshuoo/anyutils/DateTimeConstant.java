package com.yshuoo.anyutils;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;

import java.time.ZoneId;

/**
 *
 * @author yangshuo
 * 2017/5/8.16:36
 */
public class DateTimeConstant {
    /**
     * 日期格式
     */
    public static final DateTimeFormatter DATE_FORMATTER_1 = DateTimeFormat.forPattern("yyyy-MM-dd");
    /**
     * 日期格式二
     */
    public static final DateTimeFormatter DATE_FORMATTER_2 = DateTimeFormat.forPattern("yyyy/MM/dd");

    /**
     * 用户行程 日期格式三
     */
    public static final DateTimeFormatter SIMPLE_DATE_FORMATTER = DateTimeFormat.forPattern("yyyyMMdd");

    public static final DateTimeFormatter FLIGHT_DATE_TIME_FORMATTER = DateTimeFormat.forPattern("yyyy-M-d H:mm:ss")
            .withZoneUTC();

    public static final java.time.format.DateTimeFormatter FLIGHT_DATE_TIME_FORMATTER_1 =
            java.time.format.DateTimeFormatter.ofPattern("yyyy-M-d H:mm:ss");

    public static final java.time.format.DateTimeFormatter DEAD_LINE_DATE_FORMATTER_2 =
            java.time.format.DateTimeFormatter.ofPattern("yyyy-M-d H:mm:ss").withZone(ZoneId.of("+00:00"));

    /**
     * 出票截至时间 pattern
     */
    public static final DateTimeFormatter DEAD_LINE_DATE_FORMATTER =
            DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    /**
     * 出票截至时间 pattern
     */
    public static final java.time.format.DateTimeFormatter DEAD_LINE_DATE_FORMATTER_1 =
            java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSz");
    /**
     * 日期格式
     */
    public static final java.time.format.DateTimeFormatter DATE_FORMATTER =
            java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    /**
     * 格式列表
     */
    public static final DateTimeParser[] PARSERS = {
            DATE_FORMATTER_1.getParser(),
            DATE_FORMATTER_2.getParser(),
            SIMPLE_DATE_FORMATTER.getParser()};


    /**
     * 所有的日期格式
     */
    public static final DateTimeFormatter ALL_DATE_FORMATTER = new DateTimeFormatterBuilder()
            .append(null, PARSERS).toFormatter();

    public static final DateTimeFormatter JODA_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter JODA_FLIGHT_TIME_FORMATTER_1 =
            DateTimeFormat.forPattern("yyyyMMddHHmmss");

    public static final DateTimeFormatter JODA_FLIGHT_TIME_FORMATTER_2 =
            DateTimeFormat.forPattern("yyyyMMddHHmm");

    public static final DateTimeFormatter JODA_FLIGHT_TIME_FORMATTER_3 =
            DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static final java.time.format.DateTimeFormatter FLIGHT_TIME_FORMATTER_1 =
            java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    public static final java.time.format.DateTimeFormatter YY_MM_DD_HH_MM =
            java.time.format.DateTimeFormatter.ofPattern("yyMMddHHmm");
    public static final java.time.format.DateTimeFormatter ZONED_DATE_FORMATTER =
            java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");

    public static final java.time.format.DateTimeFormatter DATE_FORMATTER_3 =
            java.time.format.DateTimeFormatter.ofPattern("yyyy/M/d");

    public static final java.time.format.DateTimeFormatter DATE_FORMATTER_4 =
            java.time.format.DateTimeFormatter.ofPattern("yyyy-M-d");

    public static final java.time.format.DateTimeFormatter DATE_FORMATTER_5 =
            java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static final java.time.format.DateTimeFormatter DATE_FORMATTER_6 =
            java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final java.time.format.DateTimeFormatter DATE_FORMATTER_9 =
            java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static final java.time.format.DateTimeFormatter DATE_FORMATTER_10 =
            java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static final java.time.format.DateTimeFormatter DATE_FORMATTER_11 =
            java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public static final DateTimeParser[] FLIGHT_DATE_TIME = {
            JODA_FORMATTER.getParser(),
            JODA_FLIGHT_TIME_FORMATTER_1.getParser(),
            JODA_FLIGHT_TIME_FORMATTER_2.getParser(),
            JODA_FLIGHT_TIME_FORMATTER_3.getParser()
    };

    public static final DateTimeFormatter FLIGHT_DATE_TIME_FORMATTERS = new DateTimeFormatterBuilder()
            .append(null, FLIGHT_DATE_TIME).toFormatter();
}
