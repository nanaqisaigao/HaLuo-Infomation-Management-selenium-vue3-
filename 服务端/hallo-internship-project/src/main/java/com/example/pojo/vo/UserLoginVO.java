package com.example.pojo.vo;

import lombok.Data;

@Data
public class UserLoginVO {
    private String token;
    private Integer id;
    private String level;
    private String username;
}
