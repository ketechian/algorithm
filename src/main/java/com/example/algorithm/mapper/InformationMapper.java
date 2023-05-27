package com.example.algorithm.mapper;

import com.example.algorithm.domain.po.Information;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InformationMapper {
    int deleteByPrimaryKey(Integer imId);

    int insert(Information record);

    int insertGroup(@Param("fromId")List<Integer> fromId, Integer fatherId);

    Information selectByPrimaryKey(Integer imId);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKey(Information record);

    List<Information> selectByFromId(Integer Id, Integer start, Integer count);

    Integer countById(Integer teacherId, Integer roleId);

    int checkByPrimaryKey(Integer messageId);
}