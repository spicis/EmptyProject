package com.spicis.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.timeout}")
    private int timeout;
    @Value("${redis.maxTotal}")
    private int maxTotal;
    @Value("${redis.maxTotal}")
    private int maxIdle;
    @Value("${redis.maxIdle}")
    private int minIdle;

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);

        JedisPool jedisPool = null;
        if (StringUtils.isNotBlank(password)) {
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        } else {
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
        }

        return jedisPool;
    }
}
