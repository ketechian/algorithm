package com.example.algorithm.mapper;

import com.example.algorithm.domain.po.Examine;
import com.example.algorithm.domain.po.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExamineMapper {
    int deleteByPrimaryKey(Integer exId);

    int insert(Examine record);

    Examine selectByPrimaryKey(Integer exId);

    int updateByPrimaryKey(Examine record);

    Integer countAllByTeacherId(Integer teacherId);

    List<Examine> selectByFromId(Integer teacherId, Integer start, Integer count);

    Integer countNotProcessedByTeacherId(Integer teacherId);

    List<Examine> selectNotProcessedByFromId(Integer teacherId, Integer start, Integer count);

    void checkByPrimaryKey(Integer examineId);
}