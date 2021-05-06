package com.oj.ojbackend.mapper;

import com.oj.ojbackend.pojo.CommitLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommitLogMapper {
    List<CommitLog> queryCommitLogsById(String id);
    int addCommitLog(CommitLog commitLog);
}
