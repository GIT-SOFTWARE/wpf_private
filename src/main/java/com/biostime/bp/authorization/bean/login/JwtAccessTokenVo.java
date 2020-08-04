package com.biostime.bp.authorization.bean.login;

import lombok.Data;
@Data
public class JwtAccessTokenVo {
    private String accessToken;
    private long expiresIn;
}
