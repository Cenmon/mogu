package com.cenmo.mogu.sso.config;

import com.cenmo.mogu.sso.dao.JedisClient;
import com.cenmo.mogu.sso.dao.JedisClientImplSingle;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @author Cenmo
 * @Date 2020-10-16 2020/10/16
 */
@Data
@Configuration
@ConfigurationProperties("mogu.redis.jedispool")
public class JedisConfig {

    private String host;
    private Integer port;

    @Bean("JedisPool")
    public JedisPool JedisPoolDI(){
        return new JedisPool(host,port);
    }

    @Bean("jedisClient")
    public JedisClient JedisDI(){
        return new JedisClientImplSingle();
    }
}
