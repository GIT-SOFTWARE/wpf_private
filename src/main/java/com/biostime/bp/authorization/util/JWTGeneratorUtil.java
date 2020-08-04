package com.biostime.bp.authorization.util;

import io.jsonwebtoken.*;

import java.security.PrivateKey;
import java.util.Date;

/**
 * JWT生成器
 */

public class JWTGeneratorUtil {

	public static void main(String[] args) {
		//RSA test
		/*SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        JwtBuilder builder = Jwts.builder()
                .setId("jwt")
                .setIssuedAt(new Date())
                .setSubject("哈啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊")
                .setIssuer("jwt").signWith(SignatureAlgorithm.RS256,new PrivateKey );*/
	}

    /**
     * 生成JWT
     *
     * @param id
     * @param subject
     * @param ttlMills
     * @return
     */
    public static String createJWT(String id,String issuer,String key,String subject,Date now ,long ttlMills) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .compressWith(CompressionCodecs.GZIP)
                .signWith(signatureAlgorithm, key);
        if (ttlMills >= 0) {
            long expMills = now.getTime() + ttlMills;
            Date exp = new Date(expMills);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt,String key) throws Exception {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
        return claims;
    }
    
 

}
