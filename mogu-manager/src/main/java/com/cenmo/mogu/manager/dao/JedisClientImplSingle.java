package com.cenmo.mogu.manager.dao;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientImplSingle implements JedisClient {

	@Autowired
	private JedisPool jedisPool;
	
	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String reString = jedis.get(key);
		jedis.close();
		return reString;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String reString = jedis.set(key,value);
		jedis.close();
		return reString;
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		String reString = jedis.hget(hkey, key);
		jedis.close();
		return reString;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		long res = jedis.hset(hkey, key, value);
		jedis.close();
		return res;
	}

	@Override
	public long del(String key) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		long res = jedis.del(key);
		jedis.close();
		return res;
	}

	@Override
	public long hdel(String hkey, String key) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		long res = jedis.hdel(hkey, key);
		jedis.close();
		return res;
	}

	@Override
	public long incr(String key) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		long res = jedis.incr(key);
		jedis.close();
		return res;
	}

	@Override
	public long expire(String key, int second) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		long res = jedis.expire(key, second);
		jedis.close();
		return res;
	}

	@Override
	public long ttl(String key) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		long res = jedis.ttl(key);
		jedis.close();
		return res;
	}

}
