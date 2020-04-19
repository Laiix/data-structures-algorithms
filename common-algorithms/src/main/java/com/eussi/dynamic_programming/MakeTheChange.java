package com.eussi.dynamic_programming;

/**
 * @author wangxueming
 * @create 2020-04-19 13:10
 * @description
 */
public class MakeTheChange {
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
    }

    private static int dp_1(int[] coins, int amount) {
        if(amount==0) return 0;
        if(amount<0) return -1;

        int res = Integer.MAX_VALUE;
        for(int i=0; i<coins.length; i++) {
            int subProblem = dp_1(coins, amount-coins[i]);
            if(subProblem<0) continue;

            if(res>subProblem+1)
                res = subProblem+1;
        }
        return res;
    }

    private static int dp_2(int[] coins, int amount) {
        if(amount==0) return 0;
        if(amount<0) return -1;

        int res = Integer.MAX_VALUE;
        int[] memo = new int[amount+1];
        for(int i=0; i<coins.length; i++) {
            int subProblem = -1;
            if(memo[amount-coins[i]]>0)
                subProblem = memo[amount-coins[i]];
            else {
                subProblem = dp_1(coins, amount-coins[i]);
                memo[amount-coins[i]] = subProblem;
            }
            if(subProblem<0) continue;
            if(res>subProblem+1)
                res = subProblem+1;
        }
        return res;
    }

    private static int dp_3(int[] coins, int amount) {
        int[] dp = new int[amount+1];

        for(int i=0; i<dp.length; i++) {

            dp[i] = i==0?0:Integer.MAX_VALUE;
            // 内层 for 在求所有子问题 +1 的最小值
            for(int j=0; j<coins.length; j++) {
                //子问题无解
                if(i-coins[j]<0) continue;

                if(dp[i] > 1+dp[i-coins[j]])
                    dp[i] = 1+dp[i-coins[j]];
            }
        }
        return dp[amount]==Integer.MAX_VALUE?-1:dp[amount];
    }
}
