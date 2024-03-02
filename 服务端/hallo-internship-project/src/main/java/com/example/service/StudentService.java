package com.example.service;

import com.example.pojo.entity.Params;
import com.example.pojo.entity.Student;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface StudentService {

    String getName(Integer id);
    public Student select(Integer id);
    public void update(Student student);

    PageInfo<Student> findBySearch(Params params);

    List<Student> findAll();

    List<Student> getList();
}
