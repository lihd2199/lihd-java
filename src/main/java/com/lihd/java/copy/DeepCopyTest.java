package com.lihd.java.copy;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

/**
 * @program: lihd-java
 * @description: 深拷贝
 * @author: li_hd
 * @create: 2020-05-19 13:26
 **/
@Data
public class DeepCopyTest implements Cloneable {

    @Override
    protected Object clone() {
        final DeepCopyTest deepCopyTest = new DeepCopyTest();
        deepCopyTest.setAbc(abc);
        deepCopyTest.setStudent(new Student(student.age, student.name));
        return deepCopyTest;
    }

    private DeepCopyTest.Student student;
    private int abc;

    @Test
    public void test_listCopy() throws CloneNotSupportedException {

        final DeepCopyTest deepCopyTest = new DeepCopyTest();
        deepCopyTest.setStudent(new Student(1, "lihd"));

        final DeepCopyTest clone = (DeepCopyTest) deepCopyTest.clone();

        System.out.println(deepCopyTest.student.age);
        System.out.println(clone.student.age);

        deepCopyTest.getStudent().setAge(12);

        System.out.println(deepCopyTest.student.age);
        System.out.println(clone.student.age);

        Assert.assertEquals(deepCopyTest.student.age, clone.student.age);

    }

    @Data
    @AllArgsConstructor
    static class Student {
        int age;
        String name;
    }


}
