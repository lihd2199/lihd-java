package com.lihd.java.collection;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @program: lihd-java
 * @description:
 * @author: 黎
 * @create: 2020-05-08 19:48
 **/
public class SampleForList {

    @Test
    public void test() {

        Integer a = Integer.valueOf(122);

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> integers = new ArrayList<>(list);

        for (int i = 0; i < integers.size(); i++) {
            if (integers.get(i) == 5) {
                integers.remove(i);
                integers.remove(integers.get(i));
            }
        }

        System.out.println(integers);

        Iterator<Integer> var5 = integers.iterator();

        while (var5.hasNext()) {
            Integer integer = var5.next();
            if (integer == 7) {
                var5.remove();
            }
        }


        for (Integer integer : integers) {
            if (integer == 8) {
                integers.remove(integer);
                integers.add(1233);
            }
        }


    }


}
