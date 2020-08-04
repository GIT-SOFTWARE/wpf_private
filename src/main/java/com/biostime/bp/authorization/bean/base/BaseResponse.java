package com.biostime.bp.authorization.bean.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="mailto:yongfeng.huang@hh.global">11517</a>
 * @date 2018/9/10 10:42
 */
@SuppressWarnings("serial")
@Data
public class BaseResponse<T> implements Serializable {

    private String seqNo;

    private String code;

    private String desc;

    private Object[] args;

    private T response;

    public static <T> BaseResponse<T> buildSuccessResp(T response) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode("200");
        baseResponse.setResponse(response);
        return baseResponse;
    }

    public static <T> BaseResponse<T> buildResp(String code, String desc, Object... args) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(code);
        baseResponse.setDesc(desc);
        baseResponse.setArgs(args);
        return baseResponse;
    }

    public static <T> BaseResponse<T> error(String seqNo, ResponseEnum e) {
    	BaseResponse<T> baseResponse = new BaseResponse<>();
    	baseResponse.setSeqNo(seqNo);
        baseResponse.setCode(e.getCode());
        baseResponse.setDesc(e.toString());
        baseResponse.setArgs(e.getArgs());
        return baseResponse;
    }
    
    public static <T> BaseResponse<T> success(String seqNo, T data) {
    	BaseResponse<T> res = error(seqNo, ResponseEnum.SUCCESS);
    	res.setResponse(data);
    	return res;
    }
}
