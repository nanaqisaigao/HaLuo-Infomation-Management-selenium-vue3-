package com.example.mapper;

import com.example.pojo.entity.HistoryPassword;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface HistoryPwdMapper {
    @Select("select * from historypassword where user_id=#{id} and deleted=0;")
    List<HistoryPassword> getpwd(Integer id);
    @Insert("insert into historypassword (user_id,historical_password, deleted) values " +
            "(#{userId},#{historicalPassword},0)")
    void insert(HistoryPassword historyPassword);
    @Update("update historypassword set deleted=1 where id=#{id}")
    void update(Integer id);

}
