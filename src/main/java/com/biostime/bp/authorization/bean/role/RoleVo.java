package com.biostime.bp.authorization.bean.role;

import lombok.Data;

@Data
public class RoleVo {
	  /**
     * 角色主键
     */
    private Long id;

    /**
     * 应用编码
     */
    private String appCode;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 状态 0 -停用 1-启用 2-删除
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;






}
