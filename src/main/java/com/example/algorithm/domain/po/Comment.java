package com.example.algorithm.domain.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * comment
 * @author 
 */
@Data
@NoArgsConstructor
public class Comment implements Serializable {
    public Comment(Integer cmTopicId, Integer cmUserId, Integer cmUserRole, Integer cmFloor, String cmContent, Integer cmLikes, Integer cmUnlikes) {
        this.cmTopicId = cmTopicId;
        this.cmUserId = cmUserId;
        this.cmUserRole = cmUserRole;
        this.cmFloor = cmFloor;
        this.cmContent = cmContent;
        this.cmLikes = cmLikes;
        this.cmUnlikes = cmUnlikes;
    }

    /**
     * 评论id
     */
    private Integer cmId;

    /**
     * 评论题目id
     */
    private Integer cmTopicId;

    /**
     * cm_user_id
     */
    private Integer cmUserId;

    /**
     * 评论者权限组,判断角色
     */
    private Integer cmUserRole;

    /**
     * 评论楼层
     */
    private Integer cmFloor;

    /**
     * 楼中楼层数
     */
    private Integer cmDFloor;

    /**
     * 回复楼中楼层数
     */
    private Integer cmReply;

    /**
     * 评论内容
     */
    private String cmContent;

    /**
     * 点赞数
     */
    private Integer cmLikes;

    /**
     * 点踩数
     */
    private Integer cmUnlikes;

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