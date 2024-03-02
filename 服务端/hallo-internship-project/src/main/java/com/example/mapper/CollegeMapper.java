package com.example.mapper;

import com.example.pojo.entity.College;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollegeMapper {
    @Select("select id from college where college_name=#{name}")
    Integer getId(String name);
    @Select("select college_name from college where deleted=0 ")
    List<String> getall();

    @Select("select * from college where id = #{collegeId}")
    College selectByPrimaryKey(Integer collegeId);
}
