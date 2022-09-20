package com.jhj.blogsearch.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import redis.embedded.RedisServer;

@TestConfiguration
public class TestRedisConfig {

    private final RedisServer redisServer;

    public TestRedisConfig(@Value("${spring.redis.port}") int redisPort) {
        redisServer = new RedisServer(redisPort);
    }

    @PostConstruct
    public void startRedis() {
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        redisServer.stop();
    }

}