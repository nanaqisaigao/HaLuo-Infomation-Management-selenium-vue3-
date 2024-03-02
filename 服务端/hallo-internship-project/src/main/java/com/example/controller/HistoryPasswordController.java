package com.example.controller;

import com.example.common.Result;
import com.example.pojo.entity.PwdParams;
import com.example.service.CheckPwdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HistoryPasswordController {
    @Autowired
    CheckPwdService checkPwdService;
    @PutMapping("/student/changepsw")
    public Result updatePwd(@RequestBody PwdParams pwdParams){
        if(!checkPwdService.checkpwd1(pwdParams.getPassword()))
            return Result.error("修改密码不符合要求");
        String message=checkPwdService.checkpwd2(pwdParams);
        if(message.equals("密码修改成功"))
            return Result.success();
        else return Result.error(message);
    }
}
