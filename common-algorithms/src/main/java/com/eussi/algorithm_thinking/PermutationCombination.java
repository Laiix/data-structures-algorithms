package com.eussi.algorithm_thinking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wangxueming
 * @create 2020-05-14 22:07
 * @description
 */
public class PermutationCombination {

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



    }

    private static void combination(int[] nums, int select, LinkedList<Integer> res, int n) {
        if(select==0) {
            System.out.println(res);
            return;
        }

        for(int i=n; i< nums.length; i++) {
            if(res.contains(nums[i]))
                continue;

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
