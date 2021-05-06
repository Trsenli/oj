package com.oj.ojbackend.mapper;

import com.oj.ojbackend.pojo.Favor;
import com.oj.ojbackend.pojo.FavorDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FavorMapper {
    List<FavorDto> queryFavorById(String uid);
    int addFavor(Favor favor);
    int deleteFavor(Favor favor);
}
