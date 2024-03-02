package com.example.service;

import com.example.pojo.entity.FreeTime;
import com.example.pojo.entity.TFreetimes;

import java.util.List;

public interface FreeTimeService {
    int[][] getOne(int userId, int index);
    void changeTime(int userId,int index,int day,int time,int status);
    TFreetimes getAll(int index);
}
