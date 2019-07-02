package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.entity.House;
import com.kgc.entity.HouseCondition;
import com.kgc.mapper.HouseMapper;
import com.kgc.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;

    @Override
    public House getHouse(String id) {
        return houseMapper.getHouseAndDid(id);
    }

    @Override
    public int deleteHouse(String id) {
        House house=new House();
        house.setId(id);
        house.setIsdel(new Integer(1));
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    //通过审核
    @Override
    public int passHouse(String id) {
        House house=new House();
        house.setId(id);
        house.setIspass(1);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    //通过标题查询详情
    @Override
    public House houseByIdLook(String id) {
        return houseMapper.houseByIdLook(id);
    }

    //浏览者看的房屋
    @Override
    public PageInfo<House> getHouseByList(HouseCondition houseCondition) {
        PageHelper.startPage(houseCondition.getPage(),houseCondition.getPageSize());
        //调用业务  Example只能实现单表条件搜索查询
        List<House> list=houseMapper.getHouseByList(houseCondition);
        return new PageInfo<House>(list);
    }

    //查看房屋审核状态
    @Override
    public PageInfo<House> getHouseByState(Integer page, Integer rows, Integer ispass) {
        PageHelper.startPage(page,rows);
        List<House> list = houseMapper.getHouseByState(ispass);
        return new PageInfo<House>(list);
    }

    @Override
    public int updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    //查询用户房屋
    @Override
    public PageInfo<House> geUserHousePage(Integer page, Integer rows, Integer uid) {
        PageHelper.startPage(page,rows);
        List<House> list=houseMapper.geHouseUserById(uid);
        PageInfo<House> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    //添加
    @Override
    public int allHouse(House house) {
        return houseMapper.insertSelective(house);
    }
}
