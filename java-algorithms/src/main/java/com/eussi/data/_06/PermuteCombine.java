package com.eussi.data._06;


import com.eussi.data._04.StackX;

import static com.eussi.util.PrintUtil.println;

/**
 * @author wangxueming
 * @create 2020-01-13 14:07
 * @description
 */
public class PermuteCombine {

    private static StackX<Integer> stack = new StackX<>(100);
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
            println(stack);
            return;
        }

        for(int i = minv; i <= maxv; i++){
            stack.push(i);
            dfs(minv, maxv, curnum+1, maxnum);
            stack.pop();
        }
    }

    /**
     * 递归方法，当实际选取的小球数目与要求选取的小球数目相同时，跳出递归
     * @param minv - 小球编号的最小值
     * @param maxv - 小球编号的最大值
     * @param curnum - 当前已经确定的小球的个数
     * @param maxnum - 要选取的小球的数目
     */
    public static void dfs2(int minv,int maxv,int curnum, int maxnum){
        if(curnum == maxnum){
            println(stack);
            return;
        }

        for(int i = minv; i <= maxv; i++){
            if(!used[i]){       //起到了剪枝的作用，出现过的数据不能在出现
                stack.push(i);
                used[i] = true;
                dfs2(minv, maxv, curnum+1, maxnum);
                stack.pop();
                used[i] = false;
            }
        }

    }

    /**
     * 递归方法，当前已抽取的小球个数与要求抽取小球个数相同时，退出递归
     *      @param curmaxv - 当前已经抓取小球中最大的编号
     *      @param maxv - 待抓取小球中最大的编号
     *      @param curnum - 当前已经抓取的小球数目
     *      @param maxnum - 需要抓取小球的数目
     */
    public static void dfs3(int curmaxv, int maxv, int curnum, int maxnum){
        if(curnum == maxnum){
            println(stack);
            return;
        }

        for(int i = curmaxv + 1; i <= maxv; i++){
            stack.push(i);
            dfs3(i, maxv,curnum + 1, maxnum);
            stack.pop();
        }
    }

}
