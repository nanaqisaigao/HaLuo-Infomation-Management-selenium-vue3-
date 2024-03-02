package com.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.example.mapper.StudentMapper;
import com.example.pojo.dto.WeekReportDTO;
import com.example.pojo.entity.*;
import com.example.mapper.WeekReportMapper;
import com.example.service.WeekReportService;
import com.example.utils.ThreadLocalUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class WeekReportServiceImpl implements WeekReportService {

    @Autowired
    private WeekReportMapper weekReportMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public void save(WeekReportDTO weekReportDTO) {
        WeekReport weekReport = new WeekReport();
        BeanUtils.copyProperties(weekReportDTO,weekReport);
        Integer studentId = studentMapper.getStudentId(weekReportDTO.getUserId());
        weekReport.setStudentId(studentId);
        log.info("weekReportDTO:{}",weekReportDTO);
        log.info("weekReport:{}",weekReport);

        weekReport.setCreateTime(LocalDateTime.now());
        weekReport.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        weekReport.setDeleted(0);
        weekReportMapper.insert(weekReport);
    }

    @Override
    public void update(WeekReport weekReport) {
        weekReport.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        weekReportMapper.update(weekReport);
    }
    @Override
    public PageInfo<WeekReport> findBySearch(Params params){
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        User userCurrent = ThreadLocalUtils.getCurrentUser();
        String level = userCurrent.getLevel();
        if("Student".equals(level)){
            Student student = studentMapper.findByUserId(userCurrent.getId());
            params.setUserid(student.getId());
        }
        List<WeekReport> list = weekReportMapper.findBySearch(params);
        if(ObjectUtil.isEmpty(list)){
            return PageInfo.of(new ArrayList<>());
        }
        for(WeekReport weekReport : list) {
            if (ObjectUtil.isNotEmpty(weekReport.getStudentId())) {
                Student student = studentMapper.findById(weekReport.getStudentId());
                if (ObjectUtil.isNotEmpty(student)) {
                    weekReport.setUserName(student.getUsername());
                }
            }
        }
        return PageInfo.of(list);
    }
}
