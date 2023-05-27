package com.example.algorithm.service.impl;

import com.example.algorithm.domain.po.PermissionGroup;
import com.example.algorithm.domain.po.Student;
import com.example.algorithm.domain.po.Teacher;
import com.example.algorithm.mapper.AdminMapper;
import com.example.algorithm.mapper.PermissionGroupMapper;
import com.example.algorithm.mapper.StudentMapper;
import com.example.algorithm.mapper.TeacherMapper;
import com.example.algorithm.service.LogonService;
import com.example.algorithm.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/14 00:34
 */

@Service
public class LogonServiceImpl implements LogonService {

    @Autowired
    private PermissionGroupMapper permissionGroupMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Result userRegister(String name, String phone, Integer roleGroupId, String password,
                               String rwPassword, String mailbox, String path) {

        Map<String, Object> data = new HashMap<>();
        Map<String, Object> message = new HashMap<>();
        //初始默认返回码
        Integer registerCode = 1;

        //前后密码不一致
        if (!(password.equals(rwPassword))) {
            data.put("password", "前后密码不一致");
            registerCode = -1;
            message.put("register_code", registerCode);
            message.put("data", data);
            return new Result().result200(message, path);
        }

        PermissionGroup permissionGroup = permissionGroupMapper.selectByPrimaryKey(roleGroupId);
        String roleName = permissionGroup.getPrName();

        switch (roleName) {
            //学生注册
            case "student":
                try {
                    studentMapper.insert(new Student(name, phone, password, mailbox, permissionGroup.getPrId()));
                    data.put("result", "学生注册成功");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            //老师注册
            case "teacher":
                try {
                    teacherMapper.insert(new Teacher(name, phone, password, mailbox, permissionGroup.getPrId()));
                    data.put("result", "老师注册成功");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            //管理员注册
            case "admin":
                System.out.println("暂无");
                break;
            default:
                data.put("result", "权限错误,注册失败");
                break;
        }

        message.put("register_code", registerCode);
        message.put("data", data);
        return new Result().result200(message, path);
    }
}
