package com.oj.ojbackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CommitLog {
    private String uid;
    private String pfid;
    private String title;
    private String exec_res;
    private String use_time;
    private String use_memery;
    private String score;
    private String time;
}
