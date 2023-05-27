package com.example.algorithm.mapper;

import com.example.algorithm.domain.po.PermissionGroup;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionGroupMapper {
    int deleteByPrimaryKey(Integer prId);

    int insert(PermissionGroup record);

    int insertSelective(PermissionGroup record);

    PermissionGroup selectByPrimaryKey(Integer prId);

    int updateByPrimaryKeySelective(PermissionGroup record);

    int updateByPrimaryKey(PermissionGroup record);
}