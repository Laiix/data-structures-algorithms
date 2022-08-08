package com.eussi.dynamic_programming;

import java.util.Arrays;

/**
 * @author wangxueming
 * @create 2020-04-19 13:10
 * @description leetcode 322
 */
public class MakeTheChange {
    public static void main(String[] args) {
        //凑零钱问题
        System.out.println("凑零钱问题");
        coinChange(new int[]{1, 2, 5}, 11);
    }
    /**
     * 给你 k 种面值的硬币，面值分别为 c1, c2 ... ck ，每种硬
     * 币的数量无限，再给⼀个总金额 amount ，问你最少需要几枚硬币凑出这个
     * 金额，如果不可能凑出，算法返回 -1
     */

    // coins 中是可选硬币面值，amount 是目标金额
    public static void coinChange(int[] coins, int amount) {
        //暴力递归
        System.out.println(dp_1(coins, amount));
        //带备忘录的递归
        System.out.println(dp_2(coins, amount));
        //迭代解法
        System.out.println(dp_3(coins, amount));
        //leet优化解法
        System.out.println(dp_4(coins, amount));
    }

    public static int dp_1(int[] coins, int amount) {
        if(amount==0) {
            return 0;
        } else if(amount==-1) {
            return -1; //无解
        }

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub;
            if(amount-coin<0 || (sub = dp_1(coins, amount-coin))==-1) {
                continue;
            }
            res = Math.min(res, 1+sub);
        }
        return res==Integer.MAX_VALUE?-1:res;
    }

    public static int dp_2(int[] coins, int amount) {
        int[] memo = new int[amount+1];
        return helper(coins, amount, memo);
    }


    public static int helper(int[] coins, int amount, int[] memo) {
        if (amount == 0) {
            return 0;
        } else if (amount == -1) {
            return -1; //无解
        }

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub;
            if (amount - coin < 0) {
                continue;
            }
            if (memo[amount-coin]>0) {
                sub = memo[amount-coin];
            } else {
                sub = helper(coins, amount-coin, memo);
                memo[amount-coin] = sub;
            }
            if(sub==-1) {
                continue;
            }
            res = Math.min(res, 1+sub);
        }
        return res==Integer.MAX_VALUE?-1:res;
    }


    //dp[i]=x 表示，当目前金额为i时，
    public static int dp_3(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        dp[0] = 0;
        for(int i=1; i<dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                if(i-coin<0 || dp[i-coin]==-1) {
                    continue;
                }
                dp[i] = Math.min(dp[i], 1+dp[i-coin]);
            }
            if(dp[i]==Integer.MAX_VALUE) {
                dp[i] = -1;
            }
        }
        return dp[amount];
    }

    public static int dp_4(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for(int coin:coins){
            for(int i=coin;i<=amount;i++){
                dp[i] = Math.min(dp[i-coin]+1, dp[i]);
            }
        }
        return dp[amount] == amount+1?-1:dp[amount];
    }
}
