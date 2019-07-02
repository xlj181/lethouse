package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.entity.DistrictExample;
import com.kgc.entity.Street;
import com.kgc.entity.StreetExample;
import com.kgc.mapper.StreetMapper;
import com.kgc.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetServiceImpl implements StreetService {
    @Autowired
    private StreetMapper streetMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return streetMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Street record) {
        return streetMapper.insert(record);
    }

    @Override
    public List<Street> selectByExample(StreetExample example) {
        return streetMapper.selectByExample(example);
    }

    @Override
    public Street selectByPrimaryKey(Integer id) {
        return streetMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Street record) {
        return streetMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<Street> selectDistrict(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        StreetExample streetExample=new StreetExample();
        List<Street> list=streetMapper.selectByExample(streetExample);
        return new PageInfo<>(list);
    }

    @Override
    public List<Street> getStreetByDistrict(Integer districtId) {

        StreetExample streetExample=new StreetExample();
        StreetExample.Criteria criteria=streetExample.createCriteria();
        //传条件
        criteria.andDistrictIdEqualTo(districtId);
        List<Street> list =streetMapper.selectByExample(streetExample);
        return list;
    }

    @Override
    public PageInfo<Street> selectDistrict(Integer page, Integer pageSize, Integer districtId) {
       PageHelper.startPage(page,pageSize);
       //查询街道
        StreetExample streetExample=new StreetExample();
        StreetExample.Criteria criteria=streetExample.createCriteria();
        //传条件
        criteria.andDistrictIdEqualTo(districtId);
        List<Street> list =streetMapper.selectByExample(streetExample);
        return new PageInfo<>(list);
    }

    @Override
    public int deleteStreetById(Integer[] ids) {
        return streetMapper.deleteStreetById(ids);
    }
}
