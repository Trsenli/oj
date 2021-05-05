package com.oj.ojbackend.ojEngine;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.*;
import java.util.Map;

public class ojEngine {
    private String cmd;
    private String timeLim;
    private String memLim;
    private int caseNum;
    private String fileName;

    /**
     *
     * @param codeCont  代码
     * @param codeType  代码类型(.c/.cpp/.java/.python)，先用C尝试
     * @param path  题目路径，以xx-x形式存储，如17-1
     * @param timeLim   题目时间限制，ms为单位
     * @param memLim    题目空间限制，KB为单位
     */
    public ojEngine(String codeCont,String codeType,String path,int caseNum,String timeLim,String memLim) throws IOException {
        fileName="temp"+codeType;
        BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
        out.write(codeCont);
        out.close();

        cmd="python3 /mnt/test.py src path num";
        cmd=cmd.replace("src","temp"+codeType);
        cmd=cmd.replace("path","/mnt/ojResource/"+path.split("-")[0]+"/"+path.split("-")[1]+"/testcase/");
        cmd=cmd.replace("num",String.valueOf(caseNum));

        this.timeLim=timeLim;
        this.memLim=memLim;
        this.caseNum=caseNum;
    }

    public JSONObject execCode() throws IOException {
        System.out.println("===========execCode invoke");
        Runtime runtime = Runtime.getRuntime();
        Process exec = null;
        BufferedReader br;
        try {
            exec = runtime.exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //读取命令行内容
        String execRes="";
        StringBuilder err= new StringBuilder();
        int score=0,execTime=0,execMem=0;
        assert exec != null;
        //读取错误内容
        br = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
        String line;
        while ((line = br.readLine()) != null){
            err.append(line).append('\n');
        }
        String errCont=err.toString();

        br = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        while ((line = br.readLine()) != null){
            System.out.println("=============line=====  "+line);
            Map<String,Object> ans= JSON.parseObject(line, new TypeReference<Map<String, Object>>() {});
            String tempRes= (String) ans.get("result");
            if(!tempRes.equals("Accepted")){
                execRes=tempRes;
                break;
            }
            int tempTime=Integer.parseInt(ans.get("timeused").toString());
            int tempMem=Integer.parseInt(ans.get("memoryused").toString());
            execRes=tempRes;
            score+=1;
            execTime= Math.max(execTime, tempTime);
            execMem=Math.max(execMem,tempMem);
        }
        score=score*100/caseNum;

        //打包运行结果内容
        Result res=new Result(execRes,execTime,execMem);
        res.judge(timeLim,memLim);
        JSONObject obj=new JSONObject();
        obj.put("execRes",res.execRes);
        obj.put("errCont",errCont);
        obj.put("execTime",res.execTime);
        obj.put("execMem",res.execMem);
        obj.put("score",score);

        //删除临时代码文件
        runtime=Runtime.getRuntime();
        exec=runtime.exec("rm -f "+fileName);
        return obj;
    }
}

class Result{
    String execRes;
    int execTime;
    int execMem;

    public Result(String execRes, int execTime, int execMem) {
        this.execRes = execRes;
        this.execTime = execTime;
        this.execMem = execMem;
    }

    public void judge(String timeLim,String memLim){
        int setTime= Integer.parseInt(timeLim);
        int setMem=Integer.parseInt(memLim)*1024;
        if(execRes.equals("Accepted")){
            if(execMem>setMem){
                execRes="Memory Limit Exceeded";
                return;
            }
            if(execTime>setTime){
                execRes="Time Limit Exceeded";
            }
        }
    }
}
