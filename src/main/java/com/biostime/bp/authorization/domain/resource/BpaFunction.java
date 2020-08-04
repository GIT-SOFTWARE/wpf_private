package com.biostime.bp.authorization.domain.resource;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bpa_function")
public class BpaFunction {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 应用编码
     */
    @Column(name = "app_code")
    private String appCode;

    /**
     * 功能编码
     */
    @Column(name = "function_code")
    private String functionCode;

    /**
     * 功能名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 功能分组
     */
    @Column(name="`group`")
    private String group;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 功能类型 1-按钮
     */
    private Integer type;

    /**
     * URL
     */
    private String url;

    /**
     * 属性
     */
    private String attribute;

    /**
     * 菜单id
     */
    @Column(name = "relation_id")
    private Long relationId;

    /**
     * 状态 0-停用 1-有效 2-已删除
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
     * 获取应用编码
     *
     * @return app_code - 应用编码
     */
    public String getAppCode() {
        return appCode;
    }

    /**
     * 设置应用编码
     *
     * @param appCode 应用编码
     */
    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    /**
     * 获取功能编码
     *
     * @return function_code - 功能编码
     */
    public String getFunctionCode() {
        return functionCode;
    }

    /**
     * 设置功能编码
     *
     * @param functionCode 功能编码
     */
    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    /**
     * 获取功能名称
     *
     * @return name - 功能名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置功能名称
     *
     * @param name 功能名称
     */
    public void setName(String name) {
        this.name = name;
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
     * 获取功能分组
     *
     * @return group - 功能分组
     */
    public String getGroup() {
        return group;
    }

    /**
     * 设置功能分组
     *
     * @param group 功能分组
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * 获取权重
     *
     * @return weight - 权重
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 设置权重
     *
     * @param weight 权重
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * 获取功能类型 1-按钮
     *
     * @return type - 功能类型 1-按钮
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置功能类型 1-按钮
     *
     * @param type 功能类型 1-按钮
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取URL
     *
     * @return url - URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置URL
     *
     * @param url URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取属性
     *
     * @return attribute - 属性
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * 设置属性
     *
     * @param attribute 属性
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    /**
     * 获取菜单id
     *
     * @return relation_id - 菜单id
     */
    public Long getRelationId() {
        return relationId;
    }

    /**
     * 设置菜单id
     *
     * @param relationId 菜单id
     */
    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    /**
     * 获取状态 0-停用 1-有效 2-已删除
     *
     * @return status - 状态 0-停用 1-有效 2-已删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 0-停用 1-有效 2-已删除
     *
     * @param status 状态 0-停用 1-有效 2-已删除
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
}