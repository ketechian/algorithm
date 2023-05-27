package com.example.algorithm.domain.po;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * task
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {
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

    /**
     * 最后修改时间
     */
    private Date modifyTime;

    private static final long serialVersionUID = 1L;
}