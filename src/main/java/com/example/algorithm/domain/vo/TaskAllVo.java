package com.example.algorithm.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/21 02:26
 */
@Data
public class TaskAllVo {
    /**
     * 任务id
     */
    private Integer tkId;

    /**
     * 父任务id
     */
    private Integer tkFatherId;

    /**
     * 发布到具体角色id
     */
    private Integer tkFromId;

    /**
     * 题目id
     */
    private Integer tkTopicId;

    private String topicName;

    /**
     * 1为查收, 2为未查收
     */
    private Integer tkCheck;

    /**
     * 1为完成, 2为未完成
     */
    private Integer tkOver;

    /**
     * 1为存在, 2为删除
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createTime;
}
