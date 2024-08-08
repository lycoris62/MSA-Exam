package com.sparta.msa_exam.product.global.redis;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {

        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues() // null 미포함
                .entryTtl(Duration.ofSeconds(60)) // 기본 TTL 60초
                .computePrefixWith(CacheKeyPrefix.simple()) // 키 구분은 :: 로 기본값 설정
                .serializeKeysWith(SerializationPair.fromSerializer(RedisSerializer.string())) // 키는 문자열 직렬화
                .serializeValuesWith(SerializationPair.fromSerializer(RedisSerializer.json())); // 값은 JSON 직렬화

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(configuration)
                .build();
    }
}
