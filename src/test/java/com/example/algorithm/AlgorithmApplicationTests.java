package com.example.algorithm;

import com.example.algorithm.domain.po.PermissionGroup;
import com.example.algorithm.domain.po.Student;
import com.example.algorithm.mapper.PathMapper;
import com.example.algorithm.mapper.PermissionGroupMapper;
import com.example.algorithm.mapper.StudentMapper;
import com.example.algorithm.utils.oss.OssFileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.util.DateUtil.now;


@SpringBootTest
class AlgorithmApplicationTests {

    @Autowired
    private PermissionGroupMapper permissionGroupMapper;

    @Autowired
    private PathMapper pathMapper;

    @Autowired
    OssFileUtil ossFileUtil;

    @Autowired
    private StudentMapper studentMapper;

    @Test
    void addPermissionGroup() {
        permissionGroupMapper.insert(new PermissionGroup("admin", "", 1 , now(), now()));
    }

    @Test
    void addStudent() {
     //   studentMapper.insert(new Student("name", "12345678910", "123456", "mailbox", 1));
    }

    @Test
    void fullPath() {
        System.out.println(pathMapper.selectByPhName("class"));
    }
}
