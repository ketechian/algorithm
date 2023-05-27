package com.example.algorithm.mapper;

import com.example.algorithm.domain.bo.InputStudentBo;
import com.example.algorithm.domain.po.Student;
import com.example.algorithm.domain.vo.ClassVo;
import com.example.algorithm.domain.vo.StudentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {
    int deleteByPrimaryKey(Integer stId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer stId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    Student studentLogin(String stPhone, String stPassword);

    List<Integer> selectAll(Integer isDeleteNum);

    List<Student> selectById(@Param("studentId") List<Integer> studentId, Integer start, Integer count);

    StudentVo selectByPathNameId(Integer studentId);

}