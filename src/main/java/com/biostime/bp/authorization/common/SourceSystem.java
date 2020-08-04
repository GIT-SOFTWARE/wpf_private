package com.biostime.bp.authorization.common;

public enum SourceSystem {
	/**
	 * 权限管理系统
	 */
	LMS_AUTH_SERVER("lms-auth-server"),//
	/**
	 * MTS系统,主要用到该系统的功能:生成短链接及跳转
	 */
	MTS("mts"),//
	/**
	 * 企业微信
	 */
	WORK_WEIXIN("work_weixin");//
	
	
	private String val;
	
	SourceSystem(String val){
		this.val = val; 
	}

	public String getVal() {
		return val;
	} 

	
	
}
