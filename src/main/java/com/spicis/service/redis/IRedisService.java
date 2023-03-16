package com.spicis.service.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IRedisService {

    Long setnx(String key);

    Long setnxWithExpire(String key, int secondes);

    Long expire(String key, int seconds);

    Long ttl(String key);

    Long del(String key);

    String set(String key, String value);

    String get(String key);

    Long sAdd(String key, String... value);

    Set<String> sMembers(String key);

    Long srem(String key, String... value);

    Long hset(String key, String mapKey, String value);

    Long zadd(String key, Map<String, Double> map);

    Long zadd(String key, double score, String member);

    Long zrem(String key, String... members);

    Long zcard(String key);

    Set<String> zrevrange(String key, long start, long end);

    Set<String> zrevrangeBeScore(String key, long start, long end);

    Map<String, String> hgetALl(String key);

    String hget(String key, String mapKey);

    Long hdel(String key, String... mapKey);

    String hmset(String key, Map<String, String> data);

    List<String> hmget(String key, String... fields);

    Long lpush(String key, String...values);

    Long llen(String key);

    List<String> lrange(String key, long start, long end);

    String ltrim(String key, long start, long end);

    String lindex(String key, long index);

    String lset(String key, long index, String value);

    Long lrem(String key, long count, String value);

    String lpop(String key);

}
