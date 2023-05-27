package com.example.algorithm.mapper;

import com.example.algorithm.domain.po.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int deleteByPrimaryKey(Integer cmId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer cmId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    int updateLike(Integer topicId);

    int updateUnlike(Integer topicId);

    int countComment(Integer topicId);

    List<Comment> selectByTopicId(Integer topicId, Integer start, Integer count);
}