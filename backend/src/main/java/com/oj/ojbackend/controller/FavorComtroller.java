package com.oj.ojbackend.controller;

import com.oj.ojbackend.mapper.FavorMapper;
import com.oj.ojbackend.pojo.Favor;
import com.oj.ojbackend.pojo.FavorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FavorComtroller {
    @Autowired
    private FavorMapper favorMapper;

    @GetMapping("/favor/{uid}")
    public List<FavorDto> getFavorById(@PathVariable String uid){
        return favorMapper.queryFavorById(uid);
    }

    @GetMapping("/favor/add/{uid}/{fid}")
    public String addFavor(@PathVariable String uid,@PathVariable String fid){
        int res = favorMapper.addFavor(new Favor(uid,fid));
        if(res>0)return "success";
        else return "fail";
    }

    @GetMapping("/favor/delete/{uid}/{fid}")
    public String deleteFavor(@PathVariable String uid,@PathVariable String fid){
        int res = favorMapper.deleteFavor(new Favor(uid,fid));
        if(res>0)return  "success";
        else return "fail";
    }
}
