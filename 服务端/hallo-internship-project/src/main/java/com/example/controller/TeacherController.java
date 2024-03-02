package com.example.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.exception.CustomException;
import com.example.mapper.CollegeMapper;
import com.example.mapper.MajorMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.entity.*;
import com.example.service.StudentService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@RestController
public class TeacherController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CollegeMapper collegeMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MajorMapper majorMapper;
    @GetMapping("teacher/stu/search")
    public Result findBySearch(Params params){
//        log.info("token验证成功，开始调用接口");
        PageInfo<Student> infos = studentService.findBySearch(params);
        return Result.success(infos);
    }

    @GetMapping("teacher/stu/exp")
    public Result export(HttpServletResponse response) throws IOException {
        log.info("执行...");
        //一行一行的写数据
        //每一行数据，就对应数据库的一条数据，即Type
        //需要一个map<key,value>映射数据,将map放入list
        //1、从数据库中查询所有数据
        List<Student> student_all = studentService.findAll();
        if(ObjectUtil.isEmpty(student_all)){
            throw new CustomException("下载失败,未找到数据");
        }
        for(Student student : student_all){
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
        //2、定义list和map处理数据
        List<Map<String, Object>> list = new ArrayList<>(student_all.size());
        //3、遍历封装map将数据放入list
        for(Student student : student_all){
            Map<String, Object> row = new HashMap<>();

            row.put("姓名",student.getUsername());
            row.put("手机号",student.getPhone());
            row.put("年级",student.getGrade());
            row.put("学历",student.getEducation());
            row.put("专业",student.getMajorName());
            row.put("学院",student.getCollegeName());
            list.add(row);
        }
        //4、创建一个excelwriter，吧list用write写出来
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list,true);
        //5、下载excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=student.xlsx");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);

        return Result.success();

    }
    @GetMapping("teacher/stu/exppart")
    public Result exppart(HttpServletResponse response) throws IOException {
        //一行一行的写数据
        //每一行数据，就对应数据库的一条数据，即Type
        //需要一个map<key,value>映射数据,将map放入list
        //1、从数据库中查询所有数据
        List<Student> student_all = studentService.getList();
        if(ObjectUtil.isEmpty(student_all)){
            throw new CustomException("下载失败,未找到数据");
        }
        for(Student student : student_all){
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
        //2、定义list和map处理数据
        List<Map<String, Object>> list = new ArrayList<>(student_all.size());
        //3、遍历封装map将数据放入list
        for(Student student : student_all){
            Map<String, Object> row = new HashMap<>();

            row.put("姓名",student.getUsername());
            row.put("手机号",student.getPhone());
            row.put("年级",student.getGrade());
            row.put("学历",student.getEducation());
            row.put("专业",student.getMajorName());
            row.put("学院",student.getCollegeName());
            list.add(row);
        }
        //4、创建一个excelwriter，吧list用write写出来
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list,true);
        //5、下载excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=student.xlsx");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);

        return Result.success();

    }
}
