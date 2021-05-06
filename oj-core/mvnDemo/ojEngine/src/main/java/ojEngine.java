import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ojEngine {
    private String cmd;
    private String timeLim;
    private String memLim;

    /**
     *
     * @param codeCont  代码
     * @param codeType  代码类型(.c/.cpp/.java/.python)，先用C尝试
     * @param path  题目路径，以xx-x形式存储，如17-1
     */
    public ojEngine(String codeCont,String codeType,String path,int caseNum,String timeLim,String memLim) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("temp"+codeType));
        out.write(codeCont);
        out.close();

        cmd="python3 src path num";
        cmd.split(" ")[1]="temp"+codeType;
        cmd.split(" ")[2]="/mnt/ojResource/"+path.split("-")[0]+"/"+path.split("-")[1];
        cmd.split(" ")[3]= String.valueOf(caseNum);

        this.timeLim=timeLim;
        this.memLim=memLim;
    }

    public JSONObject execCode(){
        Runtime runtime = Runtime.getRuntime();
        Process exec = null;
        BufferedReader br = null;
        try {
            exec = runtime.exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读取命令行内容
        String execRes="",execTime="",execMem="";
        int score=0;

        Result res=new Result(execRes,execTime,execMem);
        res.judge(timeLim,memLim);
        JSONObject obj=new JSONObject();
        obj.put("execRes",res.execRes);
        obj.put("execTime",res.execTime);
        obj.put("execMem",res.execMem);
        obj.put("score",score);
        return obj;
    }
}

class Result{
    String execRes;
    String execTime;
    String execMem;

    public Result(String execRes, String execTime, String execMem) {
        this.execRes = execRes;
        this.execTime = execTime;
        this.execMem = execMem;
    }

    public void judge(String timeLim,String memLim){
        int setTime= Integer.parseInt(timeLim);
        int setMem=Integer.parseInt(memLim)*1024;
        if(execRes.equals("Accepted")){
            if(Integer.parseInt(execMem)>setMem){
                execRes="Memory Limit Exceeded";
            }
            if(Integer.parseInt(execTime)>setTime){
                execRes="Time Limit Exceeded";
            }
        }
    }
}
