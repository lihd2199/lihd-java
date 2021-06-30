package com.lihd.java.leetcode;

public class Question53 {


    public static void main(String[] args) {

        System.out.println(helper(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }


    public static int helper(int[] nums) {

        int sum = 0;
        int max = nums[0];
        for (int num : nums) {
            sum = Math.max(sum + num, num);
            max = Math.max(max, sum);
        }
        return max;
    }

}
