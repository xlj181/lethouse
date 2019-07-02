package com.kgc.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.District;
import com.kgc.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DistrictController {
    @Autowired
    private DistrictService districtService;
    @RequestMapping("/allD")
    @ResponseBody
    public Map<String,Object> allD(Integer page,Integer rows){
        //List<District> districts = districtService.allDistrict();
        PageInfo<District> pageInfo = districtService.selectDistrict(page, rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    //添加
    @RequestMapping("/insertD")
    @ResponseBody
    public String insertD(District district){
        int insert = districtService.insert(district);
        return "{\"result\":"+insert+"}";
    }
    //修改
    @RequestMapping("/updateD")
    @ResponseBody
    public String updateD(District district){
        int insert = districtService.updateByPrimaryKey(district);

        return "{\"result\":"+insert+"}";
    }
    //删除
    @RequestMapping("/deleteD")
    @ResponseBody
    public String deleteD(Integer id){
        int i = districtService.deleteByPrimaryKey(id);
        return "{\"result\":"+i+"}";
    }

    /**
     * 删除多条
     */
    @RequestMapping("/deleteMD")
    @ResponseBody
    public String deleteMD(String ids){
        String [] arry=ids.split(",");
        Integer [] id=new Integer[arry.length];
        for(int i=0;i<arry.length;i++){
            id[i]=Integer.parseInt(arry[i]);
        }
        int i = districtService.deleteMoreDistrict(id);
        return "{\"result\":"+i+"}";
    }
}
