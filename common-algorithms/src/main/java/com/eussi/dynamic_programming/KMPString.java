package com.eussi.dynamic_programming;

/**
 * @author wangxueming
 * @create 2020-05-08 0:34
 * @description
 */
public class KMPString {
    public static void main(String[] args) {
        //KMP算法
        System.out.println("KMP算法");
        kmpString("aaaabaaabbc", "aaabb");
        kmpString("aaaabaaabbc", "saabb");
    }

    public static void kmpString(String txt, String pat) {
        //暴力解法
        System.out.println(violentSolution(txt, pat));

        //状态机解法
        System.out.println(stateMachine(txt, pat));
    }

    private static int stateMachine(String txt, String pat) {
        int[][] dp = new int[pat.length()][256];

        //构造状态机

        //base case
        dp[0][pat.charAt(0)] = 1;

        int X = 0;//状态

        for(int j=1; j<pat.length(); j++) {
            for (int c = 0; c < 256; c++) {
                if (pat.charAt(j) == c) {
                    dp[j][c] = j + 1;
                } else {
                    dp[j][c] = dp[X][c];
                }
            }

            //更新状态
            X = dp[X][pat.charAt(j)];
        }

        int state = 0;
        for(int i=0; i<txt.length(); i++) {
            state = dp[state][txt.charAt(i)];

            if(state == pat.length())
                return i - state + 1;
        }
        return -1;
    }

    private static int violentSolution(String txt, String pat) {
        for(int i=0; i<=txt.length()-pat.length(); i++) {
            int j;
            for(j=0; j<pat.length(); j++) {
                if(pat.charAt(j)!=txt.charAt(i+j)) {
                    break;
                }
            }
            if(j==pat.length())
                return i;
        }
        return -1;
    }
}
