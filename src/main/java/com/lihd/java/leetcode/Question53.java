package com.lihd.java.leetcode;

public class Question53 {


    public static void main(String[] args) {


        System.out.println(helper(new int[]{1, 2, 3, 4, -5, -6}));


    }


    public static int helper(int[] nums) {

        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

}
