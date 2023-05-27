package com.example.algorithm.domain.dto;

import lombok.Data;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/18 14:51
 */
@Data
public class InformationGroupDto {
    /**
     * 消息内容
     */
    private String igContent;

    /**
     * 发布人id
     */
    private Integer igForId;

    /**
     * 权限组id
     */
    private Integer igRoleId;

    /**
     * 1为是, 2为否, 需要权限
     */
    private Integer igFromAll;

    /**
     * 发布班级id,需要权限
     */
    private Integer igFromClass;
}
