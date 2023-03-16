package com.spicis.service.redis;

import com.spicis.logger.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class RedisService implements IRedisService {

    private static final int EXPIRE_SECONDS = 432000;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public Long setnx(String key) {
        Jedis jedis = jedisPool.getResource();
        String value = "1";
        long v = jedis.setnx(key, value);
        close(jedis);
        return v;
    }

    @Override
    public Long setnxWithExpire(String key, int secondes) {
        Long value = setnx(key);
        if (value == 1) {
            expire(key, secondes);
        } else  {
            Long time = ttl(key);
            if (time == -1) {
                expire(key, secondes);
            }
        }
        return value;
    }

    @Override
    public Long expire(String key, int seconds) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;

        if (seconds <= 0) {
            seconds = EXPIRE_SECONDS;
        }

        try {
            result = jedis.expire(key, seconds);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.expire.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        long v = jedis.ttl(key);
        close(jedis);
        return v;
    }

    @Override
    public Long del(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.del(key);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.del.Exception.key" + key, e);
        }
        close(jedis);

        return result;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String result = null;
        try {
            result = jedis.set(key, value);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.set.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String result = null;
        try {
            result = jedis.get(key);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.get.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public Long sAdd(String key, String... value) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.sadd(key, value);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.sadd.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public Set<String> sMembers(String key) {
        Jedis jedis = jedisPool.getResource();
        Set<String> result = null;
        try {
            result = jedis.smembers(key);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.sMember.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public Long srem(String key, String... value) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.srem(key, value);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.srem.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public Long hset(String key, String mapKey, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.hset(key, mapKey, value);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.hset.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public Long zadd(String key, Map<String, Double> map) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.zadd(key, map);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.zadd.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public Long zadd(String key, double score, String member) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.zadd(key, score, member);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.zadd.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public Long zrem(String key, String... members) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.zrem(key, members);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.zrem.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public Long zcard(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.zcard(key);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.zcard.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public Set<String> zrevrange(String key, long start, long end) {
        Jedis jedis = jedisPool.getResource();
        Set<String> result = null;
        try {
            result = jedis.zrevrange(key, start, end);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.zrevrange.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public Set<String> zrevrangeBeScore(String key, long start, long end) {
        Jedis jedis = jedisPool.getResource();
        Set<String> result = null;
        try {
            result = jedis.zrevrangeByScore(key, start, end);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.zrevrangeByScore.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public Map<String, String> hgetALl(String key) {
        Jedis jedis = jedisPool.getResource();
        Map<String, String> result = null;
        try {
            result = jedis.hgetAll(key);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.hgetAll.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public String hget(String key, String mapKey) {
        Jedis jedis = jedisPool.getResource();
        String result = null;
        try {
            result = jedis.hget(key, mapKey);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.hget.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public Long hdel(String key, String... mapKey) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.hdel(key, mapKey);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.hdel.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public String hmset(String key, Map<String, String> data) {
        Jedis jedis = jedisPool.getResource();
        String result = null;
        try {
            result = jedis.hmset(key, data);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.hmset.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        Jedis jedis = jedisPool.getResource();
        List<String> result = null;
        try {
            result = jedis.hmget(key, fields);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.hmget.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public Long lpush(String key, String... values) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.lpush(key, values);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.lpush.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public Long llen(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.llen(key);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.llen.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        Jedis jedis = jedisPool.getResource();
        List<String> result = null;
        try {
            result = jedis.lrange(key, start, end);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.lrange.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public String ltrim(String key, long start, long end) {
        Jedis jedis = jedisPool.getResource();
        String result = null;
        try {
            result = jedis.ltrim(key, start, end);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.ltrim.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public String lindex(String key, long index) {
        Jedis jedis = jedisPool.getResource();
        String result = null;
        try {
            result = jedis.lindex(key, index);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.lindex.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public String lset(String key, long index, String value) {
        Jedis jedis = jedisPool.getResource();
        String result = null;
        try {
            result = jedis.lset(key, index, value);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.lset.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public Long lrem(String key, long count, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.lrem(key, count, value);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.lrem.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    @Override
    public String lpop(String key) {
        Jedis jedis = jedisPool.getResource();
        String result = null;
        try {
            result = jedis.lpop(key);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("RedisService.lpop.Exception.key" + key, e);
        }
        close(jedis);
        return result;
    }

    private void close(Jedis jedis) {
        try {
            if (jedis != null) {
                jedisPool.returnResourceObject(jedis);
            }
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError(e.getMessage(), e);
        }
    }

}
