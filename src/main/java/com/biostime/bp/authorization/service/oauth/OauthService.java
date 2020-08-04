package com.biostime.bp.authorization.service.oauth;


import com.biostime.bp.authorization.bean.oauth.OauthAccessTokenVo;
import com.biostime.bp.authorization.bean.oauth.param.GetAccessTokenParam;

public interface OauthService {

	OauthAccessTokenVo getAccessToken(GetAccessTokenParam param);
	
	public OauthAccessTokenVo getAccessTokenByClient(GetAccessTokenParam param);

}
