package com.example.algorithm.mapper;

import com.example.algorithm.domain.po.InformationGroup;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InformationGroupMapper {
    int deleteByPrimaryKey(Integer igId);

    int insert(InformationGroup record);

    int insertSelective(InformationGroup record);

    InformationGroup selectByPrimaryKey(Integer igId);

    int updateByPrimaryKeySelective(InformationGroup record);

    int updateByPrimaryKey(InformationGroup record);
}