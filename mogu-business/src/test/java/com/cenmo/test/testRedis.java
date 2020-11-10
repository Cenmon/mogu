package com.cenmo.test;

import com.cenmo.mogu.business.BusinessApplication;
import com.cenmo.mogu.business.dao.JedisClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Cenmo
 * @Date 2020-10-23 2020/10/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=BusinessApplication.class)
public class testRedis {

    @Autowired
    private JedisClient jedisClient;

    @Test
    public void testJedisClient(){
        jedisClient.set("cenmo","hello");
    }

    @Test
    public void keys(){
        JedisPool jedisPool = new JedisPool("192.168.117.129",6379);


        Jedis jedis = jedisPool.getResource();
        for (String key : jedis.keys("*")) {

            System.out.println(key);
        }
        jedis.close();
    }

    @Test
    public void flushAll(){
        JedisPool jedisPool = new JedisPool("192.168.117.129",6379);

        Jedis jedis = jedisPool.getResource();
        jedis.flushAll();
        jedis.close();
    }
}
