package com.example.algorithm.mapper;

import com.example.algorithm.domain.po.TaskGroup;
import org.apache.ibatis.annotations.Mapper;

import com.example.algorithm.domain.po.TaskGroup;

import java.util.List;

@Mapper

public interface TaskGroupMapper {
    int deleteByPrimaryKey(Integer tgId);

    int insert(TaskGroup record);

    int insertSelective(TaskGroup record);

    TaskGroup selectByPrimaryKey(Integer tgId);

    int updateByPrimaryKeySelective(TaskGroup record);

    int updateByPrimaryKey(TaskGroup record);

    int updateRight(Integer tgId);

    int countByTeacher(Integer teacherId);

    List<TaskGroup> selectByTeacherId(Integer teacherId, Integer start, Integer count);
}