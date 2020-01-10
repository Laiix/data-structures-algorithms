package com.eussi.ch06_recursion.util;

/**
 * @author wangxueming
 * @create 2020-01-10 16:35
 * @description
 */
public class Knapsack {

    private int[] weights; // 可供选择的重量
    private boolean[] selects; // 记录是否被选择

    public Knapsack(int[] weights) {
        this.weights = weights;
        selects = new boolean[weights.length];
    }

    // 找所有可能的解
    // total总重量
    // index可供选择的重量
    public void knapsack(int total, int index) {
        if (total < 0 || total > 0 && index >= weights.length) {// 基值,没找到解
            return;
        }
        if (total == 0) { // 基值，找到解
            for (int i = 0; i < index; i++) {
                if (selects[i] == true) {
                    System.out.print(weights[i] + " ");
                }
            }
            System.out.println();
            return;
        }
        //选择了第一个值的情况
        selects[index] = true;
        knapsack(total - weights[index], index + 1);

        //未选择第一个值的情况
        selects[index] = false;
        knapsack(total, index + 1);
    }

}
