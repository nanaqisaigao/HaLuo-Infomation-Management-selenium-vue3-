package com.example.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TFree {
    private  int id;
    private String time;
    private int Mon;
    private int Tues;
    private int Wednes;
    private int Thurs;
    private int Fri;
}
