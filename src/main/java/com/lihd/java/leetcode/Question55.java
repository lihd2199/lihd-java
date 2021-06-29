package com.lihd.java.leetcode;

public class Question55 {

    public static void main(String[] args) {
        System.out.println(helper(new int[]{3, 2, 1, 0, 1, 6}));
    }


    public static boolean helper(int[] nums) {

        int length = nums.length;
        boolean[] dp = new boolean[length];
        dp[0] = true;

        for (int i = 1; i < length; i++) {
            for (int j = 0; j <= i; j++) {
                dp[i] = dp[j] && (j + nums[j]) >= i;
                if (dp[i]) {
                    break;
                }
            }
        }

        return dp[length - 1];

    }

    public static boolean helper2(int[] nums) {

        int length = nums.length;
        int maxRight = 0;

        for (int i = 0; i < length; i++) {
            if (i <= maxRight) {
                maxRight = Math.max(maxRight, i + nums[i]);
                if (maxRight >= length - 1) {
                    return true;
                }
            }

        }

        return false;

    }


}
