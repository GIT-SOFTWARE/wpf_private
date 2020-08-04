package com.biostime.bp.authorization.bean.base;


/**
 * @author <a href="mailto:yongfeng.huang@hh.global">11517</a>
 * @date 2018/9/11 14:05
 */

public class PageParam extends BaseRequest {
	private static final int DEFAULT_PAGE_NUM = 1;

	private static final int DEFAULT_PAGE_SIZE = 10;
   
    private Integer pageNum;
 
    private Integer pageSize;


	public Integer getPageNum() {
		return pageNum==null?DEFAULT_PAGE_NUM:pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize==null?DEFAULT_PAGE_SIZE:pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
