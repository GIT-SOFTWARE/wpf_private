package com.biostime.bp.authorization.common;

/**
 * 
* 类功能描述: TODO
*
* @version 1.0
* @author 1394
 */
public enum LanguageDataType {

	/**
	 * 菜单
	 */
	MENU(1);




    private int dataType;

    LanguageDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getDataType() {
        return dataType;
    }
}
