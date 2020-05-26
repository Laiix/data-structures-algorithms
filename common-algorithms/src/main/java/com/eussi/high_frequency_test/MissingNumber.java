package com.eussi.high_frequency_test;

public class MissingNumber {
    public static void main(String[] args) {
        //给定一个包含0,1,2,3,...,n中n个数的序列，找出0..n中没有出现在序列中的那个数
        System.out.println(missingNumber(new int[]{3, 0, 1}));

        System.out.println(missingNumber_2(new int[]{3, 0, 1}));

        System.out.println(missingNumber_3(new int[]{3, 0, 1}));
    }

    private static int missingNumber(int[] nums) {
        int n = nums.length;
        int res = 0;
        // 先和新补的索引异或⼀下
        res ^= n;
        // 和其他的元素、索引做异或
        for (int i = 0; i < n; i++)
            res ^= i ^ nums[i];
        return res;
    }

    private static int missingNumber_2(int[] nums) {
        int n = nums.length;
        // 公式：(⾸项 + 末项) * 项数 / 2
        int expect = (0 + n) * (n + 1) / 2;
        int sum = 0;
        for (int x : nums)//sum可能会溢出
            sum += x;
        return expect - sum;
    }

    private static int missingNumber_3(int[] nums) {
        int n = nums.length;
        int res = 0;
        // 新补的索引
        res += n - 0;
        // 剩下索引和元素的差加起来
        for (int i = 0; i < n; i++)
            res += i - nums[i];
        return res;
    }
}
