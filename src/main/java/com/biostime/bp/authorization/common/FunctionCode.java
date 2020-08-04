package com.biostime.bp.authorization.common;

public enum FunctionCode {
	MODIFY("modify","修改"),
	DOWNLOAD("download","下载"),
	SEE("see","查看"),
	EDIT("edit","编辑"),
	AUDIT("audit","审核"),
	QUERY("query","查询"),
	CREATE("create","新增"),
	DISTRIBUTION("distribution","分配"),
	DELETE("delete","删除"),
	EXPORT("export","导出"),
	CHANGEPWD("changepwd", "修改密码"),
    ENABLE("enable", "启用"),
    DISABLE("disable", "停用");

	private String code;
	private String name;
	
	FunctionCode(String code,String name){
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	
	
	


}
