package com.avaj.ekill.redis;

import com.avaj.ekill.redis.jedis.JedisCache;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
* @author: DoubleP
* @Date: 2019/11/2 14:18
* @Description: redis
*/
@Configuration
public class JedisConfig {
    @Bean(name = "jedis.pool")
    @Autowired
    public JedisPool jedisPool(
            @Qualifier("jedis.pool.config")JedisPoolConfig config,
            @Value("${jedis.pool.host}") String host,
            @Value("${jedis.pool.port}") int port,
            @Value("${jedis.pool.timeout}") int expire,
            @Value("${jedis.pool.password}") String password
            ) {
        return new JedisPool(config, host, port, expire, password);
    }
    @Bean(name = "jedis.pool.config")
    public JedisPoolConfig jedisPoolConfig(@Value("${jedis.pool.config.maxTotal}") int maxTotal,
                                           @Value("${jedis.pool.config.maxIdle}") int maxIdle,
                                           @Value("${jedis.pool.config.maxWaitMillis}") int maxWaitMillis) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        return config;
    }
    @Bean
    @Autowired
    public JedisCache jedisCache(@Qualifier("jedis.pool") JedisPool pool) {
        return new JedisCache(pool);
    }
}
