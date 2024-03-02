
package com.example.pojo.entity;


import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "major")
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "major_name")
    private String majorName;

    @Column(name = "college_id")
    private Integer collegeId;
    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "deleted")
    private Integer deleted;

}
