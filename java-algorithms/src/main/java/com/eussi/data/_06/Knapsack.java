package com.eussi.data._06;

import com.eussi.data._04.StackX;

import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * @author wangxueming
 * @create 2020-01-10 16:35
 * @description 背包问题
 */
public class Knapsack {

    private int[] weights; // 可供选择的重量
    private boolean[] selects; // 记录是否被选择
    private StackX<Integer> stack = new StackX<>(100);

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
                if (selects[i]) {
                    print(weights[i] + " ");
                }
            }
            println();
            return;
        }
        //选择了第一个值的情况
        selects[index] = true;
        knapsack(total - weights[index], index + 1);

        //未选择第一个值的情况
        selects[index] = false;
        knapsack(total, index + 1);
    }

    public void knapsackDfs(int target, int index) {
        if (target == 0) {
            println(stack);
            return;
        }
        for (int i = index; i < weights.length; i++) {
            stack.push(weights[i]);
            knapsackDfs(target - weights[i], i + 1);
            stack.pop();
        }
    }

}
