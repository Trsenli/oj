package com.oj.ojbackend.mapper;

import com.oj.ojbackend.pojo.Problem;
import com.oj.ojbackend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ProblemMapper {
    List<Problem> queryProblemByIf(Map<String,Object> map);
}
