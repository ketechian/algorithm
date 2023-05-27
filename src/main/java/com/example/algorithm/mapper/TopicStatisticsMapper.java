package com.example.algorithm.mapper;

import com.example.algorithm.domain.po.TopicStatistics;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TopicStatisticsMapper {
    int deleteByPrimaryKey(Integer tsId);

    int insert(TopicStatistics record);

    int insertSelective(TopicStatistics record);

    TopicStatistics selectByPrimaryKey(Integer tsId);

    int updateByPrimaryKeySelective(TopicStatistics record);

    int updateByPrimaryKey(TopicStatistics record);

    TopicStatistics select(Integer taskId, Integer topicId);

    int updateRight(Integer tsId);
}