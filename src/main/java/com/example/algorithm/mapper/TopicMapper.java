package com.example.algorithm.mapper;

import com.example.algorithm.domain.po.Topic;
import com.example.algorithm.domain.vo.TopicAllVo;
import com.example.algorithm.domain.vo.TopicVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TopicMapper {
    int deleteByPrimaryKey(Integer tpId);

    int insert(Topic record);

    Topic selectByPrimaryKey(Integer tpId);

    int updateByPrimaryKey(Topic record);

    List<TopicAllVo> selectAllTopic();

    TopicVo selectTopic(Integer topicId);

    int updateRight(Integer topicId);

    int updateError(Integer topicId);

}