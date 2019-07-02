package com.kgc.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.Street;
import com.kgc.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class StreetController {
    @Autowired
    private StreetService streetService;
    @RequestMapping("/allS")
    @ResponseBody
    public Map<String,Object> allS(Integer page,Integer rows){
        PageInfo<Street> pageInfo=streetService.selectDistrict(page,rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());

        return map;
    }

    @RequestMapping("/getStreetByDid")
    @ResponseBody
    public Map<String,Object> getUsers(Integer page,Integer rows,Integer did){
        //调用业务
        PageInfo<Street> pageInfo=streetService.selectDistrict(page,rows,did);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }



    @RequestMapping("/insertS")
    @ResponseBody
    public String insertS(Street street){
        int insert = streetService.insert(street);
        return "{\"result\":"+insert+"}";
    }
    //修改
    @RequestMapping("/updateS")
    @ResponseBody
    public String updateS(Street street){
        int update = streetService.updateByPrimaryKey(street);
        return "{\"result\":"+update+"}";
    }
    //删除
    @RequestMapping("/deleteS")
    @ResponseBody
    public String deleteS(Integer id){
        int i = streetService.deleteByPrimaryKey(id);
        return "{\"result\":"+i+"}";
    }
    //批量删除
    @RequestMapping("/deletesS")
    @ResponseBody
    public String deletesS(String ids){
        String [] arry=ids.split(",");
        Integer [] id=new Integer[arry.length];
        for(int i=0;i<arry.length;i++){
            id[i]=Integer.parseInt(arry[i]);
        }
        int i = streetService.deleteStreetById(id);
        return "{\"result\":"+i+"}";
    }
}
