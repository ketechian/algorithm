package com.example.algorithm.mapper;

import com.example.algorithm.domain.po.StudentAnswer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentAnswerMapper {
    int deleteByPrimaryKey(Integer saId);

    int insert(StudentAnswer record);

    StudentAnswer selectByPrimaryKey(Integer saId);

    int updateByPrimaryKey(StudentAnswer record);

    StudentAnswer selectByTaskAndStudentId(Integer taskId, Integer studentId);
}