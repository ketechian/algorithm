package com.example.algorithm.domain.po;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * class
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class implements Serializable {

    public Class(String clName, Date clCreateTime, Date clEndTime, String clStatus, String clIntroduce, Integer clPictureId) {
        this.clName = clName;
        this.clCreateTime = clCreateTime;
        this.clEndTime = clEndTime;
        this.clStatus = clStatus;
        this.clIntroduce = clIntroduce;
        this.clPictureId = clPictureId;
    }

    /**
     * 班级id
     */
    private Integer clId;

    /**
     * 班级名
     */
    private String clName;

    /**
     * 班级开课时间
     */
    private Date clCreateTime;

    /**
     * 班级结束时间
     */
    private Date clEndTime;

    /**
     * 班级状态
     */
    private String clStatus;

    /**
     * 班级介绍
     */
    private String clIntroduce;

    /**
     * 班级或课程封面,图片id
     */
    private Integer clPictureId;

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