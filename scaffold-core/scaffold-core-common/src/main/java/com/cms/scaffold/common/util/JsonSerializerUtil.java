package com.cms.scaffold.common.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

/**
 * json格式序列化 Created by zjh on 2018/8/21.
 */
public class JsonSerializerUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonSerializerUtil.class);

    /**
     * 序列化对象
     *
     * @param object
     * @return
     */
    public static byte[] serialize(final Object object) {
        try {
            if (object != null) {
                return serializeConfig().serialize(object);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return new byte[0];
    }

    /**
     * 反序列化对象
     *
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes) {
        try {
            if (bytes != null && bytes.length > 0) {
                return serializeConfig().deserialize(bytes);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return null;
    }

    private static Jackson2JsonRedisSerializer serializeConfig() {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer =
                new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;
    }

    /**
     * 默认方式序列化对象
     *
     * @return
     */
    public static byte[] serializeByDefaultSerializable(final Object object) {
        try {
            if (object != null) {
                return new JdkSerializationRedisSerializer().serialize(object);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return new byte[0];
    }

    /**
     * 默认方式反序列化对象
     *
     * @param bytes
     * @return
     */
    public static Object unserializeByDefaultSerializable(byte[] bytes) {
        try {
            if (bytes != null && bytes.length > 0) {
                return new JdkSerializationRedisSerializer().deserialize(bytes);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
}
