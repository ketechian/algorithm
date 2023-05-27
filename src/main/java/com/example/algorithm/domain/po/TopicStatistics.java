package com.example.algorithm.domain.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * topic_statistics
 * @author 
 */
@Data
@NoArgsConstructor
public class TopicStatistics implements Serializable {
    public TopicStatistics(Integer tsTaskId, Integer tsTopicId) {
        this.tsTaskId = tsTaskId;
        this.tsTopicId = tsTopicId;
    }

    /**
     * 统计id
     */
    private Integer tsId;

    /**
     * 任务id
     */
    private Integer tsTaskId;

    /**
     * 题目id
     */
    private Integer tsTopicId;

    /**
     * 在一次任务中正确的人数
     */
    private Integer tsTopicRight;

    /**
     * 1为存在, 2为删除
     */
    private Boolean isDelete;

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