package com.kgc.mapper;

import com.kgc.entity.House;
import com.kgc.entity.HouseCondition;
import com.kgc.entity.HouseExample;
import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    //查询用户房屋的信息
    List<House> geHouseUserById(Integer uid);

    //查询房屋信息 区域id
    House getHouseAndDid(String id);

    //查询房屋是否审核
    List<House> getHouseByState(Integer state);

    //浏览者看的房屋
    List<House> getHouseByList(HouseCondition houseCondition);

    //通过标题查看详情
    House houseByIdLook(String id);

}