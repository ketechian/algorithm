package com.example.algorithm.mapper;

import com.example.algorithm.domain.po.Class;
import com.example.algorithm.domain.vo.ClassVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassMapper {
    int deleteByPrimaryKey(Integer clId);

    int insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(Integer clId);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);

    Class selectByName(String className);

    List<ClassVo> selectByClassId(@Param("classIdList") List<Integer> classIdList);
}