package com.demo.converters;

import com.demo.pojo.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Component
public class UserConverter implements Converter<String, User> {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("name");
    }

    @Override
    public User convert(String source) {
        System.out.println(source);
        if (source != null && !"".equals(source)){
            String[] ss = source.split("-");
            User user = new User(ss[0],Integer.parseInt(ss[1]));
            return user;
        }
        return null;
    }
}
