package com.eussi.algorithm_thinking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangxueming
 * @create 2020-05-17 4:27
 * @description
 */
public class SlidingWindow {
    public static void main(String[] args) {
        //给你一个字符串s，和字符串t，请在字符串s中找出：包含T所有字母的最小字串
        String s = "AD0BEC0DEBANC";
        String t = "ABC";
        System.out.println(slidingWindow(s, t));

        //给你一个字符串s，和一个非空字符串t，找出s中所有是t的字母异位词的字串，返回这些字串的起始索引
        s = "cbaebabacd";
        t = "abc";
        System.out.println(slidingWindow_2(s, t));

        //给你一个字符串s,输出其中不含重复字符的最长字串的长度
        s = "abcabcbb";
        System.out.println(slidingWindow_3(s));
        System.out.println(slidingWindow_3("bbbbbbbbbbbbbb"));
        System.out.println(slidingWindow_3("bbbbbbbbbbbc"));

        //给你一个字符串s,输出其中重复字符的最长字串的长度
        s = "aaabbbbbcc";
        System.out.println(slidingWindow_4(s));
        System.out.println(slidingWindow_4("aabbbbp"));
    }

    private static int slidingWindow_4(String s) {
        int left=0,right=0;

        int max = Integer.MIN_VALUE;

        while(right<s.length()) {
            char c = s.charAt(right);
            right++;
            while(!"".equals(s.substring(left, right-1)) && !s.substring(left, right-1).contains(c+"")) {
                left=right-1;
            }
            if(right-left > max) {
                max = right - left;
            }
        }



        return max;
    }

    private static int slidingWindow_3(String s) {
        int left=0,right=0;

        int max = Integer.MIN_VALUE;

        while(right<s.length()) {
            char c = s.charAt(right);
            right++;
            while(s.substring(left, right-1).contains(c+"")) {
                left++;
            }
            if(right-left > max) {
                max = right - left;
            }
        }



        return max;
    }

    private static List<Integer> slidingWindow_2(String s, String t) {
        int left=0,right=0;

        Map<Character, Integer> window = new HashMap<Character, Integer>();
        Map<Character, Integer> needs = new HashMap<Character, Integer>();

        //需要匹配的字符串
        for(int i=0; i<t.length(); i++) {
            char c = t.charAt(i);
            if(needs.get(c)==null) {
                needs.put(c, 1);
            } else {
                needs.put(c, needs.get(c)+1);
            }
        }

        int match = 0;
        List<Integer> res = new ArrayList<Integer>();

        while(right<s.length()) {
            char c = s.charAt(right);

            if(needs.containsKey(c)) {
                if(window.get(c)==null) {
                    window.put(c, 1);
                } else {
                    window.put(c, window.get(c)+1);
                }
                if(window.get(c)==needs.get(c) )
                    match++;
            }


            right++;

            while(match==needs.size()) {

                if(right-left == t.length()) {
                    res.add(left);
                }

                char c1 = s.charAt(left);

                if(needs.containsKey(c1)) {
                    if(window.get(c1)==null) {
                        window.put(c1, -1);
                    } else {
                        window.put(c1, window.get(c1)-1);
                    }
                    if(window.get(c1)<needs.get(c1))
                        match--;
                }
                left++;

            }
        }
        return res;
    }

    private static String slidingWindow(String s, String t) {
        int left=0,right=0;

        Map<Character, Integer> window = new HashMap<Character, Integer>();
        Map<Character, Integer> needs = new HashMap<Character, Integer>();

        //需要匹配的字符串
        for(int i=0; i<t.length(); i++) {
            char c = t.charAt(i);
            if(needs.get(c)==null) {
                needs.put(c, 1);
            } else {
                needs.put(c, needs.get(c)+1);
            }
        }

        int match = 0;
        String res = "";
        int minLen = Integer.MAX_VALUE;

        while(right<s.length()) {
            char c = s.charAt(right);

            if(needs.containsKey(c)) {
                if(window.get(c)==null) {
                    window.put(c, 1);
                } else {
                    window.put(c, window.get(c)+1);
                }
                if(window.get(c)==needs.get(c))
                    match++;
            }


            right++;

            while(match==needs.size()) {

                if(right-left<minLen) {
                    //更新
                    minLen = right-left;
                    res = s.substring(left, right);
                }

                char c1 = s.charAt(left);

                if(needs.containsKey(c1)) {
                    if(window.get(c1)==null) {
                        window.put(c1, -1);
                    } else {
                        window.put(c1, window.get(c1)-1);
                    }
                    if(window.get(c1)<needs.get(c1))
                        match--;
                }
                left++;

            }
        }
        return res;
    }

}
