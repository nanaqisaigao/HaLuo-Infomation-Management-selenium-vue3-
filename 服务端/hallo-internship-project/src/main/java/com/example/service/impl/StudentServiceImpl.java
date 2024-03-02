package com.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.example.mapper.CollegeMapper;
import com.example.mapper.MajorMapper;
import com.example.mapper.StudentMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.entity.*;
import com.example.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private CollegeMapper collegeMapper;
    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public String getName(Integer id) {
        return studentMapper.getName(id);
    }
    @Override
    public Student select(Integer id) {
        return studentMapper.findByUserId2(id);
    }
    @Override
    public void update(Student student) {
        Integer collegeid=collegeMapper.getId(student.getCollegeName());
        Integer majorid=majorMapper.getId(student.getMajorName());
        student.setCollegeId(collegeid);
        student.setMajorId(majorid);
        studentMapper.update(student);
    }

    private List<Student> list;
    @Override
    public PageInfo<Student> findBySearch(Params params) {
        //处理分页
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        //接下来的查询会自动开启分页查询
        list =  studentMapper.findBySearch(params);
        if(ObjectUtil.isEmpty(list)){
            return PageInfo.of(new ArrayList<>());
        }
        for(Student student : list){
            if(ObjectUtil.isNotEmpty(student.getUserId())) {
                User user = userMapper.selectByPrimaryKey(student.getUserId());
                if(ObjectUtil.isNotEmpty(user)){
                    student.setPhone(user.getPhone());
                }
                College college = collegeMapper.selectByPrimaryKey(student.getCollegeId());
                if(ObjectUtil.isNotEmpty(college)){
                    student.setCollegeName(college.getCollegeName());
                }
                Major major = majorMapper.selectByPrimaryKey(student.getMajorId());
                if(ObjectUtil.isNotEmpty(major)){
                    student.setMajorName(major.getMajorName());
                }
            }
        }
        return PageInfo.of(list);
    }

    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    @Override
    public List<Student> getList() {
        return list;
    }


}
