package com.biostime.bp.authorization.exception;

import com.biostime.bp.authorization.bean.base.BaseResponse;

import lombok.Getter;

/**
 * @author <a href="mailto:yongfeng.huang@hh.global">11517</a>
 * @date 2018/9/30 16:41
 */
public class BusinessException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6220600031515826750L;
	@SuppressWarnings("rawtypes")
	@Getter
    private BaseResponse baseResponse;

    public BusinessException(String code, Object... args) {
        super();
        this.baseResponse = BaseResponse.buildResp(code, null, args);
    }

}
