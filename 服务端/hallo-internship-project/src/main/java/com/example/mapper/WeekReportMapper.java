package com.example.mapper;

import com.example.pojo.entity.Params;
import com.example.pojo.entity.WeekReport;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WeekReportMapper {


    @Insert("insert into weekreport(student_id, start_time, end_time, this_week_work, this_week_idea, finished, next_week_work, create_time, update_time, deleted) " +
            "values (#{studentId},#{startTime},#{endTime},#{thisWeekWork},#{thisWeekIdea},#{finished},#{nextWeekWork},#{createTime},#{updateTime},#{deleted})")
    void insert( WeekReport weekReport);

    void update(WeekReport weekReport);

    List<WeekReport> findBySearch(Params params);
}
