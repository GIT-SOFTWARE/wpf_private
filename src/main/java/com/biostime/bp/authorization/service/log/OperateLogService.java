package com.biostime.bp.authorization.service.log;

import com.biostime.bp.authorization.bean.login.param.LoginParam;
import com.biostime.bp.authorization.common.OperateType;
import com.biostime.bp.authorization.domain.user.BpaUser;

/** 
 * 描述: TODO
 *
 * @author <a href="mailto:zhangguojian@hh.global">12552</a>
 * @createDate 2019年5月22日 下午12:41:33
 */
public interface OperateLogService {
	public void logOperateLog(OperateType operateType, BpaUser user, LoginParam param, Long operateCustId);
}
