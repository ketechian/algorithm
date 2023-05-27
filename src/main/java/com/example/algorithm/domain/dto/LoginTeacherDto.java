package com.example.algorithm.domain.dto;

import lombok.Data;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/14 17:35
 */
@Data
public class LoginTeacherDto {
    private String phone;

    private String password;

    private String roleGroup;
}
