package com.eussi.algorithm_thinking;

public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 4, 67, 100, 900, 1009, 1009, 1009, 1100, 2200};
        int target = 1009;

        System.out.println(binarySearch(nums, target));
        System.out.println(left_bound(nums, target));
        System.out.println(right_bound(nums, target));


        //珂珂喜欢吃香蕉。这里有N堆香蕉，第i堆中有piles[i]根香蕉。警卫已经离开了，将在H小时后回来。
        //珂珂可以决定她吃香蕉的速度K（单位：根/小时）。每个小时，她将会选择一对香蕉，从中吃掉K根。如果
        //这堆香蕉少于K根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
        //珂珂喜欢慢慢吃，但仍想在警卫回来前吃掉所有的香蕉。
        //返回她可以在H小时内吃掉所有香蕉的最小速度K（K为整数）。
        System.out.println(eatSpeed(new int[]{3, 6, 7, 11}, 8));
        System.out.println(eatSpeed_2(new int[]{3, 6, 7, 11}, 8));


        //传送带上的第i个包裹的重量为weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。
        //我们装载的重量不会超过船的最大运载重量。
        //返回能在D天内将传送带上的所有包裹送达的船的最低运载能力。
        System.out.println(carryingCapacity(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5));



    }

    private static int carryingCapacity(int[] piles, int h) {
        int maxSpeed = Integer.MIN_VALUE;
        int sum = 0;
        for(int i=0; i<piles.length; i++) {
            if(piles[i]>maxSpeed) {
                maxSpeed = piles[i];
            }
            sum += piles[i];
        }

//        int speed = -1;
//        for(int i=maxSpeed; i<=sum; i++) {
//            if(canLoad(piles, h, i)) {
//                speed = i;
//                break;
//            }
//        }
//
//        return speed;

        //寻找左边界
        int left=maxSpeed;
        int right = sum + 1;
        while(left<right) {
            int mid = (left+right)/2;
            if(canLoad(piles, h, mid)) {//能够吃完，代表两方面意思
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static boolean canLoad(int[] piles, int h, int mid) {
        int times = 0;

        int currSum = 0;
        for(int i=0; i<piles.length; i++) {
            currSum+=piles[i];
            if(currSum>mid) {
                times++;
                currSum = piles[i];
            } else if(currSum==mid){
                times++;
                currSum = 0;
            }
        }

        if(currSum!=0)
            times++;


        return times<=h;
    }

    private static int eatSpeed_2(int[] piles, int h) {
        int maxSpeed = Integer.MIN_VALUE;
        for(int i=0; i<piles.length; i++) {
            if(piles[i]>maxSpeed) {
                maxSpeed = piles[i];
            }
        }


        //寻找左边界
        int left=1;
        int right = maxSpeed+1;
        while(left<right) {
            int mid = (left+right)/2;
            if(canEat(piles, h, mid)) {//能够吃完，代表两方面意思，mid可以，mid以上的都可以
                right = mid;
            } else {
                left = mid + 1;
            }
        }


        return left;
    }

    private static int eatSpeed(int[] piles, int h) {
        int maxSpeed = Integer.MIN_VALUE;
        for(int i=0; i<piles.length; i++) {
            if(piles[i]>maxSpeed) {
                maxSpeed = piles[i];
            }
        }

        int speed = -1;
        for(int i=1; i<=maxSpeed; i++) {
            if(canEat(piles, h, i)) {
                speed = i;
                break;
            }
        }

        return speed;
    }

    private static boolean canEat(int[] piles, int h, int speed) {
        int times = 0;
        for(int i=0; i<piles.length; i++) {
            times += piles[i]/speed + (piles[i]%speed==0?0:1);
        }
        return times<=h;
    }

    private static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 注意
        while(left <= right) {
            int mid = (right + left) / 2;
            if(nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1; // 注意
            else if (nums[mid] > target)
                right = mid - 1; // 注意
        }
        return -1;
    }

    private static int left_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length; // 注意
        while (left < right) { // 注意
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid; // 注意
            }
        }
//        return left;
        // target ⽐所有数都⼤
        if (left == nums.length) return -1;
        // 类似之前算法的处理⽅式
        return nums[left] == target ? left : -1;
    }

    private static int right_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                left = mid + 1; // 注意
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
//        return left - 1; // 注意
        if (left == 0) return -1;
        return nums[left-1] == target ? (left-1) : -1;
    }
}
