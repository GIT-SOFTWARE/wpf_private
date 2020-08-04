package com.biostime.bp.authorization.common;

public enum VerifyType {
	/**
	 * 重置密码
	 */
	RESET_PASSWORD(1),
	/**
	 * 登录
	 */
	LOGIN(2);

	private int type;

	VerifyType(int type) {
		this.type= type;
	}

	public int getType() {
		return type;
	}

}
