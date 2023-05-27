package com.example.algorithm.mapper;

import com.example.algorithm.domain.po.Information;
import com.example.algorithm.domain.po.Student;
import com.example.algorithm.domain.po.StudentClass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentClassMapper {
    int deleteByPrimaryKey(Integer scId);

    int insert(StudentClass record);

    int insertSelective(StudentClass record);

    StudentClass selectByPrimaryKey(Integer scId);

    int updateByPrimaryKeySelective(StudentClass record);

    int updateByPrimaryKey(StudentClass record);

    List<Integer> selectByClass(Integer informationFromClass);

    int countByClassId(Integer classId);

    List<Integer> selectStudentId(Integer classId);

    List<Integer> selectClassByStudentId(Integer studentId);
}