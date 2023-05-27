package com.example.algorithm.service.impl;

import com.example.algorithm.domain.po.Admin;
import com.example.algorithm.domain.po.PermissionGroup;
import com.example.algorithm.domain.po.Student;
import com.example.algorithm.domain.po.Teacher;
import com.example.algorithm.mapper.AdminMapper;
import com.example.algorithm.mapper.PermissionGroupMapper;
import com.example.algorithm.mapper.StudentMapper;
import com.example.algorithm.mapper.TeacherMapper;
import com.example.algorithm.service.LoginService;
import com.example.algorithm.utils.JWTUtil;
import com.example.algorithm.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/14 00:35
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private PermissionGroupMapper permissionGroupMapper;

    /**
     * 学生登录
     *
     * @param stPhone 电话号码
     * @param stPassword 密码
     * @param token token
     * @param path 请求路径
     * @return Result
     */
    @Override
    public Result studentLogin(String stPhone, String stPassword, String token, String path) {
        Map<String, Object> resultMap = new Hashtable<>();
        Map<String, String> tokenMap = new Hashtable<>();

        Student student = null;
        try {
            student = studentMapper.studentLogin(stPhone, stPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(student != null) {
            PermissionGroup permissionGroup = permissionGroupMapper.selectByPrimaryKey(student.getStRoleId());
            tokenMap.put("id", String.valueOf(student.getStId()));
            tokenMap.put("role", permissionGroup.getPrName());
            Result result = new LoginServiceImpl().loginJudge(student, resultMap, tokenMap, path, token);
            return  result;
        } else {
            //账号或密码错误
            resultMap.put("login_code", 0);
            resultMap.put("data", "账号或密码错误");
            return new Result().result200(resultMap, path);
        }
    }

    /**
     * 老师登录
     *
     * @param tPhone 老师电话
     * @param tPassword 老师密码
     * @param token
     * @param path
     * @return
     */
    @Override
    public Result teacherLogin(String tPhone, String tPassword, String token, String path) {
        Map<String, Object> resultMap = new Hashtable<>();
        Map<String, String> tokenMap = new Hashtable<>();

        Teacher teacher = null;
        try {
            teacher = teacherMapper.teacherLogin(tPhone, tPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(teacher != null) {
            PermissionGroup permissionGroup = permissionGroupMapper.selectByPrimaryKey(teacher.getTRoleId());
            tokenMap.put("id", String.valueOf(teacher.getTId()));
            tokenMap.put("role", permissionGroup.getPrName());
            Result result = new LoginServiceImpl().loginJudge(teacher, resultMap, tokenMap, path, token);
            return  result;
        } else {
            //账号或密码错误
            resultMap.put("login_code", 0);
            resultMap.put("data", "账号或密码错误");
            return new Result().result200(resultMap, path);
        }
    }

    @Override
    public Result adminLogin(String adNumber, String adPassword, String token, String path) {
        Map<String, Object> resultMap = new Hashtable<>();
        Map<String, String> tokenMap = new Hashtable<>();

        Admin admin = null;
        try {
            admin = adminMapper.adminLogin(adNumber, adPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(admin != null) {
            PermissionGroup permissionGroup = permissionGroupMapper.selectByPrimaryKey(admin.getAdRoleId());
            tokenMap.put("id", String.valueOf(admin.getAdId()));
            tokenMap.put("role", permissionGroup.getPrName());
            Result result = new LoginServiceImpl().loginJudge(admin, resultMap, tokenMap, path, token);
            return  result;
        } else {
            //账号或密码错误
            resultMap.put("login_code", 0);
            resultMap.put("data", "账号或密码错误");
            return new Result().result200(resultMap, path);
        }
    }

    @Override
    public Result updatePassword(Integer stId, String password, String nowPassword, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            Student student = studentMapper.selectByPrimaryKey(stId);
            String stPassword = student.getStPassword();
            if (stPassword.equals(password)) {
                student.setStPassword(nowPassword);

                studentMapper.updateByPrimaryKey(student);
                createCode = 1;
                data.put("createCode", createCode);
                data.put("result", "修改成功");
            } else {
                data.put("createCode", createCode);
                data.put("result", "密码成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.put("createCode", createCode);
            data.put("result", "修改失败");
        } finally {
            message.put("register_code", createCode);
            message.put("data", data);
        }
        return new Result().result200(message, path);
    }

    /**
     * @param msg       传入对应的vo
     * @param resultMap result的数据
     * @param tokenMap  token中的值
     * @param path      请求路径
     * @param token     token
     * @return
     */
    public Result loginJudge(Object msg, Map<String, Object> resultMap, Map<String, String> tokenMap,
                             String path, String token) {
        if ("".equals(token) || token == null) {
            //登陆成功,生成token
            token = JWTUtil.createToken(tokenMap);
            //返回
            resultMap.put("login_code", 1);
            resultMap.put("token", token);
            resultMap.put("data", msg);
            return new Result().result200(resultMap, path);
        } else {
            //不是新登录,验证token
            int statusCode = JWTUtil.verify(token);
            if (statusCode == 1) {
                //token未过期,登录成功
                //刷新token
                token = JWTUtil.createToken(tokenMap);
                //返回
                resultMap.put("login_code", 1);
                resultMap.put("token", token);
                resultMap.put("data", msg);
                return new Result().result200(resultMap, path);
            } else {
                //token失效或错误
                resultMap.put("login_code", 0);
                //清空token
                token = "";
                resultMap.put("token", token);
                return new Result().result401(resultMap, path);
            }
        }
    }
}
