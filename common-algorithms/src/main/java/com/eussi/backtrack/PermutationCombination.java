package com.eussi.backtrack;

import java.util.*;

/**
 * @author wangxueming
 * @create 2020-05-14 22:07
 * @description
 */
public class PermutationCombination {
    public static void main(String[] args) {
        System.out.println("排列组合：");
        PermutationCombination.permutationCombination(new int[]{1, 2, 3, 4}, 2);
    }


    //回溯法实现排列组合
    public static void permutationCombination(int[] nums, int select) {
        LinkedList<Integer> res = new LinkedList<Integer>();
        //抓了不放回去
        catchBall(nums, select, res);
        System.out.println("\n---------\n");
        //全排列
        permutation(nums, select, res);
        System.out.println("\n---------\n");
        //组合
        combination(nums, select, res, 0);

        System.out.println("\n---------\n");


        //银行凑钱
        /**
         * 面额 100， 50， 20， 10， 5 ，0.5， 0.1， 凑出x元
         */
        Set<Integer> repetion = new HashSet<Integer>();
        makeChange(new int[]{100, 50, 20, 10, 5}, 20, res, repetion);

        makeChange_2(new int[]{100, 50, 20, 10, 5}, 20, res, 0);

    }



    private static void makeChange_2(int[] nums, int x, LinkedList<Integer> res, int n) {

        if(x==0) {
            System.out.println(res);
            return;
        }
        if(x<0)//无解
            return;

        for(int i=n; i< nums.length; i++) {//n代表当前选择的值,还可以继续选择，接近于组合，但是又不是组合

            res.add(nums[i]);

            makeChange_2(nums, x-nums[i], res, i);

            res.removeLast();

        }
    }

    private static void makeChange(int[] nums, int x, LinkedList<Integer> res, Set<Integer> repetion) {
        if(x==0) {
            if(!repetion.contains(res.size()))  {//因为钱这块特殊性，所以这样去重，但是比如包含面值，3,7,4,6这样去重就会出现问题
                System.out.println(res);
                repetion.add(res.size());
            }
            return;
        }
        if(x<0)//无解
            return;

        for(int i=0; i< nums.length; i++) {
//            if(res.contains(nums[i]))
//                continue;

            res.add(nums[i]);

            makeChange(nums, x-nums[i], res, repetion);

            res.removeLast();

        }
    }


    private static void combination(int[] nums, int select, LinkedList<Integer> res, int n) {
        if(select==0) {
            System.out.println(res);
            return;
        }

        for(int i=n; i< nums.length; i++) {
//            if(res.contains(nums[i]))
//                continue;

            res.add(nums[i]);

            combination(nums, select-1, res, i+1);

            res.removeLast();

        }
    }

    private static void permutation(int[] nums, int select, LinkedList<Integer> res) {
        if(select==0) {
            System.out.println(res);
            return;
        }

        for(int i=0; i< nums.length; i++) {
            if(res.contains(nums[i]))
                continue;

            res.add(nums[i]);

            permutation(nums, select-1, res);

            res.removeLast();

        }

    }

    private static void catchBall(int[] nums, int select, LinkedList<Integer> res) {
        if(select==0) {
            System.out.println(res);
            return;
        }

        for(int i=0; i< nums.length; i++) {
            res.add(nums[i]);

            catchBall(nums, select-1, res);

            res.removeLast();

        }
    }

}
