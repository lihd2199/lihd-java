package com.lihd.java.leetcode;

import java.util.Arrays;

public class Question45 {

    //    [2,3,1,1,4,8,1,2,1,1,1,1]
    public static void main(String[] args) {

        int helper = helper(new int[]{2, 3, 1, 1, 4, 8, 1, 2, 1, 1, 1, 1});

        System.out.println(helper);

    }


    public static int helper(int[] num) {

        int length = num.length;
        int[] dp = new int[length];
        dp[0] = 0;

        for (int i = 0; i < length; i++) {
            int min = -1;
            for (int j = 0; j < i; j++) {
                if (num[j] >= i - j) {
                    int minNow = dp[j] + 1;
                    if (min < 0) {
                        min = minNow;
                    } else {
                        min = Math.min(min,minNow);
                    }
                    dp[i] = min;
                }
            }
        }


        return dp[length-1];
    }


}
