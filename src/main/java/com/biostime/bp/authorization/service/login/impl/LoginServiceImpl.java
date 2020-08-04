package com.biostime.bp.authorization.service.login.impl;

import java.util.Arrays;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.biostime.bp.authorization.bean.login.JwtAccessTokenVo;
import com.biostime.bp.authorization.bean.login.JwtUserInfoBo;
import com.biostime.bp.authorization.bean.login.param.LoginParam;
import com.biostime.bp.authorization.common.GlobalVariable;
import com.biostime.bp.authorization.common.OperateType;
import com.biostime.bp.authorization.common.VerifyType;
import com.biostime.bp.authorization.config.properties.JwtServerProperties;
import com.biostime.bp.authorization.config.properties.SystemProperties;
import com.biostime.bp.authorization.domain.app.BpaApplication;
import com.biostime.bp.authorization.domain.log.BpaOperateLog;
import com.biostime.bp.authorization.domain.user.BpaUser;
import com.biostime.bp.authorization.exception.BusinessException;
import com.biostime.bp.authorization.repository.app.BpaApplicationMapper;
import com.biostime.bp.authorization.repository.log.BpaOperateLogMapper;
import com.biostime.bp.authorization.repository.user.BpaUserMapper;
import com.biostime.bp.authorization.service.common.VerifyService;
import com.biostime.bp.authorization.service.log.OperateLogService;
import com.biostime.bp.authorization.service.login.LoginService;
import com.biostime.bp.authorization.util.BeanUtils;
import com.biostime.bp.authorization.util.Functions;
import com.biostime.bp.authorization.util.JWTGeneratorUtil;
import com.biostime.bp.authorization.util.JsonUtils;
import com.biostime.bp.authorization.util.MD5Util;


@Service
@Slf4j
public class LoginServiceImpl implements  LoginService{
	
	@Autowired
	private  BpaUserMapper bpaUserMapper;
	
	
	@Autowired
	private BpaOperateLogMapper bpaOperateLogMapper;
	
	
	@Autowired
	private JwtServerProperties jwtServerProperties;
	
	@Autowired
	private SystemProperties systemProperties;
	
	@Autowired
	private BpaApplicationMapper bpaApplicationMapper;
	
	@Autowired
	private OperateLogService operateLogService;
	
	@Autowired
	private VerifyService verifyService;
	
	@Override
	public JwtAccessTokenVo login(LoginParam param) {
		
		log.info("jwt server login account:{},appCode:{}", param.getAccount(),param.getAppCode());
		
		verifyService.validateVerify(param.getValidateCode(), param.getVerifySeq(), VerifyType.LOGIN);
		
		BpaUser user = queryBpaUserByAccount(param.getAccount());
		if(user==null){
			throw new BusinessException("300");
		}
		//用户已停用
		if(GlobalVariable.STATUS_DISABLE_INT.equals(user.getStatus())){
			throw new BusinessException("301");
		}
		//用户是否为锁定状态
		if(GlobalVariable.TRUE.equals(user.getIsLock())){
			 throw new BusinessException("302");
		}
		//判断当前应用
		BpaApplication application = queryApplicationByAppCode(param.getAppCode());
		if(application==null){
			throw new BusinessException("306");
		}
		if(!GlobalVariable.STATUS_ENABLE_INT.equals(application.getStatus())){
			throw new BusinessException("307");
		}
		//用户密码是否正确 
		if(!MD5Util.md5(param.getPassword()).equals(user.getPassword())){
			//不正确查询已经错误次数
			long errorCount = countUserLoginPasswordErrorByCurrentDay(user.getUid())+1;
			int userPasswordAllowErrorCount = systemProperties.getUserPasswordAllowErrorCount();
			logOperateLog(OperateType.LOGIN_PASSWORD_IS_ERROR,user,param);
			
			if(errorCount>=userPasswordAllowErrorCount){
				if(!GlobalVariable.TRUE.equals(user.getIsLock())){
					//锁定用户
					lockUser(user.getUid());
				}
				throw new BusinessException("305");
			}
			
			if(errorCount<=2){//前两次提示密码错误
				throw new BusinessException("300");
			}
			if(errorCount>=3&&errorCount<=(userPasswordAllowErrorCount-1)){
				throw new BusinessException("304",userPasswordAllowErrorCount-errorCount);
			}
			
		}
	
		
	
		logOperateLog(OperateType.LOGIN_SUCCESS,user,param);
		
		//生成token
		JwtUserInfoBo userInfo = new JwtUserInfoBo();
		BeanUtils.copyProperties(user, userInfo);
		userInfo.setAppCode(param.getAppCode());
		
		JwtAccessTokenVo jwtAccessToken = new JwtAccessTokenVo();
		//具体过期时间 ms
		Date now = new Date();
		jwtAccessToken.setExpiresIn(now.getTime()+jwtServerProperties.getTtlMills());
		String accessToken = JWTGeneratorUtil.createJWT(jwtServerProperties.getJwtId(), jwtServerProperties.getIssuer(),
				jwtServerProperties.getKey(), JsonUtils.toJson(userInfo), now, jwtServerProperties.getTtlMills());
		jwtAccessToken.setAccessToken(accessToken);
		return jwtAccessToken;
	}
	
	
	public BpaApplication queryApplicationByAppCode(String appCode){
		Example exa = new Example(BpaApplication.class);
		Criteria cia = exa.createCriteria();
		cia.andEqualTo("appCode", appCode);
		cia.andIn("status",Arrays.asList(GlobalVariable.STATUS_ENABLE_INT,GlobalVariable.STATUS_DISABLE_INT));
		return bpaApplicationMapper.selectOneByExample(exa);
	}
	
	public void logOperateLog(OperateType operrateType, BpaUser user, LoginParam param) {
		operateLogService.logOperateLog(operrateType, user, param, null);
	}
	
	
	
	
	
	public  BpaUser  queryBpaUserByAccount(String account){
		Example exa = new Example(BpaUser.class);
		Criteria cia = exa.createCriteria();
		cia.andEqualTo("account", account);
		cia.andIn("status",Arrays.asList(GlobalVariable.STATUS_ENABLE_INT,GlobalVariable.STATUS_DISABLE_INT));
		return bpaUserMapper.selectOneByExample(exa);
	}
	
	
	public void lockUser(Long uid){
		BpaUser update = new BpaUser();
		update.setIsLock(GlobalVariable.TRUE);
		update.setUid(uid);
		bpaUserMapper.updateByPrimaryKeySelective(update);
	}
	

	@Override
	public long countUserLoginPasswordErrorByCurrentDay(Long uid) {
		Date startTime = Functions.getNowStartDate();
		Date endTime = new Date();
		BpaOperateLog operateLog = bpaOperateLogMapper.queryLastResetPasswordOrLoginSuccessOperateLogByUid(uid, startTime, endTime);
		if(operateLog!=null) {
			startTime = operateLog.getOperateTime();
		}
		Example exa = new Example(BpaOperateLog.class);
		Criteria cia = exa.createCriteria();
		cia.andEqualTo("operateUid", uid);
		cia.andEqualTo("operateType",OperateType.LOGIN_PASSWORD_IS_ERROR.getType());
		cia.andBetween("operateTime", startTime, endTime);
		return bpaOperateLogMapper.selectCountByExample(exa);
	}

}
