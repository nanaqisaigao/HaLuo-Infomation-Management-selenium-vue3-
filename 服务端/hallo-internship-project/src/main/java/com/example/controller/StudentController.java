package com.example.controller;

import com.example.common.Result;
import com.example.mapper.CollegeMapper;
import com.example.mapper.MajorMapper;
import com.example.pojo.entity.Params;
import com.example.pojo.entity.Student;
import com.example.pojo.entity.WeekReport;
import com.example.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {
@Autowired
   private StudentService studentService;
@Autowired
   private CollegeMapper collegeMapper;
@Autowired
   private MajorMapper majorMapper;
@GetMapping("/getmes/{user_id}")
   public Result getbyuserId(@PathVariable Integer user_id){
   Student info=studentService.select(user_id);
   return  Result.success(info);
}
@GetMapping("/getcollege")
   public Result getcollege(){
   List<String> info=collegeMapper.getall();
   return  Result.success(info);
}
@GetMapping("/getmajor/{name}")
   public Result getbyuserId(@PathVariable String name){
   List<String> info=majorMapper.getname(name);

   return  Result.success(info);
}
@PutMapping("/changemes")
   public Result update(@RequestBody Student student){
   studentService.update(student);
   return Result.success();
}

}
