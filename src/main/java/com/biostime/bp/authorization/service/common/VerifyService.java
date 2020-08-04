package com.biostime.bp.authorization.service.common;

import com.biostime.bp.authorization.common.VerifyType;

public interface VerifyService {
	/**
	 * 如果验证不通过将会抛出异常
	 * @param validateCode
	 * @param verifySeq
	 * @param verifyType
	 */
	public void validateVerify(String validateCode,String verifySeq,VerifyType verifyType);

}
