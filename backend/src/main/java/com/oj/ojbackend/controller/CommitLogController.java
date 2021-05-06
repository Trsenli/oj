package com.oj.ojbackend.controller;

import com.oj.ojbackend.mapper.CommitLogMapper;
import com.oj.ojbackend.pojo.CommitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
public class CommitLogController {
    @Autowired
    CommitLogMapper commitLogMapper;
    @GetMapping("/{uid}")
    public List<CommitLog> getLogs(@PathVariable("uid") String uid){
        List<CommitLog> commitLogs = commitLogMapper.queryCommitLogsById(uid);
        return commitLogs;
    }
}
