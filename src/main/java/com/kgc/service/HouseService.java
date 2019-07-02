package com.kgc.service;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.House;
import com.kgc.entity.HouseCondition;

import java.util.List;

public interface HouseService {
    int allHouse(House house);

    //查询用户的房屋
    PageInfo<House> geUserHousePage(Integer page,Integer rows,Integer uid);

    //查询单条
    public House getHouse(String id);

    //修改房屋
    int updateHouse(House house);

    //删除
    int deleteHouse(String id);

    //查询房屋是否审核
    PageInfo<House> getHouseByState(Integer page,Integer rows, Integer ispass);

    //t通过审核
    int passHouse(String id);

    //浏览者看的房屋
    PageInfo<House> getHouseByList(HouseCondition houseCondition);

     //通过标题查看详情
        House houseByIdLook(String id);
}
