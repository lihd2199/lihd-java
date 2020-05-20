package com.lihd.java.staticinfo;

import lombok.Data;

/**
 * @program: lihd-java
 * @description: sample for static class
 * @author: li_hd
 * @create: 2020-05-13 14:21
 **/
public class SampleForStaticClass {

    private static String name;

    private String nameWithoutStatic;

    private InnerClass innerClass;

    private static InnerClass innerClass2;

    static String getName() {
        return name;
    }

    void setName(String name) {
        SampleForStaticClass.name = name;
    }

    static class InnerStaticClass {
        //        内部静态类可以有静态方法、静态属性
        private String innerName;
        private static String innerNameWithStatic;

        public String getOuterName() {
//            内部静态类只能访问静态方法、静态属性
//            System.out.println(nameWithoutStatic);
            return SampleForStaticClass.name;
        }
    }

    @Data
    class InnerClass {
        //        内部类不能有静态属性、静态方法
//        private static String innerNameWithStatic;
        private String innerName;

        public String getOuterName() {
            //内部类可以访问非静态
            System.out.println(nameWithoutStatic);
            return SampleForStaticClass.name;
        }
    }

}
