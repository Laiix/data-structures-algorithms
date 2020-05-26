package com.eussi.dynamic_programming;

public class LongestCommonSub {
    public static void main(String[] args) {

        //"abced" "ace" 最长公共子序列是"ace", 他的长度是3
        String s1 = "abcde";
        String s2 = "ace";
        System.out.println(lcs_1(s1, s2, s1.length()-1, s2.length()-1));

        System.out.println(lcs_2(s1, s2, s1.length()-1, s2.length()-1));

        System.out.println(lcs_3(s1, s2, s1.length(), s2.length()));
    }

    public static int lcs_1(String s1, String s2, int i, int j) {
        if(i==-1 || j==-1) return 0;
        if(s1.charAt(i) == s2.charAt(j)) {
            return lcs_1(s1, s2, i-1, j-1) + 1;
        } else {
            return Math.max(lcs_1(s1, s2, i-1, j), lcs_1(s1, s2, i, j-1));
        }

    }

    public static int lcs_2(String s1, String s2, int i, int j) {
        if(i==-1 || j==-1) return 0;
        int[][] memo = new int[i+1][j+1];

        return helper(s1, s2, i, j, memo);
    }

    public static int helper(String s1, String s2, int i, int j, int[][] memo) {
        if(memo[i][j]!=0)
            return memo[i][j];

        if(s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = lcs_1(s1, s2, i-1, j-1) + 1;
        } else {
            memo[i][j] = Math.max(lcs_1(s1, s2, i-1, j), lcs_1(s1, s2, i, j-1));
        }

        return memo[i][j];
    }

    //dp[i][j] 的含义是：对于 s1[1..i] 和 s2[1..j] ，
    //它们的 LCS 长度是 dp[i][j]
    public static int lcs_3(String s1, String s2, int m, int n) {
        int[][] dp = new int[m+1][n+1];

        for(int i=0; i<dp.length; i++) {
            dp[i][0] = 0;
        }
        for(int j=0; j<dp[0].length; j++) {
            dp[0][j] = 0;
        }

        for(int i=1; i<dp.length; i++) {
            for(int j=1; j<dp[0].length; j++) {
                if(s1.charAt(i-1)==s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        return dp[s1.length()][s2.length()];

    }
}
