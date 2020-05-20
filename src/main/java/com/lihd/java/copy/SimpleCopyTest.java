package com.lihd.java.copy;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

/**
 * @program: lihd-java
 * @description: 浅拷贝
 * @author: li_hd
 * @create: 2020-05-19 11:45
 **/
@Data
public class SimpleCopyTest implements Cloneable {

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    private Student student;
    private int abc;

    @Test
    public void test_listCopy() throws CloneNotSupportedException {

        final SimpleCopyTest simpleCopyTest = new SimpleCopyTest();

        final SimpleCopyTest clone = (SimpleCopyTest) simpleCopyTest.clone();

        System.out.println(simpleCopyTest.student.age);
        System.out.println(clone.student.age);

        simpleCopyTest.getStudent().setAge(12);

        System.out.println(simpleCopyTest.student.age);
        System.out.println(clone.student.age);

        Assert.assertEquals(simpleCopyTest.student.age, clone.student.age);

    }


    @Data
    @AllArgsConstructor
    static class Student {
        int age;
        String name;
    }

}
