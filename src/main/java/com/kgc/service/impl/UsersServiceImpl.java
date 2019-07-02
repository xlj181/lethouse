package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.entity.UserCondition;
import com.kgc.entity.Users;
import com.kgc.entity.UsersExample;
import com.kgc.mapper.UsersMapper;
import com.kgc.service.UsersService;
import com.kgc.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return usersMapper.deleteByPrimaryKey(id);
    }

    //注册
    @Override
    public int checkUname(String username) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria=usersExample.createCriteria();
        //添加条件
        criteria.andNameEqualTo(username);
        criteria.andIsadminEqualTo(0);//注册用户
        List<Users> list=usersMapper.selectByExample(usersExample);

        return list.size()==0?0:1;
    }

    //实现登录
    @Override
    public Users login(String username, String password) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria=usersExample.createCriteria();
        //添加条件
        criteria.andIsadminEqualTo(0);
        criteria.andNameEqualTo(username);
        //加密
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        List<Users> list=usersMapper.selectByExample(usersExample);
        if(list.size()==1){
            return list.get(0);
        }
        return null;
    }

    @Override
    public int addUser(Users users) {
        //设置为前台注册用户
        users.setIsadmin(0);
        //密码加密
        users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
        return usersMapper.insertSelective(users);
    }

    @Override
    public int insert(Users record) {
        return usersMapper.insert(record);
    }

    @Override
    public List<Users> selectByExample(UsersExample example) {
        return usersMapper.selectByExample(example);
    }

    @Override
    public int updateByPrimaryKey(Users record) {
        return usersMapper.updateByPrimaryKey(record);
    }

    //查询所有
   /* @Override
    public PageInfo<Users> selectUsers(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        UsersExample usersExample=new UsersExample();
        List<Users> list=usersMapper.selectByExample(usersExample);

        return  new PageInfo<>(list);
    }*/

    @Override
    public int deleteMoreUsers(Integer[] ids) {
        return usersMapper.deleteMoreUsers(ids);
    }

    @Override
    public int insertSelective(Users record) {
        return usersMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Users record) {
        return updateByPrimaryKeySelective(record);
    }

    //带条件查询
    @Override
    public PageInfo<Users> selectUsers(UserCondition userCondition) {

        PageHelper.startPage(userCondition.getPage(),userCondition.getRows());
        UsersExample usersExample=new UsersExample();
        //添加条件
        UsersExample.Criteria criteria=usersExample.createCriteria();
        criteria.andIsadminEqualTo(new Integer(1));//表示管理员
        //添加查询条件
        if(userCondition.getName()!=null){
            criteria.andNameLike("%"+userCondition.getName()+"%");
        }

        if(userCondition.getTelephone()!=null){
            criteria.andTelephoneLike("%"+userCondition.getTelephone()+"%");
        }
        List<Users> list=usersMapper.selectByExample(usersExample);

        return  new PageInfo<>(list);
    }
}
