package com.oj.ojbackend.controller;

import com.oj.ojbackend.mapper.UserMapper;
import com.oj.ojbackend.pojo.User;
import com.oj.ojbackend.pojo.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto){
        User user = userMapper.queryUserById(userDto.getId());
        if (user.getUser_pwd().equals(userDto.getPwd()))return "success";
        else return "fail";
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
