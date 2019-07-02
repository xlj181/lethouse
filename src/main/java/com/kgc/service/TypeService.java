package com.kgc.service;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.Type;
import com.kgc.entity.TypeExample;

import java.util.List;

public interface TypeService {
    int deleteByPrimaryKey(Integer id);

    int insert(Type record);

    //int insertSelective(Type record);

    List<Type> selectByExample(TypeExample example);

    Type selectByPrimaryKey(Integer id);

    //int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    PageInfo<Type> selectDistrict(Integer page, Integer pageSize);
    int deleteMoreType(Integer[] ids);

    List<Type> selectType();
}
