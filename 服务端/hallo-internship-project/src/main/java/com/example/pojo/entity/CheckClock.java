package com.example.pojo.entity;

import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "checkclock")
public class CheckClock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "check_date")
    private Date checkDate;
    @Column(name = "check_time")
    private String checkTime;
    @Column(name = "valid_time")
    private Float validTime;
    @Column(name = "create_time")
    private LocalDateTime createTime;
    @Column(name = "update_time")
    private Timestamp updateTime;
    @Column(name = "deleted")
    private Integer deleted;
    @Transient
    private String userName;
}
