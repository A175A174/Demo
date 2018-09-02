package com.demo.controller;

import com.demo.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class HelloWorld3 {

    @RequestMapping(value = "/world30")
    public void hello9(@RequestParam("user") User user, BindingResult result) {
        System.out.println(user);
        if (result.getErrorCount() > 0){
            for (FieldError fieldError : result.getFieldErrors()){
                System.out.println(fieldError.getField() + ":" + fieldError.getDefaultMessage());
            }
        }
    }

    @RequestMapping(value = "/world31")
    public void hello10(@Valid User user, BindingResult result) {
        System.out.println(user);
    }

    @RequestMapping(value = "/world32")
    public String hello11(@Valid User user, BindingResult result, Map<String,Object> map) {
        System.out.println(user);
        if (result.getErrorCount() > 0){
            return "world32";
        }
        return "ok";
    }

    @ResponseBody
    @RequestMapping(value = "/world33")
    public List<User> hello11() {
        List<User> list = new LinkedList<>();
        list.add(new User("zahndan",18));
        list.add(new User("lisi",19));
        return  list;
    }

    @RequestMapping(value = "/up")
    public void up(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
    }
}
