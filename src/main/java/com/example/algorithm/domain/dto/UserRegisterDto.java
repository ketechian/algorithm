package com.example.algorithm.domain.dto;

import lombok.Data;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/14 00:52
 */

@Data
public class UserRegisterDto {

    private String username;

    private String phone;

    private Integer roleGroupId;

    private String password;

    private String rwPassword;

    private String mailbox;
}
