package com.biostime.bp.authorization.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.biostime.bp.authorization.common.oauth.GrantType;

@Component
public class OauthCache  extends RedisCache<Object>{
	
	private  static final String HH_BPA_OAUTH_ACCESS_TOKEN = "hh:bpa:oauth:access:token:%d%s";

	public OauthCache(@Autowired RedisTemplate<String, Object> redisTemplate) {
		super();
		setRedisTemplate(redisTemplate);
	}
	
	public  String getHhBpaOauthAccessTokenCacheKey(Long clientId,GrantType grantType) {
		return String.format(HH_BPA_OAUTH_ACCESS_TOKEN, clientId,grantType.getType());
	}
	
	

}
