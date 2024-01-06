package cn.novots.test.util;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * jackjson负责类* @author ztzh_gonghl * @date 2021年3月25日
 */
@SuppressWarnings("deprecation")
public class JacksonUtil {

    private static Logger log = LoggerFactory.getLogger(JacksonUtil.class);

    private static ObjectMapper mapper;

    /**
     * 设置一些通用的属性
     */
    static {
        mapper = new ObjectMapper();
        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        // mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        // 如果存在未知属性，则忽略不报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 允许key没有双引号
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许key有单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 允许整数以0开头
        mapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
        // 允许字符串中存在回车换行控制符
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        // 支持数组
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }

    /**
     * 对象转换JSON字符串* @param obj /** * @return
     */
    public static String toJSONString(Object obj) {
        return toJSONString(obj, false);
    }

    /**
     * 对象转换JSON字符串,并使用json格式打印* @param obj /** * @return
     */
    public static String toFormatJSONString(Object obj) {
        return toJSONString(obj, true);
    }

    private static String toJSONString(Object obj, boolean format) {
        if (obj == null) {
            return "";
        }
        try {
            if (obj instanceof String) {
                return obj.toString();
            }
            if (obj instanceof Number) {
                return obj.toString();
            }
            if (format) {
                return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            }
            return mapper.writeValueAsString(obj);
        } catch (Throwable e) {
            log.error("JacksonUtil-toJSONString={}", obj == null ? null : obj.toString());
        }
        return "";
    }

    /**
     * 字符串转对象 * @param value /** * @param tClass /** * @return
     */
    public static <T> T toJavaObject(String value, Class<T> tClass) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        try {
            return mapper.readValue(value, tClass);
        } catch (Throwable e) {
            log.error("JacksonUtil-toJavaObject-value={},class={}", value, tClass,e);
        }
        return null;
    }

    /**
     * 字符串转对象集合* @param value /** * @param tClass /** * @return
     */
    public static <T> List<T> toJavaObjectList(String value, Class<T> tClass) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, tClass);
            return mapper.readValue(value, javaType);
        } catch (Throwable e) {
            log.error("JacksonUtil-toJavaObjectList-value={},class={}", value, tClass);
        }
        return null;
    }

    public static <T> T convertValue(Object value, Class<T> tClass) {
        if (null == value) {
            return null;
        }
        try {
            return mapper.convertValue(value, tClass);
        } catch (Throwable e) {
            log.error("JacksonUtil-convertValue-value={},class={}", toJSONString(value), tClass);
        }
        return null;
    }

    public static <T> T convertValue(Object value, Class<T> tClass, Class<?>... clazz) {
        if (null == value) {
            return null;
        }
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(tClass, clazz);
            return mapper.convertValue(value, javaType);
        } catch (Throwable e) {
            log.error("JacksonUtil-convertValue-value={},class={},clazz={}", toJSONString(value), tClass,
                Arrays.toString(clazz));
        }
        return null;
    }
    
    /**
     * 字符串转对象集合* @param value /** * @param tClass /** * @return
     */
    public static JsonNode toJsonNode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        try {
            return mapper.readTree(value);
        } catch (Throwable e) {
            log.error("JacksonUtil-toJsonNode-value={},class={}", value);
        }
        return null;
    }
}