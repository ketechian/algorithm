package com.example.algorithm.domain.dto;

import lombok.Data;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/14 15:17
 */

@Data
public class LoginStudentDto {
    private String phone;

    private String password;

    private String roleGroup;
}
