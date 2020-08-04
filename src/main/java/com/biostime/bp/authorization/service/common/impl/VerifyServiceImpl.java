package com.biostime.bp.authorization.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biostime.bp.authorization.common.GlobalVariable;
import com.biostime.bp.authorization.common.VerifyType;
import com.biostime.bp.authorization.exception.BusinessException;
import com.biostime.bp.authorization.service.common.VerifyService;
import com.biostime.bp.authorization.util.RedisUtil;

@Service
public class VerifyServiceImpl implements VerifyService {
	
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public void validateVerify(String validateCode, String verifySeq, VerifyType verifyType) {
		String key = null;
		switch (verifyType) {
		case RESET_PASSWORD:
			key = String.format(GlobalVariable.REDIS_KEY_REPWD_VALIDATECODE, verifySeq);
			break;
		case LOGIN:
			key = String.format(GlobalVariable.REDIS_KEY_LOGIN_VALIDATECODE, verifySeq);
			break;

		default:
			throw new BusinessException("209");
		}
		
		if(!redisUtil.exists(key)) {
			throw new BusinessException("210");
		}
		if(!validateCode.equalsIgnoreCase(String.valueOf(redisUtil.get(key)))){
			throw new BusinessException("210");
		}
		redisUtil.del(key);
	}

}
