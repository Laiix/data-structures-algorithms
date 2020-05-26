package com.eussi.dynamic_programming;

public class Bags {
    public static void main(String[] args) {
        //背包容量w，有N物品，第i个重量w[i]价值v[i]
        System.out.println(pack(4, new int[]{2, 1, 3}, new int[]{4, 2, 3}));

        System.out.println(pack_1(4, new int[]{2, 1, 3}, new int[]{4, 2, 3}, 3));
    }

    private static int pack_1(int target, int[] w, int[] v, int n) {//对于第n个物品，当前背包重量为target,这种情况下最大价值
        if(target==0 || n==0) {//base
            return 0;
        }
        if(target-w[n-1] < 0) {//当前背包装不下，只能选择不装入背包
            return pack_1(target, w, v, n-1);
        }
        return Math.max(pack_1(target, w, v, n-1), pack_1(target-w[n-1], w, v, n-1) + v[n-1]);
    }

    //dp[n][target] 物品为n时，重量为target的最大价值
    private static int pack(int target, int[] w, int[] v) {
        int[][] dp = new int[w.length+1][target+1];
        for(int i=0; i<dp.length; i++) {
            dp[i][0] = 0;
        }

        for(int i=0; i<dp[0].length; i++) {
            dp[0][i] = 0;
        }


        for(int i=1; i<=w.length; i++) {
            for(int j=1; j<=target; j++) {
                if(j-w[i-1]<0) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(
                            dp[i-1][j],
                            dp[i-1][j-w[i-1]] + v[i-1]);
                }
            }
        }

        return dp[w.length][target];
    }
}
