package com.biostime.bp.authorization.controller.oauth;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.biostime.bp.authorization.bean.base.BaseResponse;
import com.biostime.bp.authorization.bean.oauth.OauthAccessTokenVo;
import com.biostime.bp.authorization.bean.oauth.param.GetAccessTokenParam;
import com.biostime.bp.authorization.service.oauth.OauthService;


@RestController
@RequestMapping("/oauth")
public class OauthController {
	
	@Autowired
	private OauthService oauthService;
	
	
	@PostMapping("/token")
	public BaseResponse<OauthAccessTokenVo> getAccessToken(@RequestBody @Valid GetAccessTokenParam param){
		return BaseResponse.buildSuccessResp(oauthService.getAccessToken(param));
	}
	
	
	

}
