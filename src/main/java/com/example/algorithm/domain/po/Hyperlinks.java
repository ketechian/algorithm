package com.example.algorithm.domain.po;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * hyperlinks
 * @author 
 */
@Data
@AllArgsConstructor
public class Hyperlinks implements Serializable {
    public Hyperlinks(String hlContent, String hlInformation, Integer hlStatus, Integer hlRoleId, Integer hlUserId,Integer hlExamineId) {
        this.hlContent = hlContent;
        this.hlInformation = hlInformation;
        this.hlStatus = hlStatus;
        this.hlRoleId = hlRoleId;
        this.hlUserId = hlUserId;
        this.hlExamineId = hlExamineId;
    }

    /**
     * 图片id
     */
    private Integer hlId;

    /**
     * 超链接
     */
    private String hlContent;

    /**
     * 超链接描述
     */
    private String hlInformation;

    /**
     * 审核状态,1为审核中,2为不通过,3为通过
     */
    private Integer hlStatus;

    private Integer hlRoleId;

    /**
     * 提交人id
     */
    private Integer hlUserId;

    /**
     * 审核人id
     */
    private Integer hlExamineId;

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