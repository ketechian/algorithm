package com.example.algorithm.mapper;

import com.example.algorithm.domain.po.Task;
import com.example.algorithm.domain.vo.TaskAllVo;
import org.apache.catalina.util.NetMask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskMapper {

    int deleteByPrimaryKey(Integer tkId);

    int insert(Task record);

    Task selectByPrimaryKey(Integer tkId);


    int updateByPrimaryKey(Task record);

    int insertGroup(Integer fatherId, @Param("studentList") List<Integer> studentList, Integer topicId);

    List<TaskAllVo> selectByFromId(Integer studentId, Integer start, Integer count);

    int checkTask(Integer taskId);

    int updateOver(Integer taskId);

}