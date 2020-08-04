package com.biostime.bp.authorization.domain.permission;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bpa_permission")
public class BpaPermission {
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
     * 权限类型 1 菜单 2 功能 3资源
     */
    private Integer type;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "create_by")
    private String createBy;

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
     * 获取权限类型 1 菜单 2 功能 3资源
     *
     * @return type - 权限类型 1 菜单 2 功能 3资源
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置权限类型 1 菜单 2 功能 3资源
     *
     * @param type 权限类型 1 菜单 2 功能 3资源
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return create_by
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}