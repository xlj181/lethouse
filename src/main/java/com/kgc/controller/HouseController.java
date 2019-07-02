package com.kgc.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.*;
import com.kgc.service.DistrictService;
import com.kgc.service.HouseService;
import com.kgc.service.StreetService;
import com.kgc.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HouseController {
    @Autowired
    private DistrictService districtService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private HouseService houseService;

    @RequestMapping("/selectStreet")
    public String selectStreet(Model model){
        List<District> districts = districtService.selectDistrict();
        List<Type> types = typeService.selectType();
        model.addAttribute("districts",districts);
        model.addAttribute("types",types);
        return "fabu";
    }

    @Autowired
    private StreetService streetService;

    @RequestMapping("/streetByDid")
    @ResponseBody
    public List<Street> streetByDid(Integer did){
        //调用业务
        return streetService.getStreetByDistrict(did);
    }


    @RequestMapping("/insertH")
    public String insertH(House house,
               @RequestParam(value = "pfile",required = false)
                       CommonsMultipartFile pfile, HttpSession session) throws IOException {
        String fname=pfile.getOriginalFilename();
        String expName=fname.substring(fname.lastIndexOf("."));
        String saveName=System.currentTimeMillis()+expName; //保存文件名
        File file=new File("F:\\images\\"+saveName);
        pfile.transferTo(file);  //保存
        //设置编号
        house.setId(System.currentTimeMillis()+"");
        //设置用户编号
        Users user=(Users)session.getAttribute("user");
        house.setUserId(user.getId());
        //设置审核状态 0  如果表中有默认值 可不设
        house.setIspass(0);
        //设置是否删除  0
        house.setIsdel(0);
        //设置图片路径
        house.setPath(saveName);

        if(houseService.allHouse(house)>0){ //保存数据
            //调用业务
            //houseService.addHouse(house); //添加信息到数据库
            return "redirect:getUserHouse";  //跳转页面
        }
        else{
            //成功上传的图片删除
            file.delete();
            return "redirect:selectStreet";  //跳转页面
        }
        //return "redirect:/selectStreet";  //跳转页面
    }

    @RequestMapping("/getUserHouse")
    public String getUserHouse(Integer page,Model model,HttpSession session){
        Users user=(Users)session.getAttribute("user");
        PageInfo<House> pageInfo=houseService.geUserHousePage(page==null?1:page,5,user.getId());
        model.addAttribute("pageInfo",pageInfo);
        return "guanli";
    }

    //修改用户的房屋
    @RequestMapping("/getHouse")
    public String getHouse(String id, Model model){
        //查询区域
        List<District> districts = districtService.selectDistrict();

        //查询街道
        List<Type> types = typeService.selectType();
        //查询用户
        House house = houseService.getHouse(id);
        //传参
        model.addAttribute("districts",districts);
        model.addAttribute("types",types);
        model.addAttribute("house",house);

        return "upfabu";
    }


    //修改出租房
    @RequestMapping("/upHouse")
    public String upHouse(String oldPic,House house, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile, HttpSession session) throws  Exception{
        //1.修改时判断用户有没有修改图片
        File file=null;
        if(pfile.getOriginalFilename().equals("")){
            //System.out.println("不修改图片");
            //不需要实现文件上传，同时house实体的path属性无需设置属性
        }else {
            //System.out.println("修改图片");
            //上传新的图片，删除旧的图片，设置path为上传新的图片名称
            file=new File("F:\\images\\"+oldPic);
            pfile.transferTo(file);  //保存
            //设置图片名称
            house.setPath(oldPic);
        }
        if(houseService.updateHouse(house)<=0){
            //成功上传的图片删除
            if(file!=null) file.delete();
        }

        return "redirect:getUserHouse";  //跳转到查询用户出租房
    }

    //删除用户
    @RequestMapping("/deleteHouse")
    @ResponseBody
    public String deleteHouse(String id){
        int i = houseService.deleteHouse(id);
        return "{\"result\":"+i+"}";
    }

    //查看房屋审核状态
    //未审核
    @RequestMapping("/houseNoPass")
    @ResponseBody
    public Map<String ,Object> houseNoPass(Integer page,Integer rows){
        PageInfo<House> pageInfo = houseService.getHouseByState(page, rows ,0);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    //已审核
    @RequestMapping("/houseYesPass")
    @ResponseBody
    public Map<String ,Object> houseYesPass(Integer page,Integer rows){
        PageInfo<House> pageInfo = houseService.getHouseByState(page, rows ,1);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    //通过审核
    @RequestMapping("/passhouse")
    @ResponseBody
    public String passhouse(String id){
        int i = houseService.passHouse(id);
        return "{\"result\":"+i+"}";
    }

    //浏览者看的房屋
    @RequestMapping("/houseByList")

    public String houseByList(HouseCondition houseCondition,Model model){

        //调业务获取房屋
        PageInfo<House> pageInfo=houseService.getHouseByList(houseCondition);
      model.addAttribute("pageInfo",pageInfo);
      /*//解决模糊查询回显去掉%
        if (houseCondition.getTitle()!=null) {
            houseCondition.setTitle(houseCondition.getTitle().replaceAll("%", ""));
        }*/
     //条件查询回显
      model.addAttribute("houseCondition",houseCondition);
       // System.out.println("houseCondition = " + houseCondition.toString());
        return "list";
    }

    //浏览者看房屋类型
        @RequestMapping("/getType")
        @ResponseBody
    public List<Type> getType(){
       return typeService.selectType();
        }


    //浏览者看房屋街道
    @RequestMapping("/getDistrict")
    @ResponseBody
    public List<District> getDistrict(){
        return districtService.selectDistrict();
    }

    //通过标题查询详情
    @RequestMapping("/getDetails")
    public String getDetails(String id,Model model){
       House houses = houseService.houseByIdLook(id);
        model.addAttribute("houses",houses);
        return "details";
    }
}
