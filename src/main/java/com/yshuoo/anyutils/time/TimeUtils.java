package com.yshuoo.anyutils.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author yangshuo
 * @date 2018/11/13 14:52
 */
public class TimeUtils {

    private static DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public static Long timeStringToLong(String time){
        LocalDate date = LocalDate.parse(time, ftf);
        // 毫秒级的long
        return LocalDate.from(date).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static String timeLongToString(Long time){
        return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
    }

}
