package com.example.pojo.entity;


import lombok.Data;

import javax.persistence.Transient;

@Data
public class JwtToken {
    @Transient
    private String token;

}
