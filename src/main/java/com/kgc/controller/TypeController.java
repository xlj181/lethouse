package com.kgc.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.Type;
import com.kgc.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TypeController {
    @Autowired
    private TypeService typeService;
    @RequestMapping("/allT")
    @ResponseBody
    public Map<String,Object> allT(Integer page,Integer rows){
        PageInfo<Type> pageInfo=typeService.selectDistrict(page,rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    //添加
    @RequestMapping("/insertT")
    @ResponseBody
    public String insertT(Type type){
        int insert = typeService.insert(type);
        return "{\"result\":"+insert+"}";
    }
    //修改
    @RequestMapping("/updateT")
    @ResponseBody
    public String updateT(Type type ){
        int update = typeService.updateByPrimaryKey(type);
        return "{\"result\":"+update+"}";
    }
    //删除
    @RequestMapping("/deleteT")
    @ResponseBody
    public String deleteT(Integer id){
        int i = typeService.deleteByPrimaryKey(id);
        return "{\"result\":"+i+"}";
    }

    //批量删除
    @RequestMapping("/deletesT")
    @ResponseBody
    public String deleteT(String ids){
        String [] arry=ids.split(",");
        Integer [] id=new Integer[arry.length];
        for(int i=0;i<arry.length;i++){
            id[i]=Integer.parseInt(arry[i]);
        }
        int i = typeService.deleteMoreType(id);
        return "{\"result\":"+i+"}";
    }
}
