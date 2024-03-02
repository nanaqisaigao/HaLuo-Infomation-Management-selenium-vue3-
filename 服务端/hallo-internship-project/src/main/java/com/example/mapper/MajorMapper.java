package com.example.mapper;

import com.example.pojo.entity.Major;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MajorMapper {
    @Select("select id from major where major_name=#{name}")
    Integer getId(String name);
    @Select("select major_name from major left join college on college.id=major.college_id " +
            "where college.college_name = #{name} and major.deleted=0")
    List<String> getname(String name);

    @Select("select * from major where id = #{majorId}")
    Major selectByPrimaryKey(Integer majorId);
}
