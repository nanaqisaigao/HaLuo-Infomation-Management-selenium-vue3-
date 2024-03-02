package com.example.controller;


import com.example.common.Result;
import com.example.pojo.dto.UserRegisterDTO;
import com.example.pojo.entity.User;
import com.example.pojo.dto.UserLoginDTO;
import com.example.pojo.vo.UserLoginVO;
import com.example.service.StudentService;
import com.example.service.UserService;
import com.example.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户登录，{}", userLoginDTO);
        User user = userService.login(userLoginDTO);

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("phone", user.getPhone());
        String token = JwtUtils.createJWT(claims);

        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setId(user.getId());
        userLoginVO.setLevel(user.getLevel());
        if (user.getLevel().equals("Teacher")) {
            userLoginVO.setUsername("admin");
        } else {
            userLoginVO.setUsername(studentService.getName(user.getId()));
        }
        userLoginVO.setToken(token);

        return Result.success(userLoginVO);
    }
    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterDTO userRegisterDTO){
        log.info("用户注册：{}",userRegisterDTO);
        userService.register(userRegisterDTO);
        return Result.success();
    }

}
