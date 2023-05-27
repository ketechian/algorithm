package com.example.algorithm.mapper;

import com.example.algorithm.domain.po.Hyperlinks;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface HyperlinksMapper {
    int deleteByPrimaryKey(Integer hlId);

    int insert(Hyperlinks record);

    int insertSelective(Hyperlinks record);

    Hyperlinks selectByPrimaryKey(Integer hlId);

    int updateByPrimaryKeySelective(Hyperlinks record);

    int updateByPrimaryKey(Hyperlinks record);

    Hyperlinks selectBytText(String topicHyperlink);
}