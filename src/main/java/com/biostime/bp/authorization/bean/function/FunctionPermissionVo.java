package com.biostime.bp.authorization.bean.function;

import lombok.Data;

@Data
public class FunctionPermissionVo {
	  /**
     * 主键
     */
    private Long id;

    /**
     * 应用编码
     */
    private String appCode;

    /**
     * 功能编码
     */
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
    private String group;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 功能类型
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
    private Long relationId;

    /**
     * 状态 0-停用 1-有效 2-已删除
     */
    private Integer status;
}
