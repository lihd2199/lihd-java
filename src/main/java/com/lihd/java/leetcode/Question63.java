package com.lihd.java.leetcode;

public class Question63 {


    public static void main(String[] args) {

        System.out.println(helper(new int[][]{{0, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}}));

    }


    private static int helper(int[][] obstacleGrid) {

        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0] == null) {
            return 0;
        }

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if (obstacleGrid[m - 1][n - 1] == 1 || obstacleGrid[0][0] == 1) {
            return 0;
        }

        int[][] dp = new int[m][n];

        boolean flag = false;
        for (int i = 0; i < m; i++) {
            if (flag) {
                dp[i][0] = 0;
            } else {
                if (obstacleGrid[i][0] == 0) {
                    dp[i][0] = 1;
                } else {
                    dp[i][0] = 0;
                    flag = true;
                }
            }
        }
        flag = false;
        for (int i = 0; i < n; i++) {
            if (flag) {
                dp[0][i] = 0;
            } else {
                if (obstacleGrid[0][i] == 0) {
                    dp[0][i] = 1;
                } else {
                    dp[0][i] = 0;
                    flag = true;
                }
            }
        }


        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int left = dp[i - 1][j];
                int top = dp[i][j - 1];
                dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : (left + top);
            }
        }


        return dp[m - 1][n - 1];
    }





}
