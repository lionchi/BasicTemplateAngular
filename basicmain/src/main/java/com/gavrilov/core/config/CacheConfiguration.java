package com.gavrilov.core.config;

import com.gavrilov.core.domain.Role;
import com.gavrilov.core.domain.User;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableCaching
public class CacheConfiguration {
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<Cache> caches = new ArrayList<>();
        caches.add(new ConcurrentMapCache("users"));
        caches.add(new ConcurrentMapCache("roles"));
        caches.add(new ConcurrentMapCache(User.class.getName()));
        caches.add(new ConcurrentMapCache(User.class.getName() + ".roles"));
        caches.add(new ConcurrentMapCache(Role.class.getName()));
        caches.add(new ConcurrentMapCache(Role.class.getName() + ".users"));
        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
