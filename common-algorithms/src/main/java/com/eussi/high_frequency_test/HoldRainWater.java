package com.eussi.high_frequency_test;

public class HoldRainWater {
    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3 ,2 ,1 ,2 ,1};
        System.out.println(trap(height));

        //备忘录 时间O(n) 空间O(n)
        System.out.println(trap_2(height));

        //双指针 时间O(n) 空间O(1)
        System.out.println(trap_3(height));
    }

    private static int trap_3(int[] height) {
        int n = height.length;
        int left = 0, right = n-1;

        int ans = 0;

        int l_max = height[0];
        int r_max = height[n-1];

        while(left<=right) {
            l_max = Math.max(l_max, height[left]);
            r_max = Math.max(r_max, height[right]);

            if(l_max<r_max) {
                ans += l_max -height[left];
                left++;
            } else {
                ans += r_max - height[right];
                right--;
            }

        }

        return ans;

    }

    private static int trap_2(int[] height) {
        int[] l_max = new int[height.length];
        int[] r_max = new int[height.length];

        l_max[0] = height[0];
        r_max[height.length-1] = height[height.length-1];

        //从左到右计算l_max
        for(int i=1; i<height.length; i++) {
            l_max[i] = Math.max(height[i], l_max[i-1]);
        }

        //从右向左计算r_max
        for(int i=height.length-2; i>=0; i--) {
            r_max[i] = Math.max(height[i], r_max[i+1]);
        }


        int sum = 0;
        for(int i=1; i<height.length-1; i++) {
            sum += Math.min(l_max[i], r_max[i])-height[i];
        }
        return sum;

    }

    private static int trap(int[] height) {
        int sum = 0;
        for(int i=1; i<height.length-1; i++) {
            sum += Math.min(getMax(height, 0, i), getMax(height, i, height.length-1))-height[i];
        }
        return sum;

    }

    private static int getMax(int[] height, int low, int high) {
        int max = Integer.MIN_VALUE;
        for(int i=low; i<=high; i++) {
            if(max<height[i]) {
                max = height[i];
            }
        }

        return max;

    }
}
