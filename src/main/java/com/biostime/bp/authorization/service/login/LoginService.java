package com.biostime.bp.authorization.service.login;


import com.biostime.bp.authorization.bean.login.JwtAccessTokenVo;
import com.biostime.bp.authorization.bean.login.param.LoginParam;
import com.biostime.bp.authorization.domain.user.BpaUser;

public interface LoginService {

	JwtAccessTokenVo login(LoginParam param);
	
	public  BpaUser  queryBpaUserByAccount(String account);
	
	/**
	 * 锁定用户
	 * @param uid
	 */
	public void lockUser(Long uid);
	
	
	/**
	 * 统计
	 * @param uid
	 * @return
	 */
	public long countUserLoginPasswordErrorByCurrentDay(Long uid);
}
