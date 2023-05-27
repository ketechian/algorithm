package com.example.algorithm.mapper;

import com.example.algorithm.domain.po.Class;
import com.example.algorithm.domain.po.Teacher;
import com.example.algorithm.domain.vo.TeacherVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapper {
    int deleteByPrimaryKey(Integer tId);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer tId);

    TeacherVo selectByPathNameId(Integer tId);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    Teacher teacherLogin(String tPhone, String tPassword);

    List<Integer> selectAll(Integer isDeleteNum);

}