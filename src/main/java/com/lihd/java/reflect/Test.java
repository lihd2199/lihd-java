package com.lihd.java.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2019-12-30 09:57
 **/
public class Test {

    public static void main(String[] args) throws IllegalAccessException {

        Demo demo = new Demo();
        demo.setName("lihd");
        demo.setAge(18);

        final Field[] fields = demo.getClass().getDeclaredFields();

        for (Field field : fields) {

            System.out.println(field.getName());

            System.out.println(field.getType());

            NotEmpty annotation = field.getAnnotation(NotEmpty.class);

            if(annotation != null && annotation.notEmpty()){

                field.setAccessible(true);

                System.out.println(field.get(demo));

            }


        }






    }


}
