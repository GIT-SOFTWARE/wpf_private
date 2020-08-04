package com.biostime.bp.authorization.common;

public enum RoleType {
	GENERAL(1);
	
	private int type;
	
	RoleType(int type){
		this.type = type;
	}

	public int getType() {
		return type;
	}
	
	
}
