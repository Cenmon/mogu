package com.cenmo.tesst;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Cenmo
 * @Date 2020-10-23 2020/10/23
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class test {

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
