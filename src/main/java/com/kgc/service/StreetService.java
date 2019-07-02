package com.kgc.service;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.Street;
import com.kgc.entity.StreetExample;

import java.util.List;

public interface StreetService {
    int deleteByPrimaryKey(Integer id);

    int insert(Street record);

   // int insertSelective(Street record);

    List<Street> selectByExample(StreetExample example);

    Street selectByPrimaryKey(Integer id);

    //int updateByPrimaryKeySelective(Street record);

    int updateByPrimaryKey(Street record);

    PageInfo<Street> selectDistrict(Integer page, Integer pageSize);

    PageInfo<Street> selectDistrict(Integer page, Integer pageSize,Integer districtId);

    int deleteStreetById(Integer[] ids);

    List<Street> getStreetByDistrict(Integer districtId);;

}
