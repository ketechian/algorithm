package com.example.algorithm.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/14 13:20
 */
public class SecretUtil {
    /**
     * 将一段字符串加密为32位的字符密文
     * @param str 要加密的字符串
     * @return
     */
    public static String secretString(String str){
        byte[] digest = null;
        try {
            //使用md5加密
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest  = md5.digest(str.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //16是表示转换为16进制数
        String md5Str = new BigInteger(1, digest).toString(16);
        return md5Str;
    }
}
