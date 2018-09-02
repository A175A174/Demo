package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hello")
public class HelloWorld {


    //@RequestMapping 支持Ant风格路径匹配
    //?匹配一个字符----/user/a??=====/user/adf
    //*匹配任意个任意字符/user/a*====/useraowf
    //**匹配多层路径/user/**/cd====/user/cd

    @ResponseBody
    @RequestMapping("/world")
    public String hello(){
        return "hello world";
    }

    @ResponseBody
    @RequestMapping(value = "/world1",method = RequestMethod.GET)
    public String hello1(){
        return "hello world";
    }

    //请求体中必须包含username和age参数，且age不等于10，否则无法访问
    @RequestMapping(value = "/world2",params = {"username","age!=10"})
    public String hello2(){
        return "hello world";
    }

    //请求头中必须包含Accept-Language，且值为zh-CN,zh;q=0.9，否则无法访问
    @RequestMapping(value = "/world3",headers = {"Accept-Language=zh-CN,zh;q=0.9"})
    public String hello3(){
        return "hello world";
    }

    //获取请求地址中的值，REST风格
    //http://localhost:8080/world/123
    @RequestMapping(value = "/world4/{id}")
    public String hello4(@PathVariable("id") String id){
        return "hello world";
    }
    //http://localhost:8080/world?id=123&name=haha
    @RequestMapping(value = "/world5")
    public String hello5(@RequestParam(value = "id", required = false) String id,
                         @RequestParam(value = "name", defaultValue = "zhuzhu") String name){
        return "hello world";
    }

    //获取请求头中的值
    @RequestMapping(value = "/gethader6")
    public String hello5(@RequestHeader("Accept-Language") String language){
        return "hello world";
    }

    //获取Cookie中的值
    @RequestMapping(value = "/gethader7")
    public void hello6(@CookieValue("JSESSIONID") String id){
        System.out.println(id);
    }
}
