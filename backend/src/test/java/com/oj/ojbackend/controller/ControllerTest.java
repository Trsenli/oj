package com.oj.ojbackend.controller;
import com.oj.ojbackend.OjBackEndApplication;
import com.oj.ojbackend.pojo.RunnerDto;
import com.oj.ojbackend.pojo.User;
import com.oj.ojbackend.pojo.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = OjBackEndApplication.class)
public class ControllerTest {

    @Autowired
    private CodeController codeController;
    @Autowired
    private CommitLogController commitLogController;
    @Autowired
    private FavorComtroller favorComtroller;
    @Autowired
    private ProblemController problemController;
    @Autowired
    private UserController userController;


    @Test
    public void testcodeController() throws IOException {
        RunnerDto runnerDto=new RunnerDto();
        File file=new File("./17_1.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuffer sb = new StringBuffer();
        String text = null;
        while((text = bufferedReader.readLine()) != null){
            sb.append(text);
        }
        runnerDto.setCodeCont(sb.toString());
        runnerDto.setFid("17_1");
        runnerDto.setUid("1234567");
        runnerDto.setCodeType("C");
        System.out.println(codeController.run(runnerDto));
    }
    @Test
    public void testCommitLogController() {
        System.out.println(commitLogController.getLogs("1234567"));
    }
    @Test
    public void testFavorController() {
        System.out.println(favorComtroller.getFavorById("1234567"));
        System.out.println(favorComtroller.addFavor("1234567","17_1"));
        System.out.println(favorComtroller.deleteFavor("1234567","17_1"));
    }
    @Test
    public void testProblemController() {
        System.out.println(problemController.getProblemByFileId("17_1"));
        System.out.println(problemController.getProblemByPages("0","8"));
        System.out.println(problemController.getProblemByFuzzyTitle("网络寻路"));
    }
    @Test
    public void testUserController() {
       System.out.println(userController.getUserById("1234567"));
       System.out.println(userController.getUserList());
        UserDto userDto=new UserDto();
        userDto.setId("1234567");
        userDto.setPwd("1234567");
        System.out.println(userController.login(userDto));
        User user=new User();
        user.setUser_id("1234567");
        user.setUser_pwd("1234567");
        user.setUser_email("119498@qq.com");
        user.setUser_nick_name("testname");
        System.out.println(userController.addUser(user));
        user.setUser_email("123456@qq.com");
        System.out.println(userController.updateUser(user));

    }

}

