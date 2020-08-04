package com.biostime.bp.authorization.common;

public enum AccountType {
	/*账户类型 1-邮箱 2-手机号 3-工号 4-其他 5-client账户*/
	EMAIL(1),
	PHONE(2),
	STAFF_CODE(3),
	OTHER(4),
	CLIENT(5);
	
	private Integer type;
	
	AccountType(int type){
		this.type = type;
	}

	public Integer getType() {
		return type;
	}
	
	
}
