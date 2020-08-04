package com.biostime.bp.authorization.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


public final class JsonUtils {
    private JsonUtils() {}

    /**
     * 将对象序列化成json字符串
     * @param object javaBean
     * @return jsonString json字符串
     */
    public static String toJson(Object object) {
        try {
            return getInstance().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将json反序列化成对象
     * @param jsonString jsonString
     * @param valueType class
     * @param <T> T 泛型标记
     * @return Bean
     */
    public static <T> T parse(String jsonString, Class<T> valueType) {
        try {
            return getInstance().readValue(jsonString, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public static <T> T parse(String jsonString, TypeReference<T> typeReference) {
    	
        try {
            return getInstance().readValue(jsonString, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
    /*, new TypeReference<List<TestBean>>() {  
    }*/

    private static ObjectMapper getInstance() {
        return JacksonHolder.INSTANCE;
    }

    private static class JacksonHolder {
        private static final ObjectMapper INSTANCE = new  ObjectMapper();
        static{
        	INSTANCE.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
    }

}
