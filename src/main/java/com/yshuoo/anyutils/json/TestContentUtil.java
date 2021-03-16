package com.yshuoo.anyutils.json;

import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * 输出文本到文件
 *
 */
@Slf4j
@SuppressWarnings({"javadoc", "unused"})
public class TestContentUtil {
    /**
     * 从 resource 目录读取fatstjson json对象
     *
     * @param clazz
     * @param path
     * @param <T>
     * @return
     */
    public static <T> T convertFromFile(Class<T> clazz, String path) {
        String s;
        StringBuilder sb = new StringBuilder();
        try (InputStream inputStream = TestContentUtil.class.getClassLoader().getResourceAsStream(path);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s);
            }
        } catch (IOException e) {
            log.error("ex", e);
        }
        return JacksonSerializer.defaultSerializer().deserialize(sb.toString(), clazz);
    }

    /**
     * 从 resource 目录读取fatstjson json对象
     *
     * @param elementClass 泛型类型
     * @return
     */
    public static <E> List convertListFromFile(Class<E> elementClass, String path) {
        String s;
        StringBuilder sb = new StringBuilder();
        try (InputStream inputStream = TestContentUtil.class.getClassLoader().getResourceAsStream(path);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s);
            }
        } catch (IOException e) {
            log.error("ex", e);
        }
        return JacksonSerializer.defaultSerializer().deserialize(sb.toString(), List.class, elementClass);
    }

    /**
     * protobuf
     *
     * @param path
     * @param builder
     * @return
     */
    public static Message loadProtobuf(String path, Message.Builder builder) {
        InputStream inputStream = TestContentUtil.class.getClassLoader().getResourceAsStream(path);
        try {
            JsonFormat.parser().merge(new InputStreamReader(inputStream), builder);
        } catch (IOException e) {
        }
        return builder.build();
    }

    /**
     * protobuf builder
     *
     * @param path
     * @param builder
     * @return
     */
    public static void loadProtobufBuilder(String path, Message.Builder builder) throws IOException {
        InputStream inputStream = TestContentUtil.class.getClassLoader().getResourceAsStream(path);
        JsonFormat.parser().merge(new InputStreamReader(inputStream), builder);
    }
}
