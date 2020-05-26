package com.eussi.high_frequency_test;

public class OrderedArraysRemoveDuplicates {
    public static void main(String[] args) {
        //要求不能使用辅助数组
        System.out.println(removeDuplicated(new int[]{0,0,1,1,1,2,2,3,3,4}));
    }

    //链表操作与下面数组操作类似，最后把slow的next节点置空即可
    private static int removeDuplicated(int[] nums) {
        int n = nums.length;
        if(n==0)return 0;
        int slow = 0, fast = 1;

        while(fast<n) {
            if(nums[fast] != nums[slow]) {
                slow ++;
                nums[slow] = nums[fast];
            }
            fast ++;
        }

        return slow + 1;
    }


}
