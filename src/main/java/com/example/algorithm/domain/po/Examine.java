package com.example.algorithm.domain.po;

import java.io.Serializable;
import java.util.Date;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * examine
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Examine implements Serializable {
    public Examine(Integer exForId, Integer exFromId, Integer exTopicId, Integer exHyperlinksId, Integer exAnswerId) {
        this.exForId = exForId;
        this.exFromId = exFromId;
        this.exTopicId = exTopicId;
        this.exHyperlinksId = exHyperlinksId;
        this.exAnswerId = exAnswerId;
    }

    /**
     * 审核id
     */
    private Integer exId;

    /**
     * 申请人id
     */
    private Integer exForId;

    /**
     * 审核人id
     */
    private Integer exFromId;

    /**
     * 题目id
     */
    private Integer exTopicId;

    /**
     * 审核超链接id
     */
    private Integer exHyperlinksId;

    /**
     * 答题表id
     */
    private Integer exAnswerId;

    private Integer exCheck;

    private Integer isDelete;

    private Date createTime;

    private Date modifyTime;

    private static final long serialVersionUID = 1L;
}