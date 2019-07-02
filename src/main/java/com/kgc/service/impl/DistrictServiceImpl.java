package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.entity.District;
import com.kgc.entity.DistrictExample;
import com.kgc.mapper.DistrictMapper;
import com.kgc.mapper.StreetMapper;
import com.kgc.service.DistrictService;
import com.kgc.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Override
    public PageInfo<District> selectDistrict(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<District> list=districtMapper.allDistrict();
        PageInfo<District> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private StreetMapper streetMapper;
    @Override
    public List<District> allDistrict() {
        return districtMapper.allDistrict();
    }

    @Override
    public int deleteMoreDistrict(Integer[] ids) {
        return districtMapper.deleteMoreDistrict(ids);
    }

    @Override
    public List<District> selectDistrict() {
        return districtMapper.selectByExample(new DistrictExample());
    }

    @Transactional
    public int deleteByPrimaryKey(Integer id) {
        try {
            districtMapper.deleteByPrimaryKey(id);
            streetMapper.deleteByPrimaryKey(id);
            return 1;
        }catch (Exception e){
            return 0;
        }

    }

    @Override
    public int insert(District record) {
        return districtMapper.insert(record);
    }

    @Override
    public District selectByPrimaryKey(Integer id) {

        return districtMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(District record) {
        return districtMapper.updateByPrimaryKey(record);
    }
}
