package com.boot.util;


import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;


/**
 * json帮助类
 *
 * @author LTM
 */
public class JsonHelper {
    /**
     * <br>把object对象转换成json字符串
     *
     * @param obj object及其子类
     * @return
     */
    public static String encode(Object obj) {
        if (obj == null || obj.toString().equals("null"))
            return null;
        if (obj != null && obj.getClass() == String.class) {
            return obj.toString();
        }
        JSONSerializer serializer = new JSONSerializer();
        serializer.transform(new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"),
                Date.class);
        serializer.transform(new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"),
                Timestamp.class);
        return serializer.deepSerialize(obj);
    }

    /**
     * <br>把object对象(对象中包含其他对象属性)转换成json字符串
     *
     * @param obj    object及其子类
     * @param params obj属性对象名
     * @return
     */
    public static String encode(Object obj, String... params) {
        if (obj == null || obj.toString().equals("null"))
            return null;
        if (obj != null && obj.getClass() == String.class) {
            return obj.toString();
        }
        JSONSerializer serializer = new JSONSerializer();
        serializer.transform(new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"),
                Date.class);
        serializer.transform(new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"),
                Timestamp.class);
        serializer = serializer.include(params);
        return serializer.serialize(obj);
    }

    /**
     * <br>把json类型字符串转换成object对象
     *
     * @param json
     * @return object对象
     */
    public static Object decode(String json) {
        if (StringUtils.isBlank(json))
            return "";
        JSONDeserializer deserializer = new JSONDeserializer();
        Object obj = deserializer.deserialize(json);
        if (obj != null && obj.getClass() == String.class) {
            return decode(obj.toString());
        }
        return obj;
    }

    /**
     * <br>把json类型字符串转换成map对象
     *
     * @param json json类型字符串
     * @return map对象
     */
    public static Map<String, Object> decodeToMap(String json) {
        Object objFromJsonStr = decode(json);
        Map<String, Object> resultMap = (Map<String, Object>) objFromJsonStr;
        return resultMap;
        
    }

    /**
     * <br>把json类型字符串转换成指定对象
     *
     * @param <T>    对象class对象
     * @param source json类型字符串
     * @param bean   对象class对象
     * @return
     */
    public static <T> T decodeToAssignedType(String source, Class<T> bean) {
        JSONDeserializer deserializer = new JSONDeserializer<T>();
        deserializer.use(Date.class, new DateTransformer(
                "yyyy-MM-dd'T'HH:mm:ss"));
        Object obj = deserializer.deserialize(source, bean);
        if (obj != null && obj.getClass() == String.class) {
            return decodeToAssignedType(obj.toString(), bean);
        }
        return (T) obj;
    }

    // add
    public static Object decode2(String json) {
        if (json == null || "".equals(json.toString())) return "";
        JSONDeserializer deserializer = new JSONDeserializer();
        //deserializer.use(String.class, new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"));
        Object obj = deserializer.deserialize(json);
        if (obj != null && obj.getClass() == String.class) {
            return decode2(obj.toString());
        }
        return obj;
    }
   
    
    public static Object decodeforDate(String json) {
        if (json == null || "".equals(json.toString())) return "";
        JSONDeserializer deserializer = new JSONDeserializer();
        deserializer.use(String.class, new DateTransformer("yyyy-MM-dd\'T\'HH:mm:ss"));
        Object obj = deserializer.deserialize(json);
        if (obj != null && obj.getClass() == String.class) {
            return decodeforDate(obj.toString());
        }
        return obj;
    }
}
