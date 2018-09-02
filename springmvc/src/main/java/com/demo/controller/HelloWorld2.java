package com.demo.controller;

import com.demo.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.security.Principal;
import java.util.Locale;
import java.util.Map;

@SessionAttributes(value = {"user"},types = {String.class})
@Controller
public class HelloWorld2 {


    //自动装配，支持级联属性，如user.address.tel
    //http://localhost:8080/world21?name=zhangsan&age=10
    @RequestMapping("/world21")
    public void hello(User user){
        System.out.println(user);
    }

    @ModelAttribute
    public void getUser(Map<String,Object> map){
        User user = new User(null,13);
        map.put("user", user);
    }

    //传入原生API
    @RequestMapping("/world22")
    public void hello1(HttpServletRequest request,
                         HttpServletResponse response,
                         HttpSession session) throws IOException {
        System.out.println(request);
        response.getWriter().write("hello world");
    }

    //模型数据
    @RequestMapping("/world23")
    public ModelAndView hello1(){
        ModelAndView modelAndView = new ModelAndView("susu");
        modelAndView.addObject("name","zhangsan");
        return  modelAndView;
    }
    @RequestMapping("/world24")
    public String hello1(Map<String,Object> map){
        map.put("name","zhangsan");
        return  "susu";
    }
    @RequestMapping("/world25")
    public String hello3(Map<String,Object> map){
        User user = new User("zhangsan",18);
        map.put("user",user);
        map.put("shsh","wosho");
        return  "susu";
    }

    //国际化
    @RequestMapping("/world26")
    public String hello4(){
        return  "i18n";
    }

    //自定义视图
    @RequestMapping("/world27")
    public String hello5(){
        return  "helloView";
    }

    //转发
    @RequestMapping(value="/world28")
    public String hello7(){
        return "forward:/helloView";
    }

    //重定向
    @RequestMapping(value="/world29")
    public String hello8(){
        return "redirect:/helloView";
    }

}
