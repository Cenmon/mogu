package com.cenmo.mogu.manager.dao;

public interface JedisClient {
	String get(String key);
	
	String set(String key,String value);
	
	String hget(String hkey,String key);
	
	long hset(String hkey,String key,String value);
	
	long del(String key);
	
	long hdel(String hkey,String key);
	
	long incr(String key);
	
	long expire(String key,int second);
	
	long ttl(String key);
}
