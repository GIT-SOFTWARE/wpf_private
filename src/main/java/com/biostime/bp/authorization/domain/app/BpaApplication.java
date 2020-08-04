package com.biostime.bp.authorization.domain.app;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bpa_application")
public class BpaApplication {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 应用编码 唯一的,逻辑唯一
     */
    @Column(name = "app_code")
    private String appCode;

    /**
     * 应用名称
     */
    @Column(name = "app_name")
    private String appName;

    /**
     * 状态0 -停用 1-启用 2-删除
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

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
     * 获取应用编码 唯一的,逻辑唯一
     *
     * @return app_code - 应用编码 唯一的,逻辑唯一
     */
    public String getAppCode() {
        return appCode;
    }

    /**
     * 设置应用编码 唯一的,逻辑唯一
     *
     * @param appCode 应用编码 唯一的,逻辑唯一
     */
    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    /**
     * 获取应用名称
     *
     * @return app_name - 应用名称
     */
    public String getAppName() {
        return appName;
    }

    /**
     * 设置应用名称
     *
     * @param appName 应用名称
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * 获取状态0 -停用 1-启用 2-删除
     *
     * @return status - 状态0 -停用 1-启用 2-删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态0 -停用 1-启用 2-删除
     *
     * @param status 状态0 -停用 1-启用 2-删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
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