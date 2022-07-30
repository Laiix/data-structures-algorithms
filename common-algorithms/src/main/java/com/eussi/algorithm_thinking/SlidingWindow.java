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
        String s = "AD0BEAC0DEBANC";
        String t = "AABC";
        System.out.println(slidingWindow_1(s, t));
        System.out.println(slidingWindow_1_1(s, t)); //2022-07-17练习

        //给你一个字符串s，和一个非空字符串t，找出s中所有是t的字母异位词的字串，返回这些字串的起始索引
        s = "cbaebabacd";
        t = "abc";
        System.out.println(slidingWindow_2(s, t));

        //给你一个字符串s,输出其中不含重复字符的最长字串的长度
        s = "abcabcbb";
        System.out.println(slidingWindow_3(s));
        System.out.println(slidingWindow_3("bbbbbbbbbbbbbb"));
        System.out.println(slidingWindow_3("bbbbbbbbbbbc"));
        System.out.println(slidingWindow_4(s));
        System.out.println(slidingWindow_4("bbbbbbbbbbbbbb"));
        System.out.println(slidingWindow_4("bbbbbbbbbbbc"));

        //给你一个字符串s,输出其中重复字符的最长字串的长度
        s = "aaabbbbbcc";
        System.out.println(slidingWindow_5(s));
        System.out.println(slidingWindow_5("aabbbbp"));
        System.out.println(slidingWindow_6(s));
        System.out.println(slidingWindow_6("aabbbbp"));
    }

    private static String slidingWindow_1(String s, String t) {
        int left=0,right=0; //窗口左右指针

        Map<Character, Integer> needs = new HashMap<>();//记录目标字符串中字符数量
        //需要匹配的字符串
        for(int i=0; i<t.length(); i++) {
            char c = t.charAt(i);
            Integer count = needs.get(c);
            needs.put(c, count==null?1:++count);
        }

        String res = "";
        Map<Character, Integer> window = new HashMap<>();//记录窗口中字符数量
        int match = 0;
        int minLen = Integer.MAX_VALUE;

        //滑动窗口算法
        while(right<s.length()) {
            char rightChar = s.charAt(right);
            if(needs.containsKey(rightChar)) {
                Integer count = window.get(rightChar);
                window.put(rightChar, count==null?1:++count);
                if(window.get(rightChar).equals(needs.get(rightChar))) {
                    match++;
                }
            }
            right++;

            while(match==needs.size()) {
                if(right-left < minLen) {
                    minLen = right-left;//更新
                    res = s.substring(left, right);
                }

                char leftChar = s.charAt(left);
                if(needs.containsKey(leftChar)) {//减去对应的数据
                    Integer count = window.get(leftChar);
                    window.put(leftChar, --count);
                    if(window.get(leftChar)<needs.get(leftChar)) {
                        match--;
                    }
                }
                left++;
            }
        }
        return res;
    }

    private static String slidingWindow_1_1(String s, String t) {
        int left=0, right=0;
        String result = "";

        Map<Character, Integer> tCharMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            operateKeyValue(tCharMap, c, 1);
        }

        Map<Character, Integer> windowCharMap = new HashMap<>();
        while(right<s.length()) {
            char rightChar = s.charAt(right);
            operateKeyValue(windowCharMap, rightChar, 1);
            right++;

            while (testContains(windowCharMap, tCharMap)) {
                String window = s.substring(left, right);
                if (result.equals("") || result.length() > window.length()) {
                    result = window;
                }
                char leftChar = s.charAt(left);
                operateKeyValue(windowCharMap, leftChar, -1);
                left++;
            }
        }
        return result;
    }

    private static boolean testContains(Map<Character, Integer> windowCharMap, Map<Character, Integer> tCharMap) {
        boolean contains = true;
        for (Character character : tCharMap.keySet()) {
            if(!(windowCharMap.containsKey(character) && windowCharMap.get(character).equals(tCharMap.get(character)))) {
                contains = false;
            }
        }
        return contains;
    }

    private static void operateKeyValue(Map<Character, Integer> map, char key, int changeValue) {
        Integer value = map.get(key);
        if(value==null) {
            if(changeValue>0) {
                map.put(key, 1);
            }
        } else {
            if(value+changeValue<=0) {
                map.remove(key);
            } else {
                map.put(key, value + changeValue);
            }
        }
    }

    private static List<Integer> slidingWindow_2(String s, String t) {
        int left=0,right=0; //窗口左右指针

        Map<Character, Integer> needs = new HashMap<>();//记录目标字符串中字符数量
        //需要匹配的字符串
        for(int i=0; i<t.length(); i++) {
            char c = t.charAt(i);
            Integer count = needs.get(c);
            needs.put(c, count==null?1:++count);
        }

        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> window = new HashMap<>();//记录窗口中字符数量
        int match = 0;

        //滑动窗口算法
        while(right<s.length()) {
            char rightChar = s.charAt(right);
            if(needs.containsKey(rightChar)) {
                Integer count = window.get(rightChar);
                window.put(rightChar, count==null?1:++count);
                if(window.get(rightChar).equals(needs.get(rightChar))) {
                    match++;
                }
            }
            right++;

            while(match==needs.size()) {
                if(right-left==t.length()) {
                    res.add(left);
                }

                char leftChar = s.charAt(left);
                if(needs.containsKey(leftChar)) {//减去对应的数据
                    Integer count = window.get(leftChar);
                    window.put(leftChar, --count);
                    if(window.get(leftChar)<needs.get(leftChar)) {
                        match--;
                    }
                }
                left++;
            }
        }
        return res;
    }

    private static int slidingWindow_3(String s) {
        int left=0,right=0; //窗口左右指针

        int res = 0;
        Map<Character, Integer> window = new HashMap<>();//记录窗口中字符数量

        //滑动窗口算法
        while(right<s.length()) {
            char rightChar = s.charAt(right);
            Integer rightCount = window.get(rightChar);
            window.put(rightChar, rightCount==null?1:++rightCount);
            right++;

            while(window.get(rightChar)>1) {
                char leftChar = s.charAt(left);
                Integer leftCount = window.get(leftChar);
                window.put(leftChar, --leftCount);
                left++;
            }
            res = Math.max(res, right-left);
        }
        return res;
    }

    private static int slidingWindow_4(String s) {
        int left=0,right=0;

        //精简的写法，但是和上面原理是一致的
        int max = Integer.MIN_VALUE;
        while(right<s.length()) {
            char c = s.charAt(right);
            right++;
            while(s.substring(left, right-1).contains(Character.toString(c))) {
                left++;
            }
            max = Math.max(max, right-left);//因为是求最长子串，所以需要在每次移动right增大窗口时更新res
        }
        return max;
    }

    private static int slidingWindow_5(String s) {
        int left=0,right=0; //窗口左右指针

        int res = 0;
        Map<Character, Integer> window = new HashMap<>();//记录窗口中字符数量

        //滑动窗口算法
        while(right<s.length()) {
            char rightChar = s.charAt(right);
            Integer rightCount = window.get(rightChar);
            window.put(rightChar, rightCount==null?1:++rightCount);
            right++;

            while(window.size()>1) {//当窗口中记录的多于一个字母时
                char leftChar = s.charAt(left);
                //写法一
//                Integer leftCount = window.get(leftChar);
//                if(leftCount==1) {
//                    window.remove(leftChar);
//                } else {
//                    window.put(leftChar, --leftCount);
//                }
//                left++;
                //写法二，类似slidingWindow_6
                window.remove(leftChar);
                left = right-1;

            }
            res = Math.max(res, right-left);
        }
        return res;
    }

    private static int slidingWindow_6(String s) {
        int left=0,right=0;

        int max = Integer.MIN_VALUE;
        while(right<s.length()) {
            char c = s.charAt(right);
            right++;
            if(!"".equals(s.substring(left, right-1))
                    && !s.substring(left, right-1).contains(Character.toString(c))) {
                left=right-1;
            }
            max = Math.max(max, right-left);
        }
        return max;
    }







}
