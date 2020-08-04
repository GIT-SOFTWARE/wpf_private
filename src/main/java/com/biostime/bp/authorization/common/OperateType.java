package com.biostime.bp.authorization.common;

public enum OperateType {
	/**
	 * 用户重置密码
	 */
	USER_RESET_PASSWORD("1"),
	/**
	 * 用户登录
	 */
	LOGIN_SUCCESS("2"),
	/**
	 * 登录密码错误
	 */
	LOGIN_PASSWORD_IS_ERROR("3"),
	/**
	 * 管理员修改用户密码
	 */
	ADMIN_CHANGE_USER_PASSWORD("4"),
	/**
	 * 管理员停用账户
	 */
	DISCONTINUE_ACCOUNT("5"),
	/**
	 * 管理员启用账户
	 */
	ENABLE_ACCOUNT("6"),
	/**
	 * 用户修改密码
	 */
	USER_CHANGE_PASSWORD("7")
	;
	private String type;
	
	OperateType(String type){
		this.type = type;
	}

	public String getType() {
		return type;
	}

	
}
