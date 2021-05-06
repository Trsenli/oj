package com.oj.ojbackend.controller;

import com.alibaba.fastjson.JSONObject;

import com.oj.ojbackend.mapper.CommitLogMapper;
import com.oj.ojbackend.mapper.ProblemMapper;
import com.oj.ojbackend.pojo.CommitLog;
import com.oj.ojbackend.pojo.Problem;
import com.oj.ojbackend.pojo.RunnerDto;
import com.oj.ojbackend.ojEngine.ojEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CodeController {

    @Autowired
    private ProblemMapper problemMapper;
    @Autowired
    private CommitLogMapper commitLogMapper;

    @PostMapping("/run")
    public JSONObject run(@RequestBody RunnerDto runnerDto) throws IOException {
       System.out.println("========run invoke==============");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pb_file_id",runnerDto.getFid());
        Problem problem = problemMapper.queryProblemByIf(map).get(0);
        String timeLim = Integer.toString(problem.getPb_time_limit());
        String menLim = Integer.toString(problem.getPb_memory_limit());
        String test_case_score = problem.getPb_test_case_score();
        System.out.println(test_case_score);
        List<String> testCaseList = JSONObject.parseArray(test_case_score,String.class);
        int caseNum = testCaseList.size();

        System.out.println(caseNum);
        System.out.println(timeLim);
        System.out.println(menLim);

        ojEngine ojEngine = new ojEngine(runnerDto.getCodeCont(),
                runnerDto.getCodeType(),
                runnerDto.getFid(),
                caseNum,
                timeLim,
                menLim);
        JSONObject jo = ojEngine.execCode();
        System.out.println(jo.toString());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());// new Date()为获取当前系统时间
        // 添加日志
        CommitLog commitLog = new CommitLog(
                runnerDto.getUid(),
                runnerDto.getFid(),
                problem.getPb_title(),
                jo.getString("execRes"),
                jo.getString("execTime"),
                jo.getString("execMem"),
                jo.getString("score"),
                time
                );
        commitLogMapper.addCommitLog(commitLog);

        return jo;
    }
}
