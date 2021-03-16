package com.yshuoo.anyutils.string;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

/**
 * 字符工具类
 */
public final class StringOperateUtils {

    private StringOperateUtils() {

    }

    public static final String L_BRACKET = "(";
    public static final String R_BRACKET = ")";
    public static final String COMMA = ",";
    public static final String APOSTROPHE = "'";
    public static final Splitter COMMA_SLASH = Splitter.on(COMMA).trimResults();
    public static final Joiner COMMA_JOIN = Joiner.on(COMMA);
    public static final String OR = "|";
    public static final Splitter SPLIT_OR = Splitter.on(OR).trimResults();
    public static final Joiner JOIN_OR = Joiner.on(OR);
    public static final String SLASH = "/";
    public static final Splitter SPLIT_SLASH = Splitter.on(SLASH).trimResults();
    public static final Joiner JOIN_SLASH = Joiner.on(SLASH);
    public static final String DASH = "-";
    public static final Splitter SPLIT_DASH = Splitter.on(DASH).trimResults();
    public static final Joiner JOIN_DASH = Joiner.on(DASH);

    public static String defaultString(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    public static List<String> orSplit(String obj) {
        return split(SPLIT_OR, obj);
    }

    public static String orJoin(Collection<String> obj) {
        return join(JOIN_OR, obj);
    }

    public static List<String> commaSplit(String obj) {
        return split(COMMA_SLASH, obj);
    }

    public static String commaJoin(Collection<String> obj) {
        return join(COMMA_JOIN, obj);
    }

    public static List<String> split(Splitter splitter, String obj) {
        return splitter.splitToList(obj);
    }

    public static String join(Joiner joiner, Collection<String> obj) {
        return joiner.join(obj);
    }

    public static List<String> slashSplit(String obj) {
        return split(SPLIT_SLASH, obj);
    }

    public static String slashJoin(Collection<String> obj) {
        return join(JOIN_SLASH, obj);
    }

    public static List<String> dashSplit(String obj) {
        return split(SPLIT_DASH, obj);
    }

    public static String dashJoin(Collection<String> obj) {
        return join(JOIN_DASH, obj);
    }

    public static String defaultEmtpyString(String str) {
        return StringUtils.isEmpty(str) ? "" : str;
    }
}
