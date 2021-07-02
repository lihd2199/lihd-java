package com.lihd.java.leetcode;

public class Question70 {


    public static void main(String[] args) {

        System.out.println(helper(4));

    }

    private static int helper(int n) {

        int[] dp = new int[n + 1];

        if (n <= 2) {
            return n;
        }

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        return dp[n];
    }


}
