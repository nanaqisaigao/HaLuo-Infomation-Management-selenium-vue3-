package com.example.controller;

import com.example.common.Result;
import com.example.pojo.entity.CheckClock;
import com.example.pojo.entity.Params;
import com.example.service.CheckTimeService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class CheckTimeController {

    @Autowired
    CheckTimeService checkTimeService;
    @PostMapping("/student/sign")
    public Result save(@RequestBody CheckClock checkClock){
        checkTimeService.save(checkClock);
        return Result.success();
    }
    @GetMapping("/student/search")
    public Result findBySearchS(Params params){
        //log.info("token验证成功，开始调用接口");
        PageInfo<CheckClock> infos = checkTimeService.findBySearch(params);
        return Result.success(infos);
    }
    @GetMapping("/teacher/search")
    public Result findBySearchT(Params params){
        //log.info("token验证成功，开始调用接口");
        PageInfo<CheckClock> infos = checkTimeService.findBySearch(params);
        return Result.success(infos);
    }
    @GetMapping("teacher/sort")
    public Result sortTableData() {
        // 在这里对tableData进行排序处理
        PageInfo<CheckClock> infos = checkTimeService.sortByTime();
        // 返回排序后的数据
        return Result.success(infos);
    }

}
