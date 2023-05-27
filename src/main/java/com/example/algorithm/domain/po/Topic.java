package com.example.algorithm.domain.po;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * topic
 * @author 
 */
@Data
@AllArgsConstructor
public class Topic implements Serializable {
    public Topic(String tpName, Integer tpType, Integer tpPictureId, Integer tpHyperlinksId,
                 String tpText, String tpOption, String tpAnswer, String tpTag, Integer tpTeacherId) {
        this.tpName = tpName;
        this.tpType = tpType;
        this.tpPictureId = tpPictureId;
        this.tpHyperlinksId = tpHyperlinksId;
        this.tpText = tpText;
        this.tpOption = tpOption;
        this.tpAnswer = tpAnswer;
        this.tpTag = tpTag;
        this.tpTeacherId = tpTeacherId;
    }

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

    /**
     * 大题,超链接id
     */
    private Integer tpHyperlinksId;

    /**
     * 题干
     */
    private String tpText;

    /**
     * 选项
     */
    private String tpOption;

    /**
     * 题目答案,以"/"分隔
     */
    private String tpAnswer;

    /**
     * 标签,以"/"分隔
     */
    private String tpTag;

    /**
     * 出题老师id
     */
    private Integer tpTeacherId;

    /**
     * 做题人数
     */
    private Integer tpPersons;

    /**
     * 正确人数
     */
    private Integer tpRightPersons;

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