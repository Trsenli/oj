package com.oj.ojbackend.controller;

import com.oj.ojbackend.mapper.UserMapper;
import com.oj.ojbackend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    // 查询所有用户
    @GetMapping("/allUser")
    public List<User> getUserList(){
        return userMapper.queryAllUser();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id){
        return userMapper.queryUserById(id);
    }

    @PostMapping()
    public String addUser(@RequestBody User user){
        int res = userMapper.addUser(user);
        if(res>0)return "success";
        else return "fail";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
