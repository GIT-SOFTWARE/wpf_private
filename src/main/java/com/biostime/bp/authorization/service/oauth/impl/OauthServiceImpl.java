package com.biostime.bp.authorization.service.oauth.impl;

import java.util.Arrays;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biostime.bp.authorization.bean.login.JwtUserInfoBo;
import com.biostime.bp.authorization.bean.oauth.OauthAccessTokenVo;
import com.biostime.bp.authorization.bean.oauth.param.GetAccessTokenParam;
import com.biostime.bp.authorization.cache.OauthCache;
import com.biostime.bp.authorization.common.GlobalVariable;
import com.biostime.bp.authorization.common.oauth.GrantType;
import com.biostime.bp.authorization.config.properties.JwtServerProperties;
import com.biostime.bp.authorization.domain.oauth.BpaOauthClient;
import com.biostime.bp.authorization.domain.user.BpaUser;
import com.biostime.bp.authorization.exception.BusinessException;
import com.biostime.bp.authorization.repository.oauth.BpaOauthClientMapper;
import com.biostime.bp.authorization.service.oauth.OauthService;
import com.biostime.bp.authorization.service.user.UserService;
import com.biostime.bp.authorization.util.BeanUtils;
import com.biostime.bp.authorization.util.JWTGeneratorUtil;
import com.biostime.bp.authorization.util.JsonUtils;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class OauthServiceImpl implements OauthService {

	@Autowired
	private BpaOauthClientMapper bpaOauthClientMapper;

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtServerProperties jwtServerProperties;
	
	@Autowired
	private OauthCache oauthCache;

	@Override
	public OauthAccessTokenVo getAccessToken(GetAccessTokenParam param) {
		OauthAccessTokenVo oauthAccessToken = null;
		GrantType grantType = GrantType.getGrantType(param.getGrantType());
		if (grantType == null) {
			throw new BusinessException("600");
		}

		switch (grantType) {
		case CILENT:
			oauthAccessToken = getAccessTokenByClient(param);
			break;

		default:
			break;
		}

		return oauthAccessToken;
	}

	public OauthAccessTokenVo getAccessTokenByClient(GetAccessTokenParam param) {
		OauthAccessTokenVo oauthAccessToken = null;
		BpaOauthClient bpaOauthClient = queryBpaOauthClientByClientId(param.getClientId());
		if (bpaOauthClient == null) {
			throw new BusinessException("601");
		}
		if (GlobalVariable.STATUS_DISABLE_INT.equals(bpaOauthClient.getStatus())) {
			throw new BusinessException("603");
		}
		if (!bpaOauthClient.getClientSecret().equals(param.getClientSecret())) {
			throw new BusinessException("602");
		}

		BpaUser bpaUser = userService.queryBpaUserByUid(bpaOauthClient.getRelationUid());
		if (bpaUser == null) {
			throw new BusinessException("604");
		}
		if (GlobalVariable.STATUS_DISABLE_INT.equals(bpaUser.getStatus())) {
			throw new BusinessException("603");
		}
		
		String key = oauthCache.getHhBpaOauthAccessTokenCacheKey(param.getClientId(), GrantType.CILENT);
		if(oauthCache.exist(key)) {
			Object obj = oauthCache.get(key);
			if(obj!=null){
				return (OauthAccessTokenVo)obj;
			}
		}
		// 生成token
		JwtUserInfoBo userInfo = new JwtUserInfoBo();
		BeanUtils.copyProperties(bpaUser, userInfo);
		userInfo.setAppCode(GlobalVariable.APP_CODE);
		
		oauthAccessToken = new OauthAccessTokenVo();
		// 具体过期时间 ms
		Date now = new Date();
		oauthAccessToken.setExpiresIn(now.getTime() + jwtServerProperties.getTtlMills());
		String accessToken = JWTGeneratorUtil.createJWT(jwtServerProperties.getJwtId(), jwtServerProperties.getIssuer(),
				jwtServerProperties.getKey(), JsonUtils.toJson(userInfo), now, jwtServerProperties.getTtlMills());
		oauthAccessToken.setAccessToken(accessToken);
		//缩短10分钟 服务器之间有时间差
		long expireSeconds= (jwtServerProperties.getTtlMills()/1000)-10*60;
		oauthCache.set(key, oauthAccessToken,expireSeconds);
		return oauthAccessToken;
	}

	public BpaOauthClient queryBpaOauthClientByClientId(Long clientId) {
		Example exa = new Example(BpaOauthClient.class);
		Criteria cia = exa.createCriteria();
		cia.andIn("status", Arrays.asList(GlobalVariable.STATUS_ENABLE_INT, GlobalVariable.STATUS_DISABLE_INT));
		cia.andEqualTo("clientId", clientId);
		return bpaOauthClientMapper.selectOneByExample(exa);
	}

}
