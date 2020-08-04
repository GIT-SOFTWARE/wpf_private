package com.biostime.bp.authorization.domain.user;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bpa_user")
public class BpaUser {
    /**
     * 用户id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long uid;

    /**
     * 帐号
     */
    @Column(name = "account")
    private String account;

    /**
     * 账户类型 1-邮箱 2-手机号 3-工号 4-其他
     */
    @Column(name = "account_type")
    private Integer accountType;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 人名
     */
    @Column(name = "person_name")
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
    @Column(name = "email")
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
    @Column(name = "org_code")
    private String orgCode;

    /**
     * 部门名称
     */
    @Column(name = "org_name")
    private String orgName;

    /**
     * 系统来源
     */
    @Column(name = "source_system")
    private String sourceSystem;

    /**
     * 创建类型 1-邮箱注册创建2-手机号注册创建 3-系统创建
     */
    @Column(name = "create_type")
    private Integer createType;

    /**
     * 是否锁定 1-已锁定 0-未锁定
     */
    @Column(name = "is_lock")
    private Integer isLock;

    /**
     * 状态 1-有效 0-停用 2-已删除
     */
    private Integer status;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 国家
     */
    private String country;

    /**
     * 获取用户id
     *
     * @return uid - 用户id
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 设置用户id
     *
     * @param uid 用户id
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 获取帐号
     *
     * @return account - 帐号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置帐号
     *
     * @param account 帐号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取账户类型 1-邮箱 2-手机号 3-工号 4-其他
     *
     * @return account_type - 账户类型 1-邮箱 2-手机号 3-工号 4-其他
     */
    public Integer getAccountType() {
        return accountType;
    }

    /**
     * 设置账户类型 1-邮箱 2-手机号 3-工号 4-其他
     *
     * @param accountType 账户类型 1-邮箱 2-手机号 3-工号 4-其他
     */
    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取人名
     *
     * @return person_name - 人名
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * 设置人名
     *
     * @param personName 人名
     */
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    /**
     * 获取性别 1-男 2-女
     *
     * @return gender - 性别 1-男 2-女
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 设置性别 1-男 2-女
     *
     * @param gender 性别 1-男 2-女
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 获取手机号码
     *
     * @return phone - 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号码
     *
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取座机号码
     *
     * @return telephone - 座机号码
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置座机号码
     *
     * @param telephone 座机号码
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取公司
     *
     * @return company - 公司
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设置公司
     *
     * @param company 公司
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 获取部门编码
     *
     * @return org_code - 部门编码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置部门编码
     *
     * @param orgCode 部门编码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * 获取部门名称
     *
     * @return org_name - 部门名称
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 设置部门名称
     *
     * @param orgName 部门名称
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 获取系统来源
     *
     * @return source_system - 系统来源
     */
    public String getSourceSystem() {
        return sourceSystem;
    }

    /**
     * 设置系统来源
     *
     * @param sourceSystem 系统来源
     */
    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    /**
     * 获取创建类型 1-邮箱注册创建2-手机号注册创建 3-系统创建
     *
     * @return create_type - 创建类型 1-邮箱注册创建2-手机号注册创建 3-系统创建
     */
    public Integer getCreateType() {
        return createType;
    }

    /**
     * 设置创建类型 1-邮箱注册创建2-手机号注册创建 3-系统创建
     *
     * @param createType 创建类型 1-邮箱注册创建2-手机号注册创建 3-系统创建
     */
    public void setCreateType(Integer createType) {
        this.createType = createType;
    }

    /**
     * 获取是否锁定 1-已锁定 0-未锁定
     *
     * @return is_lock - 是否锁定 1-已锁定 0-未锁定
     */
    public Integer getIsLock() {
        return isLock;
    }

    /**
     * 设置是否锁定 1-已锁定 0-未锁定
     *
     * @param isLock 是否锁定 1-已锁定 0-未锁定
     */
    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    /**
     * 获取状态 1-有效 0-停用 2-已删除
     *
     * @return status - 状态 1-有效 0-停用 2-已删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 1-有效 0-停用 2-已删除
     *
     * @param status 状态 1-有效 0-停用 2-已删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建人
     *
     * @return create_by - 创建人
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新人
     *
     * @return update_by - 更新人
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置更新人
     *
     * @param updateBy 更新人
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取国家
     *
     * @return country - 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家
     *
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country;
    }
}