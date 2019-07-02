package com.kgc.controller;

import com.github.pagehelper.PageInfo;

import com.kgc.entity.UserCondition;
import com.kgc.entity.Users;
import com.kgc.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UsersController {
    @Autowired
    private UsersService usersService;
    //查询全部
   /* @RequestMapping("allsU")
    @ResponseBody
    public Map<String ,Object> allsU(Integer page,Integer rows){
        PageInfo<Users> pageInfo=usersService.selectUsers(page,rows);
        Map<String ,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return  map;
    }*/
    //带条件查询
    @RequestMapping("/allU")
    @ResponseBody
    public Map<String ,Object> allU(UserCondition userCondition){
        PageInfo<Users> pageInfo=usersService.selectUsers(userCondition);
       // System.out.println("con"+userCondition.getTelephone());
        Map<String ,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return  map;
    }

    //添加
    @RequestMapping("/insertU")
    @ResponseBody
    public String insertU(Users users){
        int insert = usersService.insertSelective(users);
        return "{\"result\":"+insert+"}";
    }
    //修改
    @RequestMapping("/updateU")
    @ResponseBody
    public String updateU(Users users){
        int update = usersService.updateByPrimaryKey(users);
        return "{\"result\":"+update+"}";
    }
    //删除
    @RequestMapping("/deleteU")
    @ResponseBody
    public String deleteU(Integer id){
        int i = usersService.deleteByPrimaryKey(id);
        return "{\"result\":"+i+"}";
    }
    //删除多条

    @RequestMapping("/deleteMoreU")
    @ResponseBody
    public String deleteMoreU(String ids){
        String [] arry=ids.split(",");
        Integer [] id=new Integer[arry.length];
        for(int i=0;i<arry.length;i++){
            id[i]=Integer.parseInt(arry[i]);
        }
        int i = usersService.deleteMoreUsers(id);
        return "{\"result\":"+i+"}";
    }

    //	检查用户名是否存在
    @RequestMapping("/checkUname")
    @ResponseBody
    public String checkUname(String username){
        int i = usersService.checkUname(username);
        return "{\"result\":"+i+"}";
    }

    //
    @RequestMapping("/regsU")
    //@ResponseBody
    public String regsU(Users users){
        int i = usersService.addUser(users);
        if(i>0)
        return "login";
        else
            return "error";
    }
    //实现登录
    @RequestMapping("/login")
    public String login(String inputCode,String username, String password, Model model, HttpSession session) {
        //获取手机验证
        String code = session.getAttribute("code").toString();
        if (code.equals(inputCode)) {
            Users user = usersService.login(username, password);
            if (user == null) {
                model.addAttribute("info", "用户名密码错误!");
                return "login";  //继续登入
            } else {
                //只要登入:使用session或者cookie保存登入的信息
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(3000);
                return "redirect:getUserHouse";  //用户中心的管理页
            }
        }else {
            model.addAttribute("info", "验证密码错误！！！");
            return "login";
        }
    }
}
