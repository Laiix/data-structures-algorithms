package com.eussi.dynamic_programming;

public class
ThrowsEggs {
    public static void main(String[] args) {
        //耗时很长
//        System.out.println(MaximumThrowEggs_1(2, 15));

        //备忘录解法
        System.out.println(MaximumThrowEggs_2(2, 100));

        //二分法解法
        System.out.println(MaximumThrowEggs_3(2, 100));

        //dp
        System.out.println(MaximumThrowEggs_4(2, 100));
    }


    public static int MaximumThrowEggs_1(int k, int n) {
        if(k==1) return n;
        if(n==0) return 0;
        int res = Integer.MAX_VALUE;
        for(int i=1; i<=n; i++) {
            res = Math.min(res, Math.max(MaximumThrowEggs_1(k-1, i-1), MaximumThrowEggs_1(k, n-i)) + 1);
        }

        return res;
    }

    public static int MaximumThrowEggs_2(int k, int n) {
        int[][] memo = new int[k+1][n+1];
        return helper(k, n, memo);

    }

    private static int helper(int k, int n, int[][] memo) {

        if(k==1) return n;
        if(n==0) return 0;
        if(memo[k][n]!=0)
            return memo[k][n];

        int res = Integer.MAX_VALUE;
        for(int i=1; i<=n; i++) {
            res = Math.min(
                    res, Math.max(
                            helper(k-1, i-1, memo),
                            helper(k, n-i, memo)
                    ) + 1
            );
        }
        memo[k][n] = res;
        return res;
    }

    public static int MaximumThrowEggs_3(int k, int n) {
        int[][] memo = new int[k+1][n+1];
        return helper3(k, n, memo);

    }

    private static int helper3(int k, int n, int[][] memo) {

        if(k==1) return n;
        if(n==0) return 0;
        if(memo[k][n]!=0)
            return memo[k][n];

        int res = Integer.MAX_VALUE;
//        for(int i=1; i<=n; i++) {
//            res = Math.min(
//                    res, Math.max(
//                            MaximumThrowEggs_1(k-1, i-1),
//                            MaximumThrowEggs_1(k, n-i)
//                    ) + 1
//            );
//        }
        //依托于单调递增的属性
        int low = 1;
        int high = n;

        while(low <= high) {
            int mid = (low + high) / 2;
            int broken = helper3(k-1, mid-1, memo);
            int not_broken = helper3(k, n-mid, memo);
            if(broken>not_broken) {
                high = mid - 1;
                res = Math.min(res, broken + 1);
            } else {
                low = mid + 1;
                res = Math.min(res, not_broken + 1);
            }
        }

        memo[k][n] = res;
        return res;
    }

    public static int MaximumThrowEggs_4(int k, int n) {
        int[][] dp = new int[k+1][n+1];
        int m = 0;
        while(dp[k][m] < n) {
            m++;
            for(int i=1; i<=k; i++)
                dp[i][m] = dp[i-1][m-1] + dp[i][m-1] + 1;
        }
        return m;
    }
}
