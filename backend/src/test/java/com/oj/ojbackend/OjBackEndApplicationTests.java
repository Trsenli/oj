package com.oj.ojbackend;

import com.oj.ojbackend.mapper.ProblemMapper;
import com.oj.ojbackend.mapper.UserMapper;
import com.oj.ojbackend.pojo.Problem;
import com.oj.ojbackend.pojo.User;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
class OjBackEndApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private DataSource dataSource;

    @Test
    void dbConnectTest() throws SQLException {

        System.out.println(dataSource.getConnection());
        System.out.println(dataSource);
    }

    @Test
    void CRUD(){
        userMapper.addUser(new User("testId","1234","for test"));
        System.out.println(userMapper.queryUserById("testId"));
        userMapper.updateUser(new User("testId","1234","for test - update"));
        System.out.println(userMapper.queryAllUser());
        userMapper.deleteUser("testId");
        System.out.println(userMapper.queryAllUser());
    }

    @Test
    void queryProblemByIf(){

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pb_id","asd");
        List<Problem> problems = problemMapper.queryProblemByIf(map);

    }

    @Test
    void limit(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("startIndex",0);
        map.put("pageSize",10);
        List<Problem> problems = problemMapper.queryProblemByIf(map);
    }

}
