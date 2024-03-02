package com.example.service;

import com.example.pojo.dto.WeekReportDTO;
import com.example.pojo.entity.Params;
import com.example.pojo.entity.WeekReport;
import com.github.pagehelper.PageInfo;

public interface WeekReportService {
    void save(WeekReportDTO weekReportDTO);

    void update(WeekReport weekReport);


    PageInfo<WeekReport> findBySearch(Params params);
}
