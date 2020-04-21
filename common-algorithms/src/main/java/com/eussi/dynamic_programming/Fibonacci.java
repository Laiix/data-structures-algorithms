package com.eussi.dynamic_programming;

/**
 * @author wangxueming
 * @create 2020-04-19 13:07
 * @description
 */
public class Fibonacci {
    /**
     * 斐波那契数列的例子严格来说不算动态规划，因为没有涉及求最
     * 值，以上旨在演示算法设计螺旋上升的过程
     */

    public static void fibonacci(int n) {
        //暴力递归
        System.out.println(fib_1(n));
        //带备忘录的递归解法
        System.out.println(fib_2(n));
        //dp数组的迭代解法
        System.out.println(fib_3(n));
        //dp数组的迭代解法，进一步优化
        System.out.println(fib_4(n));
    }

    public static int fib_1(int n) {
        if(n==0) return 0;
        if(n==1 || n==2) return 1;

        return fib_1(n-1) + fib_1(n-2);
    }

    public static int fib_2(int n) {
        if(n<1) return 0;
        int[] memo = new int[n+1];
        return helper(memo, n);
    }

    private static int helper(int[] memo, int n) {
        if(n==1 || n==2) return 1;
        if(memo[n]>0) return memo[n];

        memo[n] = helper(memo, n-1) + helper(memo, n-2);
        return memo[n];
    }

    public static int fib_3(int n) {
        int[] dp = new int[n+1];
        dp[1] = dp[2] = 1;
        for(int i=3; i<=n; i++)
            dp[i] = dp[i-1] + dp[i-2];
        return dp[n];
    }

    public static int fib_4(int n) {
        //当前状态只和之前的两个状态有关，其实并不需要那么⻓的⼀个 DP table 来存储所有的状态
        if(n<1) return 0;
        if (n==2 || n==1) return 1;
        int prev = 1, curr = 1;
        for (int i = 3; i <= n; i++) {
            int sum = prev + curr;
            prev = curr;
            curr = sum;
        }
        return curr;
    }
}
