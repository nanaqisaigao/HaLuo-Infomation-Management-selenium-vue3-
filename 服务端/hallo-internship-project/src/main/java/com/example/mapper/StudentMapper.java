package com.example.mapper;


import com.example.pojo.entity.Params;
import com.example.pojo.entity.Student;
import com.example.pojo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("select username from student where user_id = #{id}")
    String getName(Integer id);

    @Select("select * from student where user_id = #{id}")
    Student findByUserId(Integer id);
    @Select("select a.*,b.college_name,c.major_name,d.phone from student a " +
            "left join college b on b.id=a.college_id " +
            "left join major c on c.id=a.major_id " +
            "left join user d on d.id=a.user_id " +
            "where a.user_id = #{id}")
    Student findByUserId2(Integer id);
    @Update("update student set college_id=#{collegeId},major_id=#{majorId},username=#{username},grade=#{grade}" +
            ",education=#{education},deleted=#{deleted} where user_id=#{userId}")
    void update(Student student);

    @Select("select * from student where id = #{id}")
    Student findById(Integer id);

    List<Student> findBySearch(Params params);

    @Select("select  * from student")
    List<Student> findAll();
    @Select("select id from student where user_id = #{userId}")
    Integer getStudentId(Integer userId);

    @Insert("insert into student(user_id, username, create_time, update_time, deleted) " +
            "values (#{userId},#{username},#{createTime},#{updateTime},#{deleted})")
    void insert(Student student);
}
