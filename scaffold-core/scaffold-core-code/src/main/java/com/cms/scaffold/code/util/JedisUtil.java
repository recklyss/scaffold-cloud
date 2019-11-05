package com.cms.scaffold.code.util;

import cn.hutool.core.util.StrUtil;
import com.cms.scaffold.code.config.commonly.SpringContextHolder;
import com.cms.scaffold.common.util.JsonSerializerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisException;

import java.io.Serializable;
import java.util.*;

/**
 * Jedis Cache 工具类
 *
 * @author zhangjiaheng
 */
public class JedisUtil {

    private static final Logger logger = LoggerFactory.getLogger(JedisUtil.class);

    private static JedisPool jedisPool = null;

    private JedisUtil() {
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public static String get(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.get(key);
                value = StrUtil.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
            }
        } catch (Exception e) {
            logger.warn("get {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return value;
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public static Object getObject(String key) {
        return getObject(key, false);
    }

    /**
     * 获取缓存 序列化方式为jdk默认
     *
     * @param key 键
     * @return 值
     */
    public static Object getObject(String key, boolean useDefaultSerializable) {
        Object value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                value = toObject(jedis.get(getBytesKey(key)), useDefaultSerializable);
            }
        } catch (Exception e) {
            logger.warn("getObject {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return value;
    }

    /**
     * 设置缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public static String set(String key, String value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.set(key, value);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            logger.warn("set {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 设置缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public static String setObject(String key, Object value, int cacheSeconds) {
        return setObject(key, value, cacheSeconds, false);
    }

    /**
     * 设置缓存 useDefaultSerializable=true 使用jdk方式序列化
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public static String setObject(
            String key, Object value, int cacheSeconds, boolean useDefaultSerializable) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.set(getBytesKey(key), toBytes(value, useDefaultSerializable));
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            logger.warn("setObject {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 获取List缓存
     *
     * @param key 键
     * @return 值
     */
    public static List<String> getList(String key) {
        List<String> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.lrange(key, 0, -1);
                logger.debug("getList {} = {}", key, value);
            }
        } catch (Exception e) {
            logger.warn("getList {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return value;
    }

    /**
     * 获取List缓存
     *
     * @param key 键
     * @return 值
     */
    public static List<Object> getObjectList(String key) {
        List<Object> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                List<byte[]> list = jedis.lrange(getBytesKey(key), 0, -1);
                value = new ArrayList<Object>();
                for (byte[] bs : list) {
                    value.add(toObject(bs));
                }
            }
        } catch (Exception e) {
            logger.warn("getObjectList {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return value;
    }

    /**
     * 设置List缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public static long setList(String key, List<String> value, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            for (String v : value) {
                result += jedis.rpush(key, v);
            }
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            logger.warn("setList {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 设置List缓存
     *
     * @param <T>
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public static <T> long setObjectList(String key, List<T> value, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                jedis.del(key);
            }
            for (Object o : value) {
                result += jedis.rpush(getBytesKey(key), toBytes(o));
            }
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            logger.warn("setObjectList {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 向List缓存中添加值
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public static long listAdd(String key, String... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.rpush(key, value);
        } catch (Exception e) {
            logger.warn("listAdd {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 向List缓存中添加值
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public static long listObjectAdd(String key, Object... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            for (Object o : value) {
                result += jedis.rpush(getBytesKey(key), toBytes(o));
            }
        } catch (Exception e) {
            logger.warn("listObjectAdd {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public static Set<String> getSet(String key) {
        Set<String> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.smembers(key);
            }
        } catch (Exception e) {
            logger.warn("getSet {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return value;
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public static Set<Object> getObjectSet(String key) {
        Set<Object> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                value = new HashSet<Object>();
                Set<byte[]> set = jedis.smembers(getBytesKey(key));
                for (byte[] bs : set) {
                    value.add(toObject(bs));
                }
            }
        } catch (Exception e) {
            logger.warn("getObjectSet {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return value;
    }

    /**
     * 设置Set缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public static long setSet(String key, Set<String> value, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.sadd(key, value.toArray(new String[value.size()]));
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            logger.warn("setSet {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 设置Set缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public static long setObjectSet(String key, Set<Object> value, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                jedis.del(key);
            }
            for (Object o : value) {
                result += jedis.sadd(getBytesKey(key), toBytes(o));
            }

            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            logger.warn("setObjectSet {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 向Set缓存中添加值
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public static long sadd(String key, String... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.sadd(key, value);
        } catch (Exception e) {
            logger.warn("setSetAdd {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 向Set缓存中添加值
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public static long saddObj(String key, Object... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            for (Object o : value) {
                result += jedis.sadd(getBytesKey(key), toBytes(o));
            }
        } catch (Exception e) {
            logger.warn("setSetObjectAdd {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * Set缓存中删除值
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public static long srem(String key, String... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.srem(key, value);
        } catch (Exception e) {
            logger.warn("setSetAdd {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 获取Map缓存
     *
     * @param key 键
     * @return 值
     */
    public static Map<String, String> getMap(String key) {
        Map<String, String> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.hgetAll(key);
            }
        } catch (Exception e) {
            logger.warn("getMap {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return value;
    }

    /**
     * 获取Map缓存的某项值
     *
     * @param key
     * @param field
     * @return
     */
    public static String getMapField(String key, String field) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.hget(key, field);
            }
        } catch (Exception e) {
            logger.warn("getMap {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return value;
    }

    /**
     * 获取Map缓存
     *
     * @param key 键
     * @return 值
     */
    public static Map<String, Object> getObjectMap(String key) {
        Map<String, Object> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                value = new HashMap<>();
                Map<byte[], byte[]> map = jedis.hgetAll(getBytesKey(key));
                for (Map.Entry<byte[], byte[]> e : map.entrySet()) {
                    value.put(StrUtil.toString(e.getKey()), toObject(e.getValue()));
                }
            }
        } catch (Exception e) {
            logger.warn("getObjectMap {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return value;
    }

    /**
     * 获取Map缓存的某个对象
     *
     * @param key
     * @param field
     * @return
     */
    public static Object getObjectMapField(String key, String field) {
        Object value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                value = toObject(jedis.hget(getBytesKey(key), getBytesKey(field)));
            }
        } catch (Exception e) {
            logger.warn("getObjectMap {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return value;
    }

    /**
     * 设置Map缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public static String setMap(String key, Map<String, String> value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.hmset(key, value);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            logger.warn("setMap {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 设置Map缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public static String setObjectMap(String key, Map<String, Object> value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                jedis.del(key);
            }
            result = hmSetAndGetStr(key, value, jedis);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            logger.warn("setObjectMap {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    private static String hmSetAndGetStr(String key, Map<String, Object> value, Jedis jedis) {
        Map<byte[], byte[]> map = new HashMap<>();
        for (Map.Entry<String, Object> e : value.entrySet()) {
            map.put(getBytesKey(e.getKey()), toBytes(e.getValue()));
        }
        return jedis.hmset(getBytesKey(key), map);
    }

    /**
     * 向Map缓存中添加值
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public static String mapPut(String key, Map<String, String> value) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hmset(key, value);
        } catch (Exception e) {
            logger.warn("mapPut {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 向Map缓存中添加值
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public static String mapObjectPut(String key, Map<String, Object> value) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = hmSetAndGetStr(key, value, jedis);
        } catch (Exception e) {
            logger.warn("mapObjectPut {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 移除Map缓存中的值
     *
     * @param key    键
     * @param mapKey
     * @return
     */
    public static long mapRemove(String key, String... mapKey) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hdel(key, mapKey);
        } catch (Exception e) {
            logger.warn("mapRemove {}  {}", key, mapKey, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 移除Map缓存中的值
     *
     * @param key    键
     * @param mapKey
     * @return
     */
    public static long mapObjectRemove(String key, String mapKey) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hdel(getBytesKey(key), getBytesKey(mapKey));
        } catch (Exception e) {
            logger.warn("mapObjectRemove {}  {}", key, mapKey, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 判断Map缓存中的Key是否存在
     *
     * @param key    键
     * @param mapKey
     * @return
     */
    public static boolean mapExists(String key, String mapKey) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hexists(key, mapKey);
        } catch (Exception e) {
            logger.warn("mapExists {}  {}", key, mapKey, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 判断Map缓存中的Key是否存在
     *
     * @param key    键
     * @param mapKey
     * @return
     */
    public static boolean mapObjectExists(String key, String mapKey) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hexists(getBytesKey(key), getBytesKey(mapKey));
        } catch (Exception e) {
            logger.warn("mapObjectExists {}  {}", key, mapKey, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 删除缓存
     *
     * @param key 键
     * @return
     */
    public static long del(String... key) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.del(key);
        } catch (Exception e) {
            logger.warn("del {}", key, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 批量删除
     *
     * @param pattern
     * @return
     */
    public static long batchDel(String... pattern) {
        long result = 0;
        if (pattern == null || pattern.length == 0) {
            return result;
        }
        Jedis jedis = null;
        try {
            jedis = getResource();
            for (String kp : pattern) {
                Set<String> sets = jedis.keys(kp + "*");
                if (!CollectionUtils.isEmpty(sets)) {
                    String[] keys = sets.toArray(new String[sets.size()]);
                    result = jedis.del(keys);
                }
            }

        } catch (Exception e) {
            logger.warn("del pattern: {}", pattern, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 删除缓存
     *
     * @param key 键
     * @return
     */
    public static long delObject(String key) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                result = jedis.del(getBytesKey(key));
            } else {
                logger.debug("delObject {} not exists", key);
            }
        } catch (Exception e) {
            logger.warn("delObject {}", key, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 缓存是否存在
     *
     * @param key 键
     * @return
     */
    public static boolean exists(String key) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.exists(key);
        } catch (Exception e) {
            logger.warn("exists {}", key, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 缓存是否存在
     *
     * @param key 键
     * @return
     */
    public static boolean existsObject(String key) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.exists(getBytesKey(key));
        } catch (Exception e) {
            logger.warn("existsObject {}", key, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 递增数字
     *
     * @param key 键
     * @param by  步长
     * @return
     */
    public static long incr(String key, long by) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (by > 0) {
                result = jedis.incrBy(key, by);
            } else {
                result = jedis.incr(key);
            }

        } catch (Exception e) {
            logger.warn("incr key={}, by={}", key, by, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 递减数字
     *
     * @param key 键
     * @param by  步长
     * @return
     */
    public static long decr(String key, long by) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (by > 0) {
                result = jedis.decrBy(key, by);
            } else {
                result = jedis.decr(key);
            }

        } catch (Exception e) {
            logger.warn("decr key={}, by={}", key, by, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 递增数字
     *
     * @param key 键
     * @param by  步长
     * @return
     */
    public static double incr(String key, double by) {
        double result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (by > 0) {
                result = jedis.incrByFloat(key, by);
            } else {
                result = jedis.incrByFloat(key, 1d);
            }

        } catch (Exception e) {
            logger.warn("incr key={}, by={}", key, by, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 递减数字
     *
     * @param key 键
     * @param by  步长
     * @return
     */
    public static double decr(String key, double by) {
        double result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (by > 0) {
                result = jedis.incrByFloat(key, -by);
            } else {
                result = jedis.incrByFloat(key, -1d);
            }

        } catch (Exception e) {
            logger.warn("decr key={}, by={}, result={}", key, by, result, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 获取资源
     *
     * @return
     * @throws JedisException
     */
    public static Jedis getResource() throws JedisException {
        Jedis jedis = null;
        try {
            jedis = getInstance().getResource();
        } catch (JedisException e) {
            logger.warn("getResource.", e);
            close(jedis);
            throw e;
        }
        return jedis;
    }

    public static JedisPool getInstance() {
        if (jedisPool == null) {
            synchronized (JedisUtil.class) {
                if (jedisPool == null) {
                    jedisPool = SpringContextHolder.getBean("jedisPool");
                }
            }
        }
        return jedisPool;
    }

    /**
     * 释放资源
     *
     * @param jedis
     * @param
     */
    public static void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * 发布消息到其他节点，更新缓存时使用
     *
     * @param channel
     * @param message
     */
    public static void publish(String channel, Serializable message) {
        if (StrUtil.isBlank(channel) || message == null) {
            return;
        }
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.publish(channel, (String) message);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
    }

    /**
     * getAndset
     *
     * @param key
     * @param newVal
     * @return
     */
    public static String getAndset(String key, String newVal) {
        String oldVal = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            oldVal = jedis.getSet(key, newVal);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return oldVal;
    }

    /**
     * setnx
     *
     * @param key
     * @param value
     * @return
     */
    public static Long setnx(String key, String value) {
        Long result = 0L;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.setnx(key, value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * setnx
     *
     * @param key
     * @param value
     * @return
     */
    public static Long setnx(String key, String value, int seconds) {
        Long result = 0L;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.setnx(key, value);
            if (seconds > 0) {
                jedis.expire(key, seconds);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 设置有效期
     *
     * @param key
     * @param seconds
     * @return
     */
    public static Long expire(String key, int seconds) {
        Long result = 0L;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.expire(key, seconds);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 获取byte[]类型Key
     *
     * @param object
     * @return
     */
    public static byte[] getBytesKey(Object object) {
        if (object instanceof String) {
            return StrUtil.bytes(object.toString());
        } else {
            return JsonSerializerUtil.serialize(object);
        }
    }

    /**
     * 返回给定key的有效时间，以秒为单位 当 key 不存在时，返回 -2 。 当 key 存在但没有设置剩余生存时间时，返回 -1 。 否则，以秒为单位，返回 key 的剩余生存时间。
     *
     * @param key
     * @return
     * @author jiaheng
     * @date 2017-9-26
     */
    public static long ttl(String key) {
        Long result = 0L;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.ttl(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * Object转换byte[]类型
     *
     * @param object
     * @return
     */
    public static byte[] toBytes(Object object) {
        return toBytes(object, false);
    }

    /**
     * Object转换byte[]类型 默认序列化方式
     *
     * @param object
     * @return
     */
    public static byte[] toBytes(Object object, boolean useDefaultSerializable) {
        if (useDefaultSerializable) {
            return JsonSerializerUtil.serializeByDefaultSerializable(object);
        }
        return JsonSerializerUtil.serialize(object);
    }

    /**
     * byte[]型转换Object
     *
     * @param bytes
     * @return
     */
    public static Object toObject(byte[] bytes) {
        return toObject(bytes, false);
    }

    /**
     * byte[]型转换Object 默认序列化方式
     *
     * @param bytes
     * @return
     */
    public static Object toObject(byte[] bytes, boolean useDefaultSerializable) {
        if (useDefaultSerializable) {
            return JsonSerializerUtil.unserializeByDefaultSerializable(bytes);
        }
        return JsonSerializerUtil.unserialize(bytes);
    }

    /**
     * 重命名
     *
     * @param oldkey
     * @param newkey
     */
    public static void rename(String oldkey, String newkey) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.rename(oldkey, newkey);
        } finally {
            close(jedis);
        }
    }

    /**
     * 批量设置map
     *
     * @param key
     * @param map
     * @param cacheSec
     */
    public static void hmset(String key, Map<String, String> map, int cacheSec) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                String field = it.next();
                jedis.hset(key, field, String.valueOf(map.get(field)));
            }
            if (cacheSec > 0) {
                jedis.expire(key, cacheSec);
            }

        } finally {
            close(jedis);
        }
    }

    /**
     * 设置map
     *
     * @param key
     * @param field
     * @param value
     */
    public static void hset(String key, Object field, Object value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.hset(getBytesKey(key), getBytesKey(field), getBytesKey(value));
        } finally {
            close(jedis);
        }
    }

    /**
     * 取得hash的 len
     *
     * @param key
     * @return
     */
    public static Long hlen(final String key) {
        Jedis jedis = null;
        Long result = 0L;
        try {
            jedis = getResource();
            result = jedis.hlen(getBytesKey(key));
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 取得hash的 keys
     *
     * @param key
     * @return
     */
    public static Set<byte[]> hkeys(final String key) {
        Jedis jedis = null;
        Set<byte[]> result = null;
        try {
            jedis = getResource();
            result = jedis.hkeys(getBytesKey(key));
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 取得hash的values
     *
     * @param key
     * @return
     */
    public static List<byte[]> hvals(final String key) {
        Jedis jedis = null;
        List<byte[]> result = null;
        try {
            jedis = getResource();
            result = jedis.hvals(getBytesKey(key));
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 获取byte[]类型Key
     *
     * @param key
     * @return
     */
    public static Object getObjectKey(byte[] key) {
        try {
            return StrUtil.toString(key);
        } catch (UnsupportedOperationException uoe) {
            try {
                return JedisUtil.toObject(key);
            } catch (UnsupportedOperationException uoe2) {
                uoe2.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 在头部添加字符串值存储在列表. 假如key不存在，则创建空的列表 假如key存在，但不是列表对象时，则出现异常
     *
     * @param key
     * @param param
     */
    public static void ladd(String key, String... param) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.lpush(key, param);
        } catch (Exception e) {
            logger.warn("ladd{}", key, e);
        } finally {
            close(jedis);
        }
    }

    /**
     * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
     *
     * @param key
     * @param start
     * @param end
     */
    public static void ltrim(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.ltrim(key, start, end);
        } catch (Exception e) {
            logger.warn("trim{}", key, e);
        } finally {
            close(jedis);
        }
    }

    /**
     * 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。
     *
     * @param key
     * @return
     */
    public static List<String> getladd(String key) {
        Jedis jedis = null;
        List<String> list = null;
        try {
            jedis = getResource();
            list = jedis.lrange(key, 0, -1);
        } catch (Exception e) {
            logger.warn("lrange{}", key, e);
        } finally {
            close(jedis);
        }
        return list;
    }

    /**
     * 对某个键的值自增
     *
     * @param key          键
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     * @author liboyi
     */
    public static long setIncr(String key, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.incr(key);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            logger.debug("set " + key + " = " + result);
        } catch (Exception e) {
            logger.warn("set " + key + " = " + result);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * 批量获取缓存
     *
     * @param pattern
     * @return 值
     */
    public static List<Object> batchGetObject(String pattern) {
        Jedis jedis = null;
        List<Object> list = new ArrayList<>();
        try {
            jedis = getResource();
            Set<String> sets = jedis.keys(pattern + "*");
            if (!CollectionUtils.isEmpty(sets)) {
                for (String key : sets) {
                    Object result = toObject(jedis.get(getBytesKey(key)));
                    list.add(result);
                }
            }
        } catch (Exception e) {
            logger.warn("get {} = {}", pattern, list, e);
        } finally {
            close(jedis);
        }
        return list;
    }

    /**
     * 设置sorted set缓存
     *
     * @param key   键
     * @param value 值 @Param score 值
     */
    public static Long zadd(String key, String value, double score) {
        return zadd(key, value, score, 0);
    }

    /**
     * 设置sorted set缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时 @Param score 值
     */
    public static Long zadd(String key, String value, double score, int cacheSeconds) {
        Long result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.zadd(key, score, value);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            logger.warn("zadd {} = {}", key, value, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 设置sorted set缓存
     *
     * @param key          键
     * @param members      成员 值 分数
     * @param cacheSeconds 超时时间，0为不超时 @Param score 值
     */
    public static Long zadd(String key, Map<String, Double> members, int cacheSeconds) {
        Long result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.zadd(key, members);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            logger.warn("zadd {} = {}", key, members, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 获取sortedset中的 索引范围的
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static Set<Tuple> zrangeWithScore(String key, Long start, Long end) {
        Set<Tuple> result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.zrangeWithScores(key, start, end);
        } catch (Exception e) {
            logger.warn("zrangeWithScore key = {}, start = {}, end = {}", key, start, end, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 获取sortedset中的列表的成员的分值
     *
     * @param key
     * @param member
     * @return
     */
    public static Double zscore(String key, String member) {
        Double result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.zscore(key, member);
        } catch (Exception e) {
            logger.warn("zscore {} = {}", key, member, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 查询集合成员数量
     *
     * @param key
     * @return sortedset 的成员数量
     */
    public static Long zcard(String key) {
        Long result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.zcard(key);
        } catch (Exception e) {
            logger.warn("zcard {} = {}", key, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 删除排序集合中的元素成员
     *
     * @param key     集合key
     * @param members 成员名s
     * @return
     */
    public static Long zrem(String key, String... members) {
        Long result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.zrem(key, members);
        } catch (Exception e) {
            logger.warn("zrem {} = {}", key, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 获取所有sorted set中的成员以及分值
     *
     * @param key 键值
     * @return 排序的map
     */
    public static LinkedHashMap<String, Double> zgetAll(String key) {
        LinkedHashMap<String, Double> result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = new LinkedHashMap<>();
            Set<Tuple> sortedSet = jedis.zrangeWithScores(key, 0, -1);
            for (Tuple tuple : sortedSet) {
                result.put(tuple.getElement(), tuple.getScore());
            }
        } catch (Exception e) {
            logger.warn("zrangeWithScores {} = {}", key, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 获取所有sorted set中的排名第一的成员
     *
     * @param key 键值
     * @return 排序的map
     */
    public static LinkedHashMap<String, Double> zgetFirst(String key) {
        LinkedHashMap<String, Double> result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = new LinkedHashMap<>();
            Set<Tuple> sortedSet = jedis.zrangeWithScores(key, 0, 0);
            for (Tuple tuple : sortedSet) {
                result.put(tuple.getElement(), tuple.getScore());
            }
        } catch (Exception e) {
            logger.warn("zrangeWithScores {} = {}", key, e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 给key对应的members 分数增加1
     *
     * @param key
     * @param members
     * @return
     */
    public static void zincr(String key, String... members) {
        zincr(key, 1L, members);
    }

    /**
     * 给key对应的members 分数增加score
     *
     * @param key
     * @param score
     * @param members
     * @return
     */
    public static void zincr(String key, Long score, String... members) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            for (String member : members) {
                jedis.zincrby(key, score, member);
            }
        } catch (Exception e) {
            logger.warn("zincr {} = {}", key, members, e);
        } finally {
            close(jedis);
        }
    }

    /**
     * 调用jedis的subscribe()方法
     *
     * @param jedisPubSub
     * @param channels
     */
    public static void subscribe(JedisPubSub jedisPubSub, String... channels) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.subscribe(jedisPubSub, channels);
        } catch (Exception e) {
            logger.warn("psubscribe channels==> {} exception==> {}", channels, e);
        } finally {
            close(jedis);
        }
    }

    /**
     * 调用jedis的psubscribe()方法
     *
     * @param jedisPubSub
     * @param patterns
     */
    public static void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.psubscribe(jedisPubSub, patterns);
        } catch (Exception e) {
            logger.warn("psubscribe patterns==> {} exception==> {}", patterns, e);
        } finally {
            close(jedis);
        }
    }
}
