package com.oj.ojbackend.controller;

import com.oj.ojbackend.mapper.ProblemMapper;
import com.oj.ojbackend.pojo.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/problem")
@RestController
public class ProblemController {

    @Autowired
    private ProblemMapper problemMapper;

    @GetMapping("/id/{pd_id}")
    public List<Problem> getProblemById(@PathVariable("pd_id") String pd_id){

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pb_id",pd_id);
        return problemMapper.queryProblemByIf(map);
    }

    @GetMapping("/fid/{pd_file_id}")
    public List<Problem> getProblemByFileId(@PathVariable String pd_file_id){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pb_file_id",pd_file_id);
        return problemMapper.queryProblemByIf(map);
    }

    // 模糊查询题目名称
    @GetMapping("/title/{pd_title}")
    public  List<Problem> getProblemByFuzzyTitle(@PathVariable String pd_title){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pb_title",pd_title);
        return problemMapper.queryProblemByIf(map);
    }

    // 分页查询题目
    @GetMapping("/page/{startIndex}/{pageSize}")
    public List<Problem> getProblemByPages(@PathVariable String startIndex,@PathVariable String pageSize){
        Map<String,Object> map = new HashMap<String,Object>();
        int startIndexInt = Integer.parseInt(startIndex);
        int pageSizeInt = Integer.parseInt(pageSize);
        map.put("startIndex",startIndexInt);
        map.put("pageSize",pageSizeInt);
        return problemMapper.queryProblemByIf(map);
    }

}
