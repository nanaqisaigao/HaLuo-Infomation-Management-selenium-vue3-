package com.example.service.impl;


import com.example.exception.CustomException;
import com.example.mapper.StudentMapper;
import com.example.pojo.dto.UserRegisterDTO;
import com.example.pojo.entity.Student;
import com.example.pojo.entity.User;
import com.example.mapper.UserMapper;
import com.example.pojo.dto.UserLoginDTO;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 密码规则
     * 密码仅能包含1 英文大小写、2 数字、3 特殊字符@_%
     * 一个符合要求的密码至少满足上述3种至少2种且字符长度要大于等于8
     * 其他规则： 修改的新密码不能和最近修改的3个历史密码重复
     */
    private static final String PASSWORD_REGEX = "^(?:(?=.*[a-z])(?=.*[A-Z])|(?=.*[a-z])(?=.*\\d)|(?=.*[A-Z])(?=.*\\d)|(?=.*[a-z])(?=.*[@#$%^&+=!])|(?=.*[A-Z])(?=.*[@#$%^&+=!])|(?=.*\\d)(?=.*[@#$%^&+=!]))[a-zA-Z0-9@#$%^&+=!]{8,}$";

    private static final String PHONE_REGEX = "^1[3-9]\\d{9}$";

    @Override
    public User login(UserLoginDTO userLoginDTO) {
        String phone = userLoginDTO.getPhone();
        String password = userLoginDTO.getPassword();

        User user = userMapper.getByPhone(phone);

        if(user == null){
            throw new CustomException("用户不存在");
        }

        password = DigestUtils.md5DigestAsHex(password.getBytes());

        if(!password.equals(user.getPassword())){
            //密码错误
            throw new CustomException("账号或密码错误");
        }
        return user;
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        String phone = userRegisterDTO.getPhone();
        String password = userRegisterDTO.getPassword();
        String username = userRegisterDTO.getUsername();

        int count = userMapper.existUser(phone);
        if(count == 1){
            log.info("用户已存在，请使用未注册过的phone注册");
            throw new CustomException("用户已存在，请使用未注册过的phone注册");
        }
        Pattern phonePattern = Pattern.compile(PHONE_REGEX);
        Matcher phoneMatcher = phonePattern.matcher(phone);
        if (!phoneMatcher.matches()) {
            log.info("手机号格式错误");
            throw new CustomException("手机号格式错误");
        }

        Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if (!passwordMatcher.matches()) {
            log.info("密码格式错误");
            throw new CustomException("密码格式错误");
        }

        password = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = User.builder()
                .phone(phone)
                .password(password)
                .level("Student")
                .createTime(LocalDateTime.now())
                .updateTime(Timestamp.valueOf(LocalDateTime.now()))
                .deleted(0)
                .build();
        log.info("插入用户user:{}",user);
        userMapper.insert(user);

        int userId = user.getId();
        Student student =Student.builder()
                .userId(userId)
                .username(username)
                .createTime(LocalDateTime.now())
                .updateTime(Timestamp.valueOf(LocalDateTime.now()))
                .deleted(0)
                .build();
        log.info("插入学生student:{}",student);
        studentMapper.insert(student);

    }

}
