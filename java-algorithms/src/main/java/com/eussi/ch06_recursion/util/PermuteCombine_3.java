package com.eussi.ch06_recursion.util;


import com.eussi.common.StackX;

/**
 * @author wangxueming
 * @create 2020-01-13 14:07
 * @description
 */
public class PermuteCombine_3 {

    private static StackX<Integer> s = new StackX<Integer>(100);

    /**
     * 递归方法，当前已抽取的小球个数与要求抽取小球个数相同时，退出递归
     *      @param curmaxv - 当前已经抓取小球中最大的编号
     *      @param maxv - 待抓取小球中最大的编号
     *      @param curnum - 当前已经抓取的小球数目
     *      @param maxnum - 需要抓取小球的数目
     */
    public static void dfs(int curmaxv, int maxv, int curnum, int maxnum){
        if(curnum == maxnum){
            System.out.println(s);
            return;
        }

        for(int i = curmaxv + 1; i <= maxv; i++){
            s.push(i);
            dfs(i, maxv,curnum + 1, maxnum);
            s.pop();
        }
    }

}
