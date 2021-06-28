package com.lihd.java.leetcode;

public class Question5 {

    public static void main(String[] args) {

        System.out.println(helper("abcba"));

    }

    /**
     * abcbbcbcbcbbc  只能往外扩 不能往里缩
     */

    private static String helper(String str) {

        int len = str.length();
        if (len < 2) {
            return str;
        }

        int maxLen = 1;
        int begin = 0;
        boolean[][] dp = new boolean[len][len];
        char[] chars = str.toCharArray();

        //先固定右边 这样的话 dp不会超出
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                if (i - j < 2) {
                    dp[j][i] = chars[i] == chars[j];
                } else {
                    dp[j][i] = chars[i] == chars[j] && dp[j + 1][i - 1];
                }

                if (dp[j][i] && (i - j + 1) > maxLen) {
                    // 如果 [i, j] 是回文子串，并且长度大于 max，则刷新最长回文子串
                    maxLen = i - j + 1;
                    begin = j;
                }

            }
        }


        return str.substring(begin, begin + maxLen);

    }

}
