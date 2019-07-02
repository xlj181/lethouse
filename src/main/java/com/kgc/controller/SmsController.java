package com.kgc.controller;

import com.kgc.sms.SmsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class SmsController {
    @RequestMapping("/goCode")
    @ResponseBody
    public String goCode(String tel, HttpSession session){
        //创建验证码
       int code=(int)(Math.random()*10000) ;
       //使用session保存
        session.setAttribute("code",code);
        session.setMaxInactiveInterval(3000);//验证码过期时间
        //创建发送的手机消息
        String msg="你好:你的登入验证码是:"+code+",请不要被别人获取";

        //发送消息
        int result= SmsUtil.sendMsm(tel,msg);
        return "{\"result\":"+result+"}";
    }
}
