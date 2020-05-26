package com.eussi.dynamic_programming;

public class LongestPalindromeSubString {
    public static void main(String[] args) {
        //最长回文子串
        System.out.println(longestPalindrome("juabaibaabc"));
    }

    public static String longestPalindrome(String s) {
        String res = "";
        for(int i=0; i<s.length(); i++) {
            String s1 = palindrome(s, i, i);
            String s2 = palindrome(s, i, i+1);
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;

        }
        return res;
    }

    private static String palindrome(String s, int l, int r) {
        // 防⽌索引越界
        while (l >= 0 && r < s.length()
                && s.charAt(l) == s.charAt(r)) {
            // 向两边展开
            l--; r++;
        }
        // 返回以 s[l] 和 s[r] 为中⼼的最⻓回⽂串
        return s.substring(l + 1, r);
    }
}
