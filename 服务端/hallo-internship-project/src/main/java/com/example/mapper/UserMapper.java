package com.example.mapper;


import com.example.pojo.entity.PwdParams;
import com.example.pojo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from user where phone = #{phone}")
    User getByPhone(String phone);

    @Select("select * from user where id = #{userId}")
    User selectByPrimaryKey(Integer userId);

    @Update("update user set Password=#{Password} where id=#{userId}")
    void updatePwd(PwdParams pwdParams);

    @Select("select * from user where id = #{id}")
    User getById(Integer id);

    @Select("select count(*) from user where phone = #{phone}")
    int existUser(String phone);

    void insert(User user);
}
