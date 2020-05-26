package com.eussi.algorithm_thinking;

import java.util.LinkedList;
import java.util.List;

/**
 * @author wangxueming
 * @create 2020-05-24 21:50
 * @description
 */
public class CakeSort {
    public static void main(String[] args) {
        //烧饼排序
        System.out.println(pancakeSort(new int[]{3, 2, 4, 1}));
    }

    // 记录反转操作序列
    static LinkedList<Integer> res = new LinkedList<Integer>();

    static List<Integer> pancakeSort(int[] cakes) {
        sort(cakes, cakes.length);
        return res;
    }

    static void sort(int[] cakes, int n) {
        // base case
        if (n == 1) return;
        // 寻找最⼤饼的索引
        int maxCake = 0;
        int maxCakeIndex = 0;
        for (int i = 0; i < n; i++)
            if (cakes[i] > maxCake) {
                maxCakeIndex = i;
                maxCake = cakes[i];
            }

        // 第⼀次翻转，将最大饼翻到最上⾯
        reverse(cakes, 0, maxCakeIndex);
        res.add(maxCakeIndex + 1);
        // 第⼆次翻转，将最⼤饼翻到最下⾯
        reverse(cakes, 0, n - 1);
        res.add(n);
        // 递归调⽤
        sort(cakes, n - 1);
    }

    static void reverse(int[] arr, int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++; j--;
        }
    }
}
