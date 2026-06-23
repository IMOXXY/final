package com.his.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

public class JWTUtils {

    // 自定义密钥，建议从配置文件中读取
    private static final String SECRET = "his_secret_key";
    
    // 过期时间（单位毫秒） 例如：1 小时
    private static final long EXPIRE_TIME = 60 * 60 * 1000;

    /**
     * 生成 Token
     * @param username 用户名
     * @return JWT字符串
     */
    public static String generateToken(String username) {
        Date now = new Date();
        Date expireAt = new Date(now.getTime() + EXPIRE_TIME);

        return JWT.create()
                .withSubject(username)
                .withIssuedAt(now)
                .withExpiresAt(expireAt)
                .sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 校验 Token 是否有效
     * @param token token 字符串
     * @return 是否通过验证
     */
    public static boolean verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    /**
     * 从 token 中获取用户名
     * @param token token 字符串
     * @return 用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getSubject();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
