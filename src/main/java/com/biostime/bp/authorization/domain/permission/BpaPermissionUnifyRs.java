package com.biostime.bp.authorization.domain.permission;

import javax.persistence.*;

@Table(name = "bpa_permission_unify_rs")
public class BpaPermissionUnifyRs {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 权限id
     */
    @Column(name = "permission_id")
    private Long permissionId;

    /**
     * 关联id
     */
    @Column(name = "relation_id")
    private Long relationId;

    /**
     * 关联类型1 菜单 2 功能 3资源
     */
    private Integer type;

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
     * 获取权限id
     *
     * @return permission_id - 权限id
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * 设置权限id
     *
     * @param permissionId 权限id
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * 获取关联id
     *
     * @return relation_id - 关联id
     */
    public Long getRelationId() {
        return relationId;
    }

    /**
     * 设置关联id
     *
     * @param relationId 关联id
     */
    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    /**
     * 获取关联类型1 菜单 2 功能 3资源
     *
     * @return type - 关联类型1 菜单 2 功能 3资源
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置关联类型1 菜单 2 功能 3资源
     *
     * @param type 关联类型1 菜单 2 功能 3资源
     */
    public void setType(Integer type) {
        this.type = type;
    }
}