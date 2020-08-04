package com.biostime.bp.authorization.bean.base;

import java.text.MessageFormat;

/** 
 * 描述: TODO
 *
 * @author <a href="mailto:zhangguojian@hh.global">12552</a>
 * @createDate 2019年5月22日 下午1:37:20
 */
public enum ResponseEnum {
	SUCCESS("200", "操作成功"),
	ERROR("555", "操作失败"),
	CUSTOMER_NOT_EXISTS("410", "用户{0}不存在"),
//	CUSTOMER_STATE_NOT_ALLOW("411", "用户{0}{1}状态不允许{2}操作"),
	NOT_ALLOW_OPERATOR("412", "不允许的操作类型"),
	INPUT_PARAM_ERROR("413", "参数错误"),
	ERROR_OLD_PWD("414", "旧密码错误"),
	VALIDATECODE_ERROR("415", "验证码错误"),
	INVALID_USER_MSG("416", "无效的用户信息"),
	INVALID_PWD("417", "密码长度必须超过6，且必须有数字和字母组成"),
	INVALID_EMAIL("418", "无效的邮箱"),
	INVALID_USER_OR_EMAIL("419", "无效的用户名或密码"),
	;
	private String code;
	private String desc;
	private Object[] args;
	ResponseEnum(String code, String desc) {
		this.code = code;
		this.desc  = desc;
	}
	public static ResponseEnum build(ResponseEnum e, Object... args) {
		e.args = args;
		return e;
	}
	public String toString() {
		return MessageFormat.format(this.desc, this.args);
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	
}
