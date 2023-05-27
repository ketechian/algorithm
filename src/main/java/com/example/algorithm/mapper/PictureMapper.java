package com.example.algorithm.mapper;

import com.example.algorithm.domain.po.Picture;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PictureMapper {
    int deleteByPrimaryKey(Integer ptId);

    int insert(Picture record);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(Integer ptId);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);

    Picture selectByName(String uuid);
}