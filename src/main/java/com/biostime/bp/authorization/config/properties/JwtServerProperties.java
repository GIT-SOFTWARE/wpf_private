package com.biostime.bp.authorization.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "jwt.server")
@Data
@Component
public class JwtServerProperties {

    /**
     * Jwt签名钥匙
     */
    private String key;

    /**
     * 过期时间 ms
     */
    private long ttlMills;
    
    private String jwtId;
    
    private String issuer;
    


   

}
