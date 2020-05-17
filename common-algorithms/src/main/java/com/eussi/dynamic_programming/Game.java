package com.eussi.dynamic_programming;

/**
 * @author wangxueming
 * @create 2020-04-27 22:23
 * @description
 */
public class Game {
    public static void main(String[] args) {
        //动态博弈
        System.out.println("动态博弈");
        game(new int[]{3, 9, 1, 2});
    }
    /**
     * 你和你的朋友面前有一排石头堆，⽤一个数组 piles 表示，piles[i] 表示第 i
     * 堆石子有多少个。你们轮流拿石头，一次拿一堆，但是只能拿走最左边或者
     * 最右边的石头堆。所有石头被拿完后，谁拥有的石头多，谁获胜。
     */
    public static void game(int[] piles) {
        System.out.println(stoneGame(piles));
    }

    private static int stoneGame(int[] piles) {
        int n = piles.length;

        // 初始化 dp 数组
        Pair[][] dp = new Pair[n][n];
        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++)
                dp[i][j] = new Pair(0, 0);

        // 填⼊ base case
        for (int i = 0; i < n; i++) {
            dp[i][i].fir = piles[i];
            dp[i][i].sec = 0;
        }

        // 斜着遍历数组
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = l + i - 1;
                // 先⼿选择最左边或最右边的分数
                int left = piles[i] + dp[i+1][j].sec;
                int right = piles[j] + dp[i][j-1].sec;
                // 套⽤状态转移⽅程
                if (left > right) {
                    dp[i][j].fir = left;
                    dp[i][j].sec = dp[i+1][j].fir;
                } else {
                    dp[i][j].fir = right;
                    dp[i][j].sec = dp[i][j-1].fir;
                }
            }
        }
        Pair res = dp[0][n-1];
        return res.fir - res.sec;
    }

    private static class Pair {
        private int fir;
        private int sec;

        public Pair(int fir, int sec) {
            this.fir = fir;
            this.sec = sec;
        }
    }
}
