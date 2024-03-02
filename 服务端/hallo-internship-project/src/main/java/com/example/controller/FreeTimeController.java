package com.example.controller;

import cn.hutool.json.JSONObject;
import com.example.common.Result;
import com.example.pojo.entity.FreeTime;
import com.example.pojo.entity.TFreetimes;
import com.example.service.FreeTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FreeTimeController {
    @Autowired
    private FreeTimeService freeTimeService;

    @GetMapping("/student/freetime")
    public Result getOne(@RequestParam(value = "userId",required = false) int userId,
                         @RequestParam(value = "index",required = false) int index){
        int[][] list = freeTimeService.getOne(userId,index);
        return Result.success(list);
    }

    @PostMapping("/student/changefreetime")
    public Result changeTime(@RequestBody(required = false) JSONObject jsonObject){
        int userId = jsonObject.getInt("userId");
        int index = jsonObject.getInt("index");
        int day = jsonObject.getInt("day");
        int time = jsonObject.getInt("time");
        int status = jsonObject.getInt("status");
        freeTimeService.changeTime(userId,index,day,time,status);
        return Result.success();
    }

    @GetMapping("/teacher/freetimes")
    public Result getAll(@RequestParam(value = "index",required = false) int index){
        TFreetimes tFreetimes = freeTimeService.getAll(index);
        return Result.success(tFreetimes);
    }
}
