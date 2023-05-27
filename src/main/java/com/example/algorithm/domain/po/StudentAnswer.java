package com.example.algorithm.domain.po;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * student_answer
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAnswer implements Serializable {
    public StudentAnswer(Integer saStudentId, Integer saTaskId, Integer saTopicId, Integer saPictureId) {
        this.saStudentId = saStudentId;
        this.saTaskId = saTaskId;
        this.saTopicId = saTopicId;
        this.saPictureId = saPictureId;
    }

    public StudentAnswer(Integer saStudentId, Integer saTaskId,Integer saTopicId, String saAnswer) {
        this.saStudentId = saStudentId;
        this.saTaskId = saTaskId;
        this.saTopicId = saTopicId;
        this.saAnswer = saAnswer;
    }

    /**
     * 学生答案id
     */
    private Integer saId;

    /**
     * 学生id
     */
    private Integer saStudentId;

    /**
     * 任务id
     */
    private Integer saTaskId;

    /**
     * 题目id
     */
    private Integer saTopicId;

    /**
     * 大题图片id
     */
    private Integer saPictureId;

    /**
     * 学生答案,以"/"分隔
     */
    private String saAnswer;

    /**
     * 审核id
     */
    private Integer saExamineId;

    /**
     * 1为存在, 2为删除
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date modifyTime;

    private static final long serialVersionUID = 1L;
}