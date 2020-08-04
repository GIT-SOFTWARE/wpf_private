package com.biostime.bp.authorization.bean.menu;


import lombok.Data;

@Data
public class MenuBo {
	 /**
     * 菜单主键
     */
    private Long id;

    /**
     * 父级菜单id
     */
    private Long parentId;

    /**
     * 应用编码
     */
    private String appCode;

    /**
     * 目录图标
     */
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
     * 类型 1-菜单 2-子菜单
     */
    private Integer type;

    /**
     * 状态0 -停用 1-启用 2-删除
     */
    private Integer status;


}
