package com.example.algorithm.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Calendar;
import java.util.Map;


/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/14 01:56
 */

public class JWTUtil {

    //加密用密文
    private final static String SING="MYKEYS";


    /**
     * 生成token
     * @param map 用于添加验证信息
     * @return
     */
    public static String createToken(Map<String ,String> map){
        Calendar instance = Calendar.getInstance();
        //设置过期时间为7天
        instance.add(Calendar.DATE, 7);
        JWTCreator.Builder builder =JWT.create();
        map.forEach(builder::withClaim);
        return builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SING));
    }

    /**
     * 验证token
     * @param token 传入token
     * @return
     */
    public static Integer verify(String token){
        JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(SING)).build();
        try{
            jwtVerifier.verify(token);
            return 1;
        }catch (TokenExpiredException e){
            System.out.println("登录超时");
            return -1;
        }catch (JWTDecodeException e){
            System.out.println("解码失败");
            return -2;
        }
    }

    /**
     * 从token中获取字符串类型的信息
     * @param token token
     * @param search 查询内容的键
     * @return
     */
    public static String getString(String token,String search){
        try {
            return JWT.decode(token).getClaim(search).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 从token中获取字符串类型的信息
     * @param token token
     * @param search 查询内容的键
     * @return
     */
    public static Integer getInteger(String token,String search){
        try {
            return JWT.decode(token).getClaim(search).asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 从token中获取整数型类型的信息
     * @param token token
     * @param search 查询内容的键
     * @return
     */
    public static Double getDouble(String token,String search){
        try {
            return JWT.decode(token).getClaim(search).asDouble();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 从token中获取解密后token(ps:暂时用不到)
     * @param token token
     * @return
     */
    public static DecodedJWT getTokenInfo(String token){
        //获取 token 得 DecodedJWT
        return JWT.require(Algorithm.HMAC256(SING))
                .build()
                .verify(token);
    }
}
