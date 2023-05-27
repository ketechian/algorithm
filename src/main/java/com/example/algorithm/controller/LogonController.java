package com.example.algorithm.controller;

import com.example.algorithm.domain.dto.UserRegisterDto;
import com.example.algorithm.service.LogonService;
import com.example.algorithm.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/14 00:48
 */

@RestController
@RequestMapping("/logon")
public class LogonController {

    @Autowired
    private LogonService logonService;

    @PostMapping("/userRegister")
    public Result userRegister(@RequestBody UserRegisterDto userRegisterDto) {
        return  logonService.userRegister(
                userRegisterDto.getUsername(),
                userRegisterDto.getPhone(),
                userRegisterDto.getRoleGroupId(),
                userRegisterDto.getPassword(),
                userRegisterDto.getRwPassword(),
                userRegisterDto.getMailbox(),
                "/logon/userRegister"
        );
    }


}
