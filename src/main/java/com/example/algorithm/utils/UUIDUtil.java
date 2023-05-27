package com.example.algorithm.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/14 00:19
 */
@Component
public class UUIDUtil {
    /**
     * 生成UUID
     * @return
     */
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString().replace("-","");
        return uuid;
    }
}
