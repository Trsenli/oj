package com.oj.ojbackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String user_id;
    private String user_pwd;
    private String user_nick_name;
    private String user;
}
