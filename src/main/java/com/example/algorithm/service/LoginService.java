package com.example.algorithm.service;

import com.example.algorithm.utils.Result;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/14 00:34
 */
public interface LoginService {
    Result studentLogin(String stPhone, String stPassword, String token, String path);

    Result teacherLogin(String tPhone, String tPassword, String token, String path);

    Result adminLogin(String adNumber, String adPassword, String token, String path);

    Result updatePassword(Integer stId, String password, String nowPassword, String path);
}
