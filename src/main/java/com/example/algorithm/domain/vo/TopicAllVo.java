package com.example.algorithm.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/20 14:18
 */
@Data
public class TopicAllVo {
    /**
     * 题目id
     */
    private Integer tpId;

    /**
     * 题目名称
     */
    private String tpName;

    /**
     * 题目类型,1单选,2 多选,3 判断,4 填空,5 大题
     */
    private Integer tpType;

    /**
     * 创建时间
     */
    private Date createTime;
}
