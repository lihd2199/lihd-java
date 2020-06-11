package com.lihd.java;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class App {


    public static void main(String[] args) {

        final App app = new App();
        int a = 1;
        Double b = 1.0;
        List<Integer> c = new ArrayList<>();
        String d = "1";
        final Inner e = new Inner();
        e.name = "1";
        int[] f = new int[2];
        AtomicInteger g = new AtomicInteger(0);

        app.test(a, b, c, d, e, f, g);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c.size());
        System.out.println(d);
        System.out.println(e.name);
        System.out.println(f[0]);
        System.out.println(g.get());
    }


    public void test(int a, Double b, List<Integer> c, String d, Inner e, int[] f, AtomicInteger g) {
        a = 2;
        b = 2.0;
        c.add(2);
        d = "2";
        e.name = "2";
        f[0] = 1;
        g.getAndIncrement();
    }

    static class Inner {
        private String name;

        public String getName() {
            return name;
        }
    }


}
