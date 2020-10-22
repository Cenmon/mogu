package com.cenmo.mogu.config;

import com.cenmo.mogu.dao.JedisClient;
import com.cenmo.mogu.dao.JedisClientImplSingle;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @author Cenmo
 * @Date 2020-10-16 2020/10/16
 */
@Configuration
public class JedisConfig {

    @Bean("JedisPool")
    @ConfigurationProperties("mogu.redis.jedispool")
    public JedisPool JedisPoolDI(){
        JedisPool jedisPool = new JedisPool();
        return jedisPool;
    }

    @Bean("jedisClient")
    public JedisClient JedisDI(){
        return new JedisClientImplSingle();
    }

}
