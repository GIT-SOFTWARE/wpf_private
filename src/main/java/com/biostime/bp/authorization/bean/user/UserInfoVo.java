package com.biostime.bp.authorization.bean.user;

import java.util.EnumSet;

import lombok.Data;

@Data
public class UserInfoVo {
	  /**
     * 用户id
     */
    private Long uid;

    /**
     * 帐号
     */
    private String account;

    /**
     * 账户类型
     */
    private Integer accountType;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 人名
     */
    private String personName;

    /**
     * 性别 1-男 2-女
     */
    private Integer gender;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 座机号码
     */
    private String telephone;

    /**
     * 公司
     */
    private String company;

    /**
     * 部门编码
     */
    private String orgCode;

    /**
     * 部门名称
     */
    private String orgName;

    /**
     * 系统来源
     */
    private String sourceSystem;

    /**
     * 状态 1-有效 0-停用 2-已删除
     */
    private Integer status;
    
    /**
     * 是否锁定 1-已锁定 0-未锁定
     * */
    private Integer isLock;
    

    /**
     * 国家
     */
    private String country;

    public enum Status {
		EFFECTIVE(1, "有效"), DISCONTINUE(0, "停用"), DELETED(2, "删除");
		private int value;
		private String desc;
		Status(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		public int getValue() {
			return value;
		}
		public String getDesc() {
			return desc;
		}
		public static Status get(int value) {
			return EnumSet.allOf(Status.class).stream().filter(e -> e.value == value).findFirst().get();
		}
	}
    
    public enum IsLock {
    	LOCKED(1), UNLOCKED(0);
    	private int value;
    	IsLock(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
    }
}
