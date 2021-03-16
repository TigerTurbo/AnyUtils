package com.yshuoo.anyutils.json;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.yshuoo.anyutils.DateTimeConstant;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Copyright (c) 1999-2017 携程旅行网
 * all rights reserved
 *
 * @author duwl
 * 2017/5/15.9:32
 */
@Slf4j
public class JacksonSerializer {
    private static final JacksonSerializer DEFAULT = new JacksonSerializer();
    /**
     * 线程安全(官方:can reuse, share globally)
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {

        /**
         * 序列化的规则
         */
        MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        MAPPER.disable(SerializationFeature.INDENT_OUTPUT);
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature(), true);
        MAPPER.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        MAPPER.configure(JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS.mappedFeature(), true);
        MAPPER.configure(JsonReadFeature.ALLOW_LEADING_ZEROS_FOR_NUMBERS.mappedFeature(), true);
        MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        MAPPER.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
        MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        SimpleModule module = new SimpleModule();
        module.addSerializer(DateTime.class, new DateTimeSerializer());
        module.addDeserializer(DateTime.class, new DateTimeDeserializer());
        module.addDeserializer(Calendar.class, new CalendarDeserializer());
        module.addSerializer(Calendar.class, new CalendarSerializer());
        MAPPER.registerModule(module);
    }

    public static JacksonSerializer defaultSerializer() {
        return DEFAULT;
    }

    /**
     * 使用jackson序列化对象
     *
     * @param data object
     * @return json string
     */
    public String serialize(Object data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (IOException e) {
            log.error("serialize data error", e);
            return null;
        }
    }

    public <T> T deserialize(String content, Class<T> clazz) {
        try {
            return MAPPER.readValue(content, clazz);
        } catch (IOException e) {
            log.error("serialize data error", e);
            return null;
        }
    }

    /**
     * 反序列化集合
     *
     * @param content
     * @param clazz
     * @param <T>
     * @return
     */
    public <T, E> T deserialize(String content, Class<T> clazz, Class<E> elementClass) {
        try {
            JavaType javaType = getCollectionType(clazz, elementClass);
            return MAPPER.readValue(content, javaType);
        } catch (IOException e) {
            log.error("serialize data error", e);
            return null;
        }
    }

    private static <T, E> JavaType getCollectionType(Class<T> clazz, Class<E> elementClass) {
        return MAPPER.getTypeFactory().constructParametricType(clazz, elementClass);
    }

    private static class DateTimeSerializer extends JsonSerializer<DateTime> {
        @Override
        public void serialize(DateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeString(value.toString(DateTimeConstant.JODA_FORMATTER));
        }
    }

    private static class CalendarSerializer extends JsonSerializer<Calendar> {
        @Override
        public void serialize(Calendar value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeString(DateTimeConstant.ZONED_DATE_FORMATTER
                    .format(ZonedDateTime.ofInstant(value.toInstant(), ZoneId.of("+08:00"))));
        }
    }

    private static class DateTimeDeserializer extends JsonDeserializer<DateTime> {
        @Override
        public DateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            JsonNode node = p.getCodec().readTree(p);
            String s = node.asText();
            return DateTime.parse(s, DateTimeConstant.JODA_FORMATTER);
        }
    }

    private static class CalendarDeserializer extends JsonDeserializer<Calendar> {
        @Override
        public Calendar deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            JsonNode node = p.getCodec().readTree(p);
            String s = node.asText();
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(s, DateTimeConstant.ZONED_DATE_FORMATTER);
            return GregorianCalendar.from(zonedDateTime);
        }
    }
}
