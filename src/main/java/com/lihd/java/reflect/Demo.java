package com.lihd.java.reflect;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2019-12-30 09:56
 **/
public class Demo {

    @NotEmpty
    private String name;

    @NotEmpty
    private Integer age;

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
}
