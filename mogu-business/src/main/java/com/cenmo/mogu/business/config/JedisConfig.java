package com.cenmo.mogu.business.config;

import com.cenmo.mogu.business.dao.JedisClient;
import com.cenmo.mogu.business.dao.JedisClientImplSingle;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @author Cenmo
 * @Date 2020-10-16 2020/10/16
 */
@Configuration
@ConfigurationProperties("mogu.redis.jedispool")
public class JedisConfig {

    private String host;
    private Integer port;

    @Bean("jedisPool")
    public JedisPool JedisPoolDI(){
        JedisPool jedisPool = new JedisPool(host,port);
        return jedisPool;
    }

    @Bean("jedisClient")
    public JedisClient JedisDI(){
        return new JedisClientImplSingle();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
