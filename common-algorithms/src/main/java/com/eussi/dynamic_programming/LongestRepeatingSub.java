package com.eussi.dynamic_programming;


public class LongestRepeatingSub {
    public static void main(String[] args) {
        //最长重复字符长度
        String s = "abbbccddeeffffffg";
        System.out.println(lrs(s));
        System.out.println(lrs_2(s));
        System.out.println(lrs_3(s));
        System.out.println(lrs_4(s));
    }

    private static int lrs_4(String s) {


        int[] dp = new int[s.length()];

        helper(s, dp.length-1, dp);

        int res = Integer.MIN_VALUE;
        for(int i=0; i<dp.length; i++) {
            if(res<dp[i])
                res = dp[i];
        }
        return res;

    }

    private static void helper(String s, int n, int[] dp) {
        if(n==0) {
            dp[0] = 1;
            return;
        }
        helper(s, n-1, dp);
        if(s.charAt(n)==s.charAt(n-1))
            dp[n] = dp[n-1] + 1;
        else
            dp[n] = 1;
    }

    private static String lrs_3(String s) {
        int[] dp = new int[s.length()];
        dp[0]=1;
        for(int i=1; i<dp.length; i++) {

            if(s.charAt(i)==s.charAt(i-1))
                dp[i] = dp[i-1] + 1;
            else
                dp[i] = 1;
        }

        int res = 0;
        for(int i=1; i<dp.length; i++) {
            if(dp[res]<dp[i])
                res = i;
        }
        return s.substring(res-dp[res]+1, res+1);
    }

    private static String lrs_2(String s) {
        int[][] dp = new int[s.length()][2];
        dp[0][0] = 1;
        dp[0][1] = 0;

        for(int i=1; i<dp.length; i++) {
            if(s.charAt(i)==s.charAt(i-1)) {
                dp[i][0] = dp[i-1][0] + 1;
                dp[i][1] = dp[i-1][1];
            } else {
                dp[i][0] = 1;
                dp[i][1] = i;
            }

        }

        int res = 0;
        for(int i=1; i<dp.length; i++) {
            if(dp[res][0]<dp[i][0])
                res = i;
        }
        return s.substring(dp[res][1], res + 1);
    }

    private static int lrs(String s) {
        int[] dp = new int[s.length()];
        dp[0]=1;
        for(int i=1; i<dp.length; i++) {

            if(s.charAt(i)==s.charAt(i-1))
                dp[i] = dp[i-1] + 1;
            else
                dp[i] = 1;
        }

        int res = Integer.MIN_VALUE;
        for(int i=0; i<dp.length; i++) {
            if(res<dp[i])
                res = dp[i];
        }
        return res;
    }
}
