package com.biostime.bp.authorization.domain.user;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bpa_user_attr")
public class BpaUserAttr {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 属性code
     */
    @Column(name = "attr_code")
    private String attrCode;

    /**
     * 属性值
     */
    @Column(name = "attr_value")
    private String attrValue;

    /**
     * 账户类型
     */
    @Column(name = "account_type")
    private Integer accountType;

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
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

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
     * 获取属性code
     *
     * @return attr_code - 属性code
     */
    public String getAttrCode() {
        return attrCode;
    }

    /**
     * 设置属性code
     *
     * @param attrCode 属性code
     */
    public void setAttrCode(String attrCode) {
        this.attrCode = attrCode;
    }

    /**
     * 获取属性值
     *
     * @return attr_value - 属性值
     */
    public String getAttrValue() {
        return attrValue;
    }

    /**
     * 设置属性值
     *
     * @param attrValue 属性值
     */
    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    /**
     * 获取账户类型
     *
     * @return account_type - 账户类型
     */
    public Integer getAccountType() {
        return accountType;
    }

    /**
     * 设置账户类型
     *
     * @param accountType 账户类型
     */
    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
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
}