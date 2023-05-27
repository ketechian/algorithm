package com.example.algorithm.controller;

import com.example.algorithm.domain.dto.LoginAdminDto;
import com.example.algorithm.domain.dto.LoginStudentDto;
import com.example.algorithm.domain.dto.LoginTeacherDto;
import com.example.algorithm.domain.dto.UpdatePasswordDto;
import com.example.algorithm.service.LoginService;
import com.example.algorithm.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/14 15:10
 */

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    //学生登录
    @PostMapping("/student")
    public Result loginStudent(@RequestBody LoginStudentDto loginStudentDto,
                               @RequestHeader(value = "token",required = false) String token) {

        Result result = loginService.studentLogin(loginStudentDto.getPhone(),
                loginStudentDto.getPassword(), token, "/login/student");
        return  result;
    }

    //老师登录
    @PostMapping("/teacher")
    public Result loginTeacher(@RequestBody LoginTeacherDto loginTeacherDto,
                               @RequestHeader(value = "token",required = false) String token) {

        Result result = loginService.teacherLogin(loginTeacherDto.getPhone(),
                loginTeacherDto.getPassword(), token, "/login/teacher");

        return result;
    }

    /**
     * 修改密码
     * @param updatePasswordDto
     * @return
     */
    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestBody UpdatePasswordDto updatePasswordDto) {
        return loginService.updatePassword(
                updatePasswordDto.getStId(),
                updatePasswordDto.getPassword(),
                updatePasswordDto.getNowPassword(),
                "login/updatePassword");
    }
}
