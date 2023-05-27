package com.example.algorithm.domain.dto;

import lombok.Data;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/20 18:47
 */
@Data
public class UpdatePasswordDto {
    /**
     * 学生id
     */
    private Integer stId;

    /**
     * md5加密
     */
    private String password;

    /**
     * md5加密
     */
    private String nowPassword;

}
