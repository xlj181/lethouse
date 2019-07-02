package com.kgc.service;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.UserCondition;
import com.kgc.entity.Users;
import com.kgc.entity.UsersExample;

import java.util.List;

public interface UsersService {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

   // Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

   // PageInfo<Users> selectUsers(Integer page, Integer pageSize);
    //条件查询用
    PageInfo<Users> selectUsers(UserCondition userCondition);

    //删除多条
    int deleteMoreUsers(Integer[] ids);

    int checkUname(String username);

    //添加 注册用户
    int addUser(Users users);

    //实现登录
    Users login(String username,String password);



}
