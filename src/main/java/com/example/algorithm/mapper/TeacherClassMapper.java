package com.example.algorithm.mapper;

import com.example.algorithm.domain.po.Class;
import com.example.algorithm.domain.po.TeacherClass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherClassMapper {
    int deleteByPrimaryKey(Integer tcId);

    int insert(TeacherClass record);

    int insertSelective(TeacherClass record);

    TeacherClass selectByPrimaryKey(Integer tcId);

    int updateByPrimaryKeySelective(TeacherClass record);

    int updateByPrimaryKey(TeacherClass record);

    List<Integer> selectClassByTeacherId(Integer teacherId);

    TeacherClass isTeacherInClass(Integer teacherId, Integer classId);
}