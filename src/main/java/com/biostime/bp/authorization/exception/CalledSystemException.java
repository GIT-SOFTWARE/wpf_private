package com.biostime.bp.authorization.exception;

import lombok.Getter;

/**
 * 调用第三方系统异常
 *
 * @author <a href="mailto:yongfeng.huang@hh.global">11517</a>
 * @date 2018/10/11 16:16
 */
public class CalledSystemException extends RuntimeException {

    private static final long serialVersionUID = -2687297344964130676L;

    @Getter
    private String originalCode;

    @Getter
    private String originalSystem;

    @Getter
    private String mergeReturnCode;

    /**
     * 第三方系统调用返回异常
     *
     * @param originalCode   第三方系统返回的原始编码
     * @param originalSystem 第三方系统名称
     */
    public CalledSystemException(String originalCode, String originalSystem) {
        this.originalCode = originalCode;
        this.originalSystem = originalSystem;
    }

    /**
     * 第三方系统调用返回异常
     *
     * @param mergeReturnCode 问卷系统定义的合并返回码
     */
    public CalledSystemException(String mergeReturnCode) {
        this.mergeReturnCode = mergeReturnCode;
    }

}
