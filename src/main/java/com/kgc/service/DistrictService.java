package com.kgc.service;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.District;
import com.kgc.entity.DistrictExample;

import java.util.List;

public interface DistrictService {
    List<District> allDistrict();

    PageInfo<District> selectDistrict(Integer page,Integer pageSize);

    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    //int insertSelective(District record);

   // List<District> selectByExample(DistrictExample example);

    District selectByPrimaryKey(Integer id);

    //int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);

    int deleteMoreDistrict(Integer[] ids);

    List<District> selectDistrict();
}
