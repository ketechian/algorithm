package com.example.algorithm.domain.dto;

import lombok.Data;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/14 17:56
 */

@Data
public class LoginAdminDto {
    private String phone;

    private String password;

    private String roleGroup;

    private String number;
}
