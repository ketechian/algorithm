package com.example.algorithm.domain.dto;

import lombok.Data;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/18 16:25
 */
@Data
public class TaskDto {
    /**
     * 任务所需完成题目id
     */
    private String tgTopicId;

    /**
     * 发布人id
     */
    private Integer tgForId;

    /**
     * 发布班级id,需要权限
     */
    private Integer tgFromClass;
    /**
     * 任务描述
     *
     */
    private String taskIntroduction;

}
