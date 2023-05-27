package com.example.algorithm.mapper;

import com.example.algorithm.domain.po.Path;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PathMapper {
    int deleteByPrimaryKey(Integer phId);

    int insert(Path record);

    int insertSelective(Path record);

    Path selectByPrimaryKey(Integer phId);

    int updateByPrimaryKeySelective(Path record);

    int updateByPrimaryKey(Path record);

    Path selectByPhName(String phPath);
}