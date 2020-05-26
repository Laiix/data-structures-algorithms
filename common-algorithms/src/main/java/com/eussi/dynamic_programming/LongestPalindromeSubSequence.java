package com.eussi.dynamic_programming;

public class LongestPalindromeSubSequence {
    public static void main(String[] args) {

        //最长回文子序列
        System.out.println(longestPalindrome("cabedcbrac"));
        System.out.println(longestPalindrome_2("cabedcbrac"));
    }

    private static int longestPalindrome_2(String s) {
        return helper(s, 0, s.length()-1);
    }

    private static int helper(String s, int r, int l) {
        if(l<r) return 0;
        if(l==r) return 1;

        if(s.charAt(r) == s.charAt(l)) {
            return helper(s, r+1, l-1) + 2;
        } else {
            return Math.max(helper(s, r+1, l), helper(s, r, l-1));
        }
    }

    //dp 数组的定义是：在字串 s[i..j] 中，最长回文子序列
    //的⻓度为 dp[i][j]
    public static int longestPalindrome(String s) {
        int[][] dp = new int[s.length()][s.length()];

//        for(int i=0; i<dp.length; i++) {
//            for(int j=0; j<=i; j++) {
//                if(i==j)
//                    dp[i][j] = 1;
//                else
//                    dp[i][j] = 0;
//            }
//        }
        //默认已经为0
        for(int i=0; i<dp.length; i++)
            dp[i][i] = 1;

        for(int i=dp.length-1; i>=0; i--) {
            for(int j=i+1; j<dp[i].length; j++) {
                if(s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i+1][j-1] + 2;
                else
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
            }
        }
        return dp[0][s.length()-1];
    }


}
