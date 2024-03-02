package com.example.mapper;


import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface FreeTimeMapper {
    @Select("select free_time from freetime where student_id=#{userId} AND deleted != 1")
    List<String> getOne(@Param("userId")int userId);

    @Insert("insert into freetime(student_id,free_time,create_time,update_time,deleted) values( #{userId}, #{freeTime,jdbcType=TIMESTAMP},#{now,jdbcType=TIMESTAMP},#{update,jdbcType=TIMESTAMP},#{deleted} )")
    void add(@Param("userId")int userId, @Param("freeTime") LocalDateTime freeTime,@Param("now") LocalDateTime now,@Param("update") LocalDateTime update,@Param("deleted") int deleted);

    @Update("update freetime set deleted = 1 where student_id = #{userId} AND free_time = #{dateTime,jdbcType=TIMESTAMP}")
    void change(@Param("userId")int userId,@Param("dateTime")LocalDateTime dateTime);

    @Select("select count(*) from student")
    int count();

    @Select("select free_time from freetime where deleted != 1")
    List<String> getAll();

}
