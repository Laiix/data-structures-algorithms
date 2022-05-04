package com.eussi.ch06_recursion.util;


import com.eussi.data._04.StackX;

/**
 * @author wangxueming
 * @create 2020-01-13 14:07
 * @description
 */
public class PermuteCombine_2 {
    private static StackX<Integer> s = new StackX<Integer>(100);
    private static boolean[] used = new boolean[100];

    /**
     * 递归方法，当实际选取的小球数目与要求选取的小球数目相同时，跳出递归
     * @param minv - 小球编号的最小值
     * @param maxv - 小球编号的最大值
     * @param curnum - 当前已经确定的小球的个数
     * @param maxnum - 要选取的小球的数目
     */
    public static void dfs(int minv,int maxv,int curnum, int maxnum){
        if(curnum == maxnum){
            System.out.println(s);
            return;
        }

        for(int i = minv; i <= maxv; i++){
            if(!used[i]){       //起到了剪枝的作用，出现过的数据不能在出现
                s.push(i);
                used[i] = true;
                dfs(minv, maxv, curnum+1, maxnum);
                s.pop();
                used[i] = false;
            }
        }

    }

}
