package com.oj.ojbackend.mapper;

import com.oj.ojbackend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<User> queryAllUser();

    User queryUserById(String id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(String id);
    
}
