
package com.example.pojo.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "college_id")
    private Integer collegeId;

    @Column(name = "major_id")
    private Integer majorId;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "education")
    private String education;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "deleted")
    private Integer deleted;

    @Transient
    private String phone;

    @Transient
    private String collegeName;

    @Transient
    private String majorName;

}
