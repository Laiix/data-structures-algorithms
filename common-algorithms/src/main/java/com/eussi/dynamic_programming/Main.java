package com.eussi.dynamic_programming;

/**
 * @author wangxueming
 * @create 2020-04-19 12:28
 * @description
 */
public class Main {
    /**
     * 具体来说，动态规划的一般流程就是三步：
     *      暴力的递归解法 -> 带备忘录的递归解法 -> 迭代的动态规划解法
     *
     * 就思考流程来说，就分为⼀下⼏步：
     *      找到状态和选择 -> 明确 dp 数组/函数的定义 -> 寻找状态之间的关系
     *
     * 重叠子问题、最优子结构、状态转移方程是动态规划三要素
     *
     */
    public static void main(String[] args) {
        //斐波那契数列
        System.out.println("斐波那契数列");
        Fibonacci.fibonacci(20);

        //凑零钱问题
        System.out.println("凑零钱问题");
        MakeTheChange.coinChange(new int[]{1,2,5}, 11);

        //最长递增子序列
        System.out.println("最长递增子序列");
        LongestIncreasingSubsequence.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18});

        //最小编辑距离
        System.out.println("最小编辑距离");
        MinimumEditDistance.MinimumEditDistance("rad", "apple");
        MinimumEditDistance.MinimumEditDistance("intention", "execution");
        MinimumEditDistance.MinimumEditDistance("horse", "ros");

        //青蛙跳台
        System.out.println("青蛙跳台");
        MaximumFrogJumps.maximumFrogJumps(4);

        //动态博弈
        System.out.println("动态博弈");
        Game.game(new int[]{3, 9, 1, 2});

        //区间调度问题
        System.out.println("区间调度问题");
        IntervalScheduling.intervalScheduling(new int[][]{{1, 3}, {2, 4}, {3, 6}});
    }


}
