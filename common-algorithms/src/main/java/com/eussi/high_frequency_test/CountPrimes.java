package com.eussi.high_frequency_test;

import java.util.Arrays;

/**
 * @author wangxueming
 * @create 2020-05-11 0:46
 * @description
 */
public class CountPrimes {
    public static void countPrimes(int n) {
        //效率较低
        System.out.println(getPrimes_1(n));

        System.out.println(getPrimes_2(n));
    }

    //返回[2, n)中有几个素数
    private static int getPrimes_1(int n) {
        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrime_2(i))
                count++;
        return count;
    }
    // 判断整数 n 是否是素数
    private static boolean isPrime_1(int n) {
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                // 有其他整除因⼦
                return false;
        return true;
    }

    /**
     * 12 = 2 × 6
     * 12 = 3 × 4
     * 12 = sqrt(12) × sqrt(12)
     * 12 = 4 × 3
     * 12 = 6 × 2
     *
     * 临界点 sqrt(n) * sqrt(n)
     */
    private static boolean isPrime_2(int n) {
        for (int i = 2; i*i <= n; i++)
            if (n % i == 0)
                // 有其他整除因⼦
                return false;
        return true;
    }

    private static int getPrimes_2(int n) {
        boolean[] isPrim = new boolean[n];
        // 将数组都初始化为 true
        Arrays.fill(isPrim, true);
//        for (int i = 2; i < n; i++)
        for (int i = 2; i*i <= n; i++)//优化1
            if (isPrim[i])
                // i 的倍数不可能是素数了
//                for (int j = 2 * i; j < n; j += i)
                for (int j = i * i; j < n; j += i)//优化2
                    isPrim[j] = false;
        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrim[i]) count++;
        return count;
    }
}
