package com.example.algorithm.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/20 13:52
 */
@Data
public class ClassVo {
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

    private String clPicturePath;

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
}
