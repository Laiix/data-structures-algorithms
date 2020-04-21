package com.eussi.dynamic_programming;

/**
 * @author wangxueming
 * @create 2020-04-21 19:45
 * @description
 */
public class MaximumFrogJumps {
    public static void maximumFrogJumps(int n) {
        //暴力解法
        System.out.println(frogJumps_1(n));

        //添加备忘录
        System.out.println(frogJumps_2(n));

        //dp
        System.out.println(frogJumps_3(n));

        //打印路路径
        System.out.println(frogJumps_4(n));

        //打印路路径
        System.out.println(frogJumps_5(n));
    }

    public static int frogJumps_1(int n) {
        if(n==0) return 1;
        if(n==1) return 1;
        return frogJumps_1(n-1) + frogJumps_1(n-2);
    }

    public static int frogJumps_2(int n) {
        int[] memo = new int[n+1];
        return helper(n, memo);
    }

    private static int helper(int n, int[] memo) {
        if(n==0) return 1;
        if(n==1) return 1;
        if(memo[n]!=0)
            return memo[n];
        memo[n] = frogJumps_2(n-1) + frogJumps_2(n-2);
        return memo[n];
    }

    public static int frogJumps_3(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2; i<dp.length; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    public static int frogJumps_4(int n) {
        Node[] dp = new Node[n+1];
        dp[0] = new Node(1, null, null);
        dp[1] = new Node(1, dp[0], null);
        for(int i=2; i<dp.length; i++) {
            dp[i] = new Node(dp[i-1].val + dp[i-2].val, dp[i-1], dp[i-2]);
        }

        //打印出二叉数所有路径
        printTree(dp[n], n);

        return dp[n].val;
    }

    private static void printTree(Node root, int n) {
        int[] path = new int[n+1];
        printPath(root, path, 0, 0);
    }

    private static void printPath(Node node, int[] path, int n, int pos) {
        if(node==null) return;
        path[n++] = pos;
        if(node.left==null && node.right==null) {
            for (int i = 1; i < n; i++)
                System.out.print(path[i]);
            System.out.println();
        } else {
            printPath(node.left, path, n, 1);
            printPath(node.right, path, n, 2);
        }
    }

    private static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static int frogJumps_5(int n) {
        Node1[] dp = new Node1[n+1];
        dp[0] = new Node1(1, 0);
        dp[1] = new Node1(1, 1);
        dp[1].add(dp[0], 0);

        for(int i=2; i<dp.length; i++) {
            dp[i] = new Node1( 2 * dp[i-1].val, i);
            for(int j=i-1; j>=0; j--) {
                dp[i].add(dp[j], i-1-j);
            }
        }

        //打印出二叉数所有路径
        printMultiTree(dp[n], n);

        return dp[n].val;
    }

    private static void printMultiTree(Node1 root, int n) {
        int[] path = new int[n+1];
        printMultiPath(root, path, 0, 0);
    }

    private static void printMultiPath(Node1 node, int[] path, int n, int pos) {
        if(node==null) return;

        path[n++] = pos;
        if(node.nodes.length==0) {
            for (int i = 1; i < n; i++)
                System.out.print(path[i]);
            System.out.println();
        } else {
            for(int i=0; i<node.nodes.length; i++) {
                printMultiPath(node.nodes[i], path, n, i+1);
            }
        }
    }

    private static class Node1 {
        int val;
        Node1[] nodes;

        public Node1(int val, int n) {
            this.val = val;
            nodes = new Node1[n];
        }

        public void add(Node1 node, int index) {
            nodes[index] = node;
        }
    }
}
