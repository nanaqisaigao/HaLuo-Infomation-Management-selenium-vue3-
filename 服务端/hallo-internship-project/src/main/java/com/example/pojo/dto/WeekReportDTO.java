package com.example.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeekReportDTO {
    private Integer userId;

    private Date startTime;

    private Date endTime;

    private String thisWeekWork;

    private String thisWeekIdea;

    private Boolean finished;

    private String nextWeekWork;


}
