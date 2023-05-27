package com.example.algorithm.domain.po;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * task_group
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskGroup implements Serializable {
    public TaskGroup(String tgTopicId, Integer tgForId, Integer tgFromClass, Integer tgShould, Integer tgActual) {
        this.tgTopicId = tgTopicId;
        this.tgForId = tgForId;
        this.tgFromClass = tgFromClass;
        this.tgShould = tgShould;
        this.tgActual = tgActual;
    }

    /**
     * 任务id
     */
    private Integer tgId;

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
     * 应该完成人数
     */
    private Integer tgShould;

    /**
     * 实际完成人数
     */
    private Integer tgActual;

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