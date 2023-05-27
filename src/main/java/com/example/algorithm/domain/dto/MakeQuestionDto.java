package com.example.algorithm.domain.dto;

import lombok.Data;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/20 19:39
 */

@Data
public class MakeQuestionDto {

    private Integer studentId;

    private Integer taskId;

    private Integer topicId;

    private Integer pictureId;

    private String answer;
}
