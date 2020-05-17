package com.eussi.dynamic_programming;

import java.util.Arrays;

/**
 * @author wangxueming
 * @create 2020-04-19 17:06
 * @description
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        //最长递增子序列
        System.out.println("最长递增子序列");
        lengthOfLIS(new int[]{10,9,2,5,3,7,101,18});
    }
    /**
     * 给定一个无序的整数数组，找出其中最长上升子序列的长度
     * 实例：
     *      输入: [10,9,2,5,3,7,101,18]
     *      输出: 4
     *      解释：最长的上升子序列是 [2,3,7,101], 它的长度是4。
     * 说明:
     *      可能会存在多种最长上升子序列的组合，你只需要输出对应的长度即可
     *      你算法的时间复杂度应该为O(n^2)
     * 进阶：
     *      你能将算法的时间复杂度降低到O(nlogn)吗？
     */

    public static void lengthOfLIS(int[] nums) {
        //动态规划
        System.out.println(lis_1(nums));

        //二分法实现，思维拓展即可，基本想不到
        System.out.println(lis_2(nums));
    }

    public static int lis_1(int[] nums) {
        int[] dp = new int[nums.length];
        //数组全部序列化为1
        Arrays.fill(dp, 1);

        for(int i=0; i<nums.length; i++) {
            for(int j=0; j<i; j++) {
                if(nums[i] > nums[j]) {
                    if(dp[i] < dp[j] + 1)
                        dp[i] = dp[j] +1;
                }
            }
        }

        int res=Integer.MIN_VALUE;
        for(int i=0; i<dp.length; i++) {
            if(dp[i]>res)
                res = dp[i];
        }
        return res;
    }

    public static int lis_2(int[] nums) {
        int[] top = new int[nums.length];
        // 牌堆数初始化为 0
        int piles = 0;
        for (int i = 0; i < nums.length; i++) {
            // 要处理的扑克牌
            int poker = nums[i];

            /***** 搜索左侧边界的二分查找 *****/
            int left = 0, right = piles;
            while (left < right) {
                int mid = (left + right) / 2;
                if (top[mid] > poker) {
                    right = mid;
                } else if (top[mid] < poker) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            /*********************************/

            // 没找到合适的牌堆，新建一堆
            if (left == piles) piles++;
            // 把这张牌放到牌堆顶
            top[left] = poker;
        }
        // 牌堆数就是 LIS 长度
        return piles;
    }
}
