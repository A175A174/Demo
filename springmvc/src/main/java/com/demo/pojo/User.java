package com.demo.pojo;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class User {

    @NotNull
    private String name;

    private Integer age;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date shengri;

    @NumberFormat(pattern = "##,##.#")
    private Float money;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
