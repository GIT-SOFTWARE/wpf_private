package com.biostime.bp.authorization.common.oauth;

public enum GrantType {
	/**
	 * 客户端模式
	 */
	CILENT("client_credentials");
	
	private String type;
	
	GrantType(String type){
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	public static GrantType getGrantType(String type) {
		for(GrantType g:values()) {
			if(g.getType().equals(type)) {
				return g;
			}
		}
		return null;
	}
	
}