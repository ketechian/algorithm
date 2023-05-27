package com.example.algorithm.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/20 14:42
 */
@Data
public class TopicVo {
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
     * 图片id
     */
    private Integer tpPictureId;

    private String tpPicturePath;

    /**
     * 大题,超链接id
     */
    private Integer tpHyperlinksId;

    /**
     * 题干
     */
    private String tpText;
    /**
     * 题目答案,以"/"分隔
     */
    private String tpAnswer;

    /**
     * 选项
     */
    private String tpOption;

    /**
     * 出题老师id
     */
    private Integer tpTeacherId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 做题人数
     */
    private Integer tpPersons;

    /**
     * 正确人数
     */
    private Integer tpRightPersons;
}
