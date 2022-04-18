package io.github.scrumboot.langs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Bing D. Yee
 * @since 2022/02/08
 */
public final class Jsons {

    static ObjectMapper mapper = new ObjectMapper();


    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException var2) {
            throw new RuntimeException(var2);
        }
    }

    public static <T> T toObject(String json, Class<T> cls) {
        try {
            return mapper.readValue(json, cls);
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static <T> T toObject(InputStream inputStream, Class<T> cls) {
        try {
            return mapper.readValue(inputStream, cls);
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static <T> T toObject(String content, TypeReference<T> valueTypeRef) {
        if (Strings.isBlank(content)) {
            return null;
        }
        try {
            return mapper.readValue(content, valueTypeRef);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T convertObject(Object obj, Class<T> cls) {
        return mapper.convertValue(obj, cls);
    }

    static {
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

}
