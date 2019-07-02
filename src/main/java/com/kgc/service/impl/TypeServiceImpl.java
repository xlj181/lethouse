package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.entity.Type;
import com.kgc.entity.TypeExample;
import com.kgc.mapper.TypeMapper;
import com.kgc.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {

        return typeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Type record) {
        return typeMapper.insert(record);
    }

    @Override
    public List<Type> selectByExample(TypeExample example) {
        return typeMapper.selectByExample(example);
    }

    @Override
    public Type selectByPrimaryKey(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Type record) {
        return typeMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<Type> selectDistrict(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        TypeExample typeExample=new TypeExample();
        List<Type> list=typeMapper.selectByExample(typeExample);
        return new PageInfo<>(list);
    }

    //查询全部
    @Override
    public List<Type> selectType() {
        return typeMapper.selectByExample(new TypeExample());
    }

    @Override
    public int deleteMoreType(Integer[] ids) {
        return typeMapper.deleteMoreType(ids);
    }
}
