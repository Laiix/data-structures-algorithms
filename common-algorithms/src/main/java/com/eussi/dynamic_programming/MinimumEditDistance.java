package com.eussi.dynamic_programming;

/**
 * @author wangxueming
 * @create 2020-04-20 21:08
 * @description
 */
public class MinimumEditDistance {
    /**
     * 给定两个字符串s1和S2,计算出将S1转换成S2所使用的最少操作数。
     * 你可以对一个字符串进行如下三种操作：
     *      1.	插入一个字符
     *      2.	删除一个字符
     *      3.	替换一个字符
     *
     * 示例1：
     * 输入：s1 = "horse", s2 = "ros"
     * 输出：3
     *  解释：
     *  horse -> rorse （将'h'替换为'r'）
     *  rorse -> rose （删除'r'）
     *  rose -> ros （删除'e'）
     * 示例2：
     * 输入：s1 = "intention", s2 = "execution"
     * 输出：5
     *  解释：
     *  intention -> inention （删除't'）
     *  inention -> enention （将'i'替换为'e'）
     *  enention -> exention （将'n'替换为'x'）
     *  exention -> exection （将'n'替换为'c'）
     *  exention -> execution （插入'u'）
     */

    public static void MinimumEditDistance(String s1, String s2) {
        System.out.println(med(s1, s2, s1.length()-1, s2.length()-1));
        System.out.println(med_2(s1, s2));
        System.out.println(med_3(s1, s2));
        System.out.println(med_4(s1, s2));
    }

    public static String med_4(String s1, String s2) {
        Node[][] dp = new Node[s1.length()+1][s2.length()+1];
        for(int i=0; i<=s1.length(); i++) {
            dp[i][0] = new Node(i, 'd');
        }
        for(int j=0; j<=s2.length(); j++) {
            dp[0][j] = new Node(j, 'i');
        }

        for(int i=1; i<=s1.length(); i++) {
            for(int j=1; j<=s2.length(); j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = new Node(dp[i-1][j-1].val, 'n');
                } else {
                    int index = min(dp[i-1][j],
                            dp[i][j-1],
                            dp[i-1][j-1]);

                    switch (index) {
                        case 1:
                            dp[i][j] = new Node(dp[i-1][j].val+1, 'd');
                            break;
                        case 2:
                            dp[i][j] = new Node(dp[i][j-1].val+1, 'i');
                            break;
                        case 3:
                            dp[i][j] = new Node(dp[i-1][j-1].val+1, 'r');
                            break;
                    }
                }
            }
        }

        String ret = "";
        int times = dp[s1.length()][s2.length()].val;
        int i = s1.length();
        int j = s2.length();
        while(times>0) {

            char choice = dp[i][j].choice;
            if('n'!=choice)
                --times;
            ret = ret + choice;
            switch (choice) {
                case 'd':
                    i--;
                    break;
                case 'i':
                    j--;
                    break;
                default:
                    i--;
                    j--;
            }
        }

        return ret;
    }

    private static int min(Node node, Node node1, Node node2) {
        int index = 1;
        if(node1.val < node.val)
            index = 2;
        if(index==1)
            if(node2.val < node.val)
                return 3;

        if(index==2)
            if(node2.val < node1.val)
                return 3;

        return index;
    }

    private static class Node {
        int val;
        char choice; //d delete, i insert, a add, n nothing

        public Node(int val, char choice) {
            this.val = val;
            this.choice = choice;
        }
    }


    public static int med_3(String s1, String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        for(int i=0; i<=s1.length(); i++) {
            dp[i][0] = i;
        }
        for(int j=0; j<=s2.length(); j++) {
            dp[0][j] = j;
        }

        for(int i=1; i<=s1.length(); i++) {
            for(int j=1; j<=s2.length(); j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = min(dp[i-1][j] + 1,
                            dp[i][j-1] + 1,
                            dp[i-1][j-1] + 1);
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }

    public static int med_2(String s1, String s2) {
        int[][] memo = new int[s1.length()][s2.length()];
        return dp(s1, s2, s1.length()-1, s2.length()-1, memo);
    }

    private static int dp(String s1, String s2, int i, int j, int[][] memo) {
        if(i==-1) return j+1;
        if(j==-1) return i+1;

        if(memo[i][j]!=0)
            return memo[i][j];

        if(s1.charAt(i)==s2.charAt(j)) {
            memo[i][j] = dp(s1, s2, i-1, j-1, memo);
        } else {
            memo[i][j] = min(dp(s1, s2, i-1, j, memo) + 1,
                    dp(s1, s2, i, j-1, memo) + 1,
                    dp(s1, s2, i-1, j-1, memo) + 1);
        }
        return memo[i][j];
    }

    public static int med(String s1, String s2, int i, int j) {
        if(i==-1) return j+1;
        if(j==-1) return i+1;

        if(s1.charAt(i)==s2.charAt(j)) {
            return med(s1, s2, i-1, j-1);
        } else {
            return min(med(s1, s2, i-1, j) + 1,
                    med(s1, s2, i, j-1) + 1,
                    med(s1, s2, i-1, j-1) + 1);
        }
    }

    private static int min(int m, int n, int p) {
        return Math.min(m, Math.min(n, p));
    }

}
