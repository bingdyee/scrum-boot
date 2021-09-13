package com.rechaox.scrum.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Bing D. Yee
 * @since 2021/04/07
 */
public final class JacksonUtils {

    static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * 对象转JSON
     *
     * @param obj 对象
     * @return JSON
     */
    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * JSON转对象
     *
     * @param json JSON
     * @param cls 对象Class
     * @param <T> 范型
     * @return 对象
     */
    public static <T> T toObject(String json,  Class<T> cls) {
        try {
            return mapper.readValue(json, cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 读取JSON输入转对象
     *
     * @param inputStream JSON输入流
     * @param cls 对象Class
     * @param <T> 范型
     * @return 对象
     */
    public static <T> T toObject(InputStream inputStream, Class<T> cls) {
        try {
            return mapper.readValue(inputStream, cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
