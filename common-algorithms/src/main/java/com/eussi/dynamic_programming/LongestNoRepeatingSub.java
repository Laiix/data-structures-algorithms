package com.eussi.dynamic_programming;

public class LongestNoRepeatingSub {
    public static void main(String[] args) {
        //最长不重复字符串
        String s = "ufdbdafeidlj";
        System.out.println(lnrs(s));
        System.out.println(lnrs_2(s));
        System.out.println(lnrs_3(s));
    }

    private static int lnrs_3(String s) {
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

        char ci = s.charAt(n);
        String si = s.substring(n-1-dp[n-1]+1, n-1+1);
        if(si.contains(ci+"")) {
            int index = si.indexOf(ci);
            dp[n] = dp[n-1] - (index+1) + 1;

        } else {
            dp[n] = dp[n-1] + 1;
        }
    }

    private static String lnrs_2(String s) {
        int[] dp = new int[s.length()];
        dp[0]=1;
        for(int i=1; i<dp.length; i++) {
            //查看上一个有没有包含重复字符
            char ci = s.charAt(i);
            String si = s.substring(i-1-dp[i-1]+1, i-1+1);
            if(si.contains(ci+"")) {
                int index = si.indexOf(ci);
                dp[i] = dp[i-1] - (index+1) + 1;

            } else {
                dp[i] = dp[i-1] + 1;
            }
        }

        int res = 0;
        for(int i=1; i<dp.length; i++) {
            if(dp[res]<dp[i])
                res = i;
        }
        return s.substring(res-dp[res]+1, res+1);
    }


    private static int lnrs(String s) {
        int[] dp = new int[s.length()];
        dp[0]=1;
        for(int i=1; i<dp.length; i++) {
            //查看上一个有没有包含重复字符
            char ci = s.charAt(i);
            String si = s.substring(i-1-dp[i-1]+1, i-1+1);
            if(si.contains(ci+"")) {
                int index = si.indexOf(ci);
                dp[i] = dp[i-1] - (index+1) + 1;

            } else {
                dp[i] = dp[i-1] + 1;
            }
        }

        int res = Integer.MIN_VALUE;
        for(int i=0; i<dp.length; i++) {
            if(res<dp[i])
                res = dp[i];
        }
        return res;
    }
}
