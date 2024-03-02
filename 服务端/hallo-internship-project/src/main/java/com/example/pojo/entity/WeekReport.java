package com.example.pojo.entity;

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
@Table(name = "weekreport")
public class WeekReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
    @Column(name = "this_week_work")
    private String thisWeekWork;
    @Column(name = "this_week_idea")
    private String thisWeekIdea;
    @Column(name = "finished")
    private Boolean finished;
    @Column(name = "next_week_work")
    private String nextWeekWork;
    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "deleted")
    private Integer deleted;
    @Transient
    private String userName;
}
