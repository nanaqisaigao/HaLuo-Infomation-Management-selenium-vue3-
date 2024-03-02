package com.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.example.mapper.CheckTimeMapper;
import com.example.mapper.StudentMapper;
import com.example.pojo.entity.*;
import com.example.service.CheckTimeService;
import com.example.utils.ThreadLocalUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class CheckTimeServiceImpl implements CheckTimeService {
    @Autowired
    private CheckTimeMapper checktimeMapper;
    @Autowired
    private StudentMapper studentMapper;
    private List<CheckClock> list;
    @Override
    public void save(CheckClock CheckClock) {
        CheckClock checkClock=checktimeMapper.select(CheckClock);
        if(checkClock==null)
            checktimeMapper.insert(CheckClock);
        else {
            Float vt=checkClock.getValidTime();
            String ct=checkClock.getCheckTime();
            CheckClock.setValidTime(vt+CheckClock.getValidTime());
            CheckClock.setCheckTime(ct+","+CheckClock.getCheckTime());
            checktimeMapper.update(CheckClock);
        }
    }

    @Override
    public PageInfo<CheckClock> findBySearch(Params params){
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        User userCurrent = ThreadLocalUtils.getCurrentUser();
        String level = userCurrent.getLevel();
        if("Student".equals(level)){
//            Student student = studentMapper.findByUserId(userCurrent.getId());
//            params.setUserid(student.getId());
            params.setUserid(userCurrent.getId());
        }
        list = checktimeMapper.findBySearch(params);
        if(ObjectUtil.isEmpty(list)){
            return PageInfo.of(new ArrayList<>());
        }
        for(CheckClock checkClock : list) {
            if (ObjectUtil.isNotEmpty(checkClock.getUserId())) {
                Student student = studentMapper.findByUserId(checkClock.getUserId());
                if (ObjectUtil.isNotEmpty(student)) {
                    checkClock.setUserName(student.getUsername());
                }
            }
        }
        return PageInfo.of(list);
    }

    @Override
    public PageInfo<CheckClock> sortByTime() {
        Collections.reverse(list);
        return new PageInfo<CheckClock>(list);
    }


}
