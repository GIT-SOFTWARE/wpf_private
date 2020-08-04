package com.biostime.bp.authorization.domain.resource;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bpa_catalogue")
public class BpaCatalogue {
    /**
     * 菜单主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 父级菜单id
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 应用编码
     */
    @Column(name = "app_code")
    private String appCode;

    /**
     * 目录图标
     */
    @Column(name = "icon_url")
    private String iconUrl;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单链接
     */
    private String url;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 备注
     */
    private String remark;

    /**
     * 类型 1-父菜单 2-子菜单
     */
    private Integer type;

    /**
     * 状态0 -停用 1-启用 2-删除
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
     * 修改人
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取菜单主键
     *
     * @return id - 菜单主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置菜单主键
     *
     * @param id 菜单主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取父级菜单id
     *
     * @return parent_id - 父级菜单id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父级菜单id
     *
     * @param parentId 父级菜单id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
     * 获取目录图标
     *
     * @return icon_url - 目录图标
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * 设置目录图标
     *
     * @param iconUrl 目录图标
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    /**
     * 获取菜单名称
     *
     * @return name - 菜单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置菜单名称
     *
     * @param name 菜单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取菜单链接
     *
     * @return url - 菜单链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置菜单链接
     *
     * @param url 菜单链接
     */
    public void setUrl(String url) {
        this.url = url;
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
     * 获取类型 1-父菜单 2-子菜单
     *
     * @return type - 类型 1-父菜单 2-子菜单
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型 1-父菜单 2-子菜单
     *
     * @param type 类型 1-父菜单 2-子菜单
     */
    public void setType(Integer type) {
        this.type = type;
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
     * 获取修改人
     *
     * @return update_by - 修改人
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置修改人
     *
     * @param updateBy 修改人
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}