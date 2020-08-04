package com.biostime.bp.authorization.bean.login;

import lombok.Data;

@Data
public class JwtUserInfoBo {
	/**
	 * 用户id
	 */
	private Long uid;

	/**
	 * 帐号
	 */
	private String account;

	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 应用编码
	 */
	private String appCode;
}
