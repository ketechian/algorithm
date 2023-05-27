package com.example.algorithm.domain.dto;

import lombok.Data;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/21 19:54
 */
@Data
public class CommentDto {
    /**
     * 评论题目id
     */
    private Integer cmTopicId;

    /**
     * 评论用户id
     */
    private Integer cmUserId;

    /**
     * 评论者权限组,判断角色
     */
    private Integer cmUserRole;

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

}
