package com.example.controller;


import com.example.common.Result;
import com.example.pojo.*;
import com.example.pojo.dto.WeekReportDTO;
import com.example.pojo.entity.Params;
import com.example.pojo.entity.WeekReport;
import com.example.service.WeekReportService;
import com.example.service.impl.WeekReportServiceImpl;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Slf4j
@RestController
public class WeekReportController {

    @Autowired
    private WeekReportService weekReportService;
    @InitBinder
    public void InitBinder(WebDataBinder dataBinder)
    {
        dataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String value) {
                try {
                    setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
                } catch(ParseException e) {
                    setValue(null);
                }
            }
            @Override
            public String getAsText() {
                return new SimpleDateFormat("yyyy-MM-dd").format((Date) getValue());
            }
        });
    }

    @PostMapping("/student/addnotice")
    public Result save(@RequestBody WeekReportDTO weekReportDTO){
        log.info("学生新增周报，weekReport:{}",weekReportDTO);
        weekReportService.save(weekReportDTO);
        return Result.success();
    }

    @PutMapping("/student/changenotice")
    public Result updateStudent(@RequestBody WeekReport weekReport){
        log.info("学生修改周报，weekReport:{}",weekReport);
        weekReportService.update(weekReport);
        return Result.success();
    }

    @PutMapping("/teacher/changenotice")
    public Result updateTeacher(@RequestBody WeekReport weekReport){
        log.info("老师修改周报，weekReport:{}",weekReport);
        weekReportService.update(weekReport);
        return Result.success();
    }

    @GetMapping("/teacher/tnotices")
    public Result findBySearchT(Params params){
        log.info("token通过,调用查询接口");
        PageInfo<WeekReport> infos = weekReportService.findBySearch(params);
        return Result.success(infos);
    }
    @GetMapping("/student/tnotices")
    public Result findBySearchS(Params params){
        log.info("token通过,调用查询接口");
        PageInfo<WeekReport> infos = weekReportService.findBySearch(params);
        return Result.success(infos);
    }


}
