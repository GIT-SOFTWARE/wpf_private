package com.biostime.bp.authorization.bean.oauth.param;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class GetAccessTokenParam {
	
	@NotNull(message="201")
	private Long clientId;
	
	@NotNull(message="201")
	private String clientSecret;
	
	@NotNull(message="201")
	private String grantType;
}
