package com.example.service;


import com.example.pojo.dto.UserRegisterDTO;
import com.example.pojo.entity.User;
import com.example.pojo.dto.UserLoginDTO;
import com.example.pojo.vo.UserLoginVO;

public interface UserService {

    User login(UserLoginDTO userLoginDTO);

    User getById(Integer id);

    void register(UserRegisterDTO userRegisterDTO);
}
