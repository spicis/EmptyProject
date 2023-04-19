package com.spicis.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Configuration
public class CaffeineConfig {
    @Bean
    @ConfigurationProperties(prefix = "caffeine")
    public Map<String, String> cacheSpecs() {
        return new HashMap<>();
    }

    @Bean
    public CacheManager cacheManager() {
        Map<String, String> cacheSpecs = cacheSpecs();
        SimpleCaffeineCacheManager manager = new SimpleCaffeineCacheManager(cacheSpecs);
        if (!cacheSpecs.isEmpty()) {
            List<CaffeineCache> caches = cacheSpecs.entrySet().stream()
                    .map(entry -> buildCache(entry.getKey(), entry.getValue())).collect(Collectors.toList());
            manager.setCaches(caches);
        }
        return manager;
    }

    private CaffeineCache buildCache(String name, String cfg) {
        CacheConfig cacheConfig = new CacheConfig(cfg);
        final Caffeine<Object, Object> caffeineBuilder = Caffeine.newBuilder()
                .expireAfterWrite(cacheConfig.getExpire(), TimeUnit.SECONDS)
                .maximumSize(cacheConfig.getSize());
        return new CaffeineCache(name, caffeineBuilder.build());
    }

    private class SimpleCaffeineCacheManager extends SimpleCacheManager {

        private Map<String, String> cacheSpecs;

        public SimpleCaffeineCacheManager(Map<String, String> cacheSpecs) {
            super();
            this.cacheSpecs = cacheSpecs;
        }

        @Override
        protected Cache getMissingCache(String name) {
            String cfg = this.cacheSpecs.get(name);
            if (StringUtils.isBlank(cfg)) {
                cfg = this.cacheSpecs.get("default");
                if (cfg == null)
                    throw new IllegalArgumentException(String.format("Undefined [default] caffeine cache"));
            }
            return buildCache(name, cfg);
        }
    }

    private class CacheConfig {
        private long size = 0L;
        private long expire = 0L;
        public CacheConfig(String cfg) {
            String[] config = cfg.split(",");
            if (config.length == 1) {
                String sSize = config[0].trim();
                this.size = Long.parseLong(sSize);
            } else if (config.length == 2) {
                String sSize = config[0].trim();
                String sExpire = config[1].trim();
                this.size = Long.parseLong(sSize);
                this.expire = Long.parseLong(sExpire.substring(0, sExpire.length()));
            }
        }

        public long getSize() {
            return size;
        }

        public long getExpire() {
            return expire;
        }

        @Override
        public String toString() {
            return String.format("[SIZE:%d,EXPIRE:%d]", size, expire);
        }

    }

}
