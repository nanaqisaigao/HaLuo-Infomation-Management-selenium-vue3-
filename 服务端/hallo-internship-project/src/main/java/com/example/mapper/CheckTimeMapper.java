package com.example.mapper;

import com.example.pojo.entity.CheckClock;
import com.example.pojo.entity.Params;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

@Mapper
public interface CheckTimeMapper {
    @Select("select * from checkclock where user_id=#{userId} and check_date=#{checkDate} and deleted=0")
    CheckClock select(CheckClock CheckClock);
    @Insert("insert into checkclock(user_id, check_date, check_time, valid_time, deleted) " +
            "values (#{userId},#{checkDate},#{checkTime},#{validTime},0)")
    void insert(CheckClock CheckClock);
     @Update("update checkclock set check_time=#{checkTime},valid_time=#{validTime} where user_id=#{userId} and check_date=#{checkDate}")
    void update(CheckClock CheckClock);

    List<CheckClock> findBySearch(Params params);
}
