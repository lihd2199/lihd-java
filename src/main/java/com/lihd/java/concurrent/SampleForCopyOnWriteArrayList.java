package com.lihd.java.concurrent;

import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-04-16 10:45
 **/
public class SampleForCopyOnWriteArrayList {


    @Test
    public void test() {

        final CopyOnWriteArrayList<Integer> integers = new CopyOnWriteArrayList<>();

        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);


        new Thread(() -> integers.forEach(System.out::println)).start();


        new Thread(() -> {
            final boolean add = integers.add(5);
            System.out.println(add);
        }).start();




    }


}
