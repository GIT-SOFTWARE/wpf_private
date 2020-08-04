package com.biostime.bp.authorization.bean.oauth;

import lombok.Data;
@Data
public class OauthAccessTokenVo {
    private String accessToken;
    private long expiresIn;
}
