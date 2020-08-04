package com.biostime.bp.authorization.service.log.impl;

import java.text.MessageFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biostime.bp.authorization.bean.login.param.LoginParam;
import com.biostime.bp.authorization.common.OperateLogContentTemplate;
import com.biostime.bp.authorization.common.OperateType;
import com.biostime.bp.authorization.domain.log.BpaOperateLog;
import com.biostime.bp.authorization.domain.user.BpaUser;
import com.biostime.bp.authorization.repository.log.BpaOperateLogMapper;
import com.biostime.bp.authorization.service.log.OperateLogService;
import com.biostime.bp.authorization.util.IPUtil;
import com.biostime.jwt.client.bpa.util.ClientUtil;

/** 
 * 描述: TODO
 *
 * @author <a href="mailto:zhangguojian@hh.global">12552</a>
 * @createDate 2019年5月22日 下午12:41:52
 */
@Service
public class OperateLogServiceImpl implements OperateLogService {
	@Autowired
	private BpaOperateLogMapper bpaOperateLogMapper;
	
	@Override
	@Transactional
	public void logOperateLog(OperateType operateType, BpaUser user, LoginParam param, Long operateCustId) {
		BpaOperateLog log = new BpaOperateLog();
		log.setIp(IPUtil.getIpAddr());
		log.setOperateTime(new Date());
		log.setOperateType(operateType.getType());
		switch (operateType) {
			case LOGIN_PASSWORD_IS_ERROR :
				log.setOperateUid(user.getUid());
				log.setOperateContent(MessageFormat.format(OperateLogContentTemplate.LOGIN_PASSWORD_IS_ERROR,
						param.getAccount(), param.getAppCode()));
				break;
			case LOGIN_SUCCESS :
				log.setOperateUid(user.getUid());
				log.setOperateContent(MessageFormat.format(OperateLogContentTemplate.LOGIN_SUCCESS, param.getAccount(),
						param.getAppCode()));
				break;
			case DISCONTINUE_ACCOUNT :
				log.setOperateUid(operateCustId);
				log.setToOperateUid(user.getUid());
				log.setOperateContent(MessageFormat.format(OperateLogContentTemplate.DISCONTINUE_ACCOUNT,
						operateCustId, user.getUid(), ClientUtil.getLoginUser().getAppCode()));
				break;
			case ENABLE_ACCOUNT :
				log.setOperateUid(operateCustId);
				log.setToOperateUid(user.getUid());
				log.setOperateContent(MessageFormat.format(OperateLogContentTemplate.ENABLE_ACCOUNT, operateCustId,
						user.getUid(), ClientUtil.getLoginUser().getAppCode()));
				break;
			case ADMIN_CHANGE_USER_PASSWORD :
				log.setOperateUid(operateCustId);
				log.setToOperateUid(user.getUid());
				log.setOperateContent(MessageFormat.format(OperateLogContentTemplate.ADMIN_CHANGE_USER_PWD, operateCustId,
						user.getUid(), ClientUtil.getLoginUser().getAppCode()));
				break;
			case USER_CHANGE_PASSWORD :
				log.setOperateUid(operateCustId);
				log.setToOperateUid(user.getUid());
				log.setOperateContent(MessageFormat.format(OperateLogContentTemplate.USER_CHANGE_PWD, user.getUid(), ""));
				break;	
			default :
				break;
		}
		bpaOperateLogMapper.insert(log);
	}
}
