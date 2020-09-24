package com.lihd.java;

import org.junit.Test;

/**
 * @author: li_hd
 * @date: 2020-09-24 18:02
 **/
public class AppTest {

    @Test
    public void test() {

        final StringBuilder stringBuilder = new StringBuilder();
        handler(53, stringBuilder, 0, 1);

        System.out.println(stringBuilder);

    }


    public void handler(int s, StringBuilder str, int s1, int s2) {

        if (s <= 0) {
            return;
        }

        if (s <= 26) {
            str.append((char) (64 + s));
            return;
        }

        int mo = s % 26;
        str.append((char) (64 + mo));

        s1 = s1 + s2 * 26;
        s2 = s2 * 26;

        if (s > s1) {
            handler(s - s1, str, s1, s2);
        } else if (s == s1) {

        } else {
            handler2(s, str);
        }


    }

    private void handler2(int i, StringBuilder str) {

        if (i <= 26) {
            str.append((char) (64 + i));
            return;
        }


    }

}
