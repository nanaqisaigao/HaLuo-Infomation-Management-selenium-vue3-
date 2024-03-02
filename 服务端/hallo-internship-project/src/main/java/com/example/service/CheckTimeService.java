package com.example.service;

import com.example.pojo.entity.CheckClock;
import com.example.pojo.entity.Params;
import com.example.pojo.entity.WeekReport;
import com.github.pagehelper.PageInfo;

public interface CheckTimeService {
    void save(CheckClock CheckClock);


    PageInfo<CheckClock> findBySearch(Params params);

    PageInfo<CheckClock> sortByTime();
}
