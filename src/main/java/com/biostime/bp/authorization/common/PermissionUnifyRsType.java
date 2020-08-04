package com.biostime.bp.authorization.common;

/**
 * 权限资源统一类型
 * 1 菜单 2 功能 3资源
 *
 */
public enum PermissionUnifyRsType {
	/**
	 * 菜单
	 */
	MENU(1),
	/**
	 * 功能
	 */
	FUNCTION(2),
	/**
	 * 资源
	 */
	RESOURCES(3);
	
	private int type;
	
	PermissionUnifyRsType(int type){
		this.type = type;
	}

	public int getType() {
		return type;
	}
	
	

}
