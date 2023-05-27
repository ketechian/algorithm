package com.example.algorithm.service;

import com.example.algorithm.utils.Result;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/14 00:33
 */
public interface LogonService {

    /**
     * 用户注册
     * @param name 用户姓名
     * @param phone 用户手机号
     * @param roleGroupId 权限组id
     * @param password 密码
     * @param rwPassword 再输入密码
     * @param mailbox 邮箱
     * @param path 路径
     * @return result
     */
    public Result userRegister(String name, String phone, Integer roleGroupId, String password, String rwPassword,
                               String mailbox, String path);

}
