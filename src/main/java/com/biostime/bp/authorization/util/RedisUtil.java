package com.biostime.bp.authorization.util;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/** 
 * 描述: TODO
 *
 * @author <a href="mailto:zhangguojian@hh.global">12552</a>
 * @createDate 2019年5月23日 上午10:56:00
 */
@Slf4j
@Component
public class RedisUtil {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	public boolean expire(String key, long time) {
		if (time > 0) {
			return redisTemplate.expire(key, time, TimeUnit.SECONDS);
		}
		return true;
	}
	
	public long getExpire(String key) {
		return redisTemplate.getExpire(key, TimeUnit.SECONDS);
	}
	
	public boolean exists(String key) {
		return redisTemplate.hasKey(key);
	}
	
	public boolean del(String key) {
		return redisTemplate.delete(key);
	}
	
	public void set(String key, Object value, Long time) {
		if (null != time && time > 0) {
			redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
		} else {
			redisTemplate.opsForValue().set(key, value);
		}
	}
	
	public Object get(String key) {
		return null == key ? null : redisTemplate.opsForValue().get(key);
	}
	
	public void hmset(String key, Map<String, Object> value, Long time) {
		redisTemplate.opsForHash().putAll(key, value);
		expire(key, time);
	}
	
	public Map<Object, Object> hmget(String key) {
		return redisTemplate.opsForHash().entries(key);
	}
	
	public void hset(String key, String item, Object value, Long time) {
		redisTemplate.opsForHash().put(key, item, value);
		if (null != time && time > 0) {
			expire(key, time);
		}
	}

	public Object hget(String key, String item) {
		return redisTemplate.opsForHash().get(key, item);
	}
}
