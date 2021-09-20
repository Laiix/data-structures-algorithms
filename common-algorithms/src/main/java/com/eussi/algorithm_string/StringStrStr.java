package com.eussi.algorithm_string;

import java.util.HashMap;
import java.util.Map;

public class StringStrStr {

    public static void main(String[] args) {

        String src = "bcbdcabdca";
        String pattern = "cabdca";
        testBySearch(src, pattern);

        src = "abababababca";
        pattern = "abababca";
        testBySearch(src, pattern);

        src = "caaababb";
        pattern = "aabab";
        testBySearch(src, pattern);

        src = "abcde";
        pattern = "cb";
        testBySearch(src, pattern);

        src = "babcabdefabcdabed";
        pattern = "";
        testBySearch(src, pattern);

        src = "ijadgijaigsdafkdsfd";
        pattern = "ijai";
        testBySearch(src, pattern);

    }

    public static void testBySearch(String src, String pattern) {
        System.out.println("src:" + src);
        System.out.println("pattern:" + src);

        System.out.println("jdk:" + src.indexOf(pattern));
        System.out.println("bf(brute force):" + bf(src, pattern));
        System.out.println("bf2(brute force):" + bf2(src, pattern));

        System.out.println("KMP(Knuth-Morris-Pratt):" + findByKmp(src, pattern));

        System.out.println("sunday:" + findBySunday(src, pattern));
        System.out.println("sunday2:" + findBySunday2(src, pattern));
        System.out.println("sunday3:" + findBySunday3(src, pattern));

        System.out.println("bm(Boyer-Moore):" + findByBm(src, pattern));

        System.out.println("---");
    }



    public static int bf(String src, String pattern) {
        int haylen = src.length();
        int needlen = pattern.length();
        //特殊情况
        if (haylen < needlen) {
            return -1;
        }
        if (needlen == 0) {
            return 0;
        }
        //主串
        for (int i = 0; i < haylen - needlen + 1; ++i) {
            int j;
            //模式串
            for (j = 0; j < needlen; j++) {
                //不符合的情况，直接跳出，主串指针后移一位
                if (src.charAt(i+j) != pattern.charAt(j)) {
                    break;
                }
            }
            //匹配成功
            if (j == needlen) {
                return i;
            }

        }
        return -1;
    }

    public static int bf2(String src, String pattern) {
        //i代表主串指针，j模式串
        int i,j;
        //主串长度和模式串长度
        int halen = src.length();
        int nelen = pattern.length();
        //循环条件，这里只有 i 增长
        for (i = 0 , j = 0; i < halen && j < nelen; ++i) {
            //相同时，则移动 j 指针
            if (src.charAt(i) == pattern.charAt(j)) {
                ++j;
            } else {
                //不匹配时，将 j 重新指向模式串的头部，将 i 本次匹配的开始位置的下一字符
                i -= j;
                j = 0;
            }
        }
        //查询成功时返回索引，查询失败时返回 -1；
        int renum = j == nelen ? i - nelen : -1;
        return renum;

    }


    public static int findByKmp(String src, String pattern) {
        int srcLen = src.length();
        int patternLen = pattern.length();
        if(srcLen<patternLen)
            return -1;
        if(patternLen==0)
            return 0;
        int[] next = getNext2(pattern);
        int i=0;
        int j=0;
        while(i<srcLen && j<patternLen) {
            if(j==-1 || src.charAt(i)==pattern.charAt(j)) {
                i++;
                j++;
            } else
                j = next[j];

        }
        if(j==patternLen)
            return i-j;
        return -1;

    }

    private static int findBySunday(String src, String pattern) {
        Map<Character, Integer> patternMap = new HashMap<Character, Integer>();
        for(int i=0; i<pattern.length(); i++) {
            patternMap.put(pattern.charAt(i), i);//后面覆盖前面
        }

        int i=0;
        int j=0;

        int srcLen = src.length();
        int patternLen = pattern.length();
        while(i<srcLen && j<patternLen) {
            if(src.charAt(i)==pattern.charAt(j)) {
                i++;
                j++;
            } else {
                int nextIndex = i+(patternLen-j);
                if(nextIndex<srcLen) {
                    char nextChar = src.charAt(nextIndex);
                    Integer next = patternMap.get(nextChar);
                    if(next==null) {
                        int next2Index = nextIndex + 1;
                        if(next2Index<srcLen){
                            j=0;
                            i = next2Index;
                        } else {
                            return -1;
                        }
                    } else {
                        j = 0;
                        i = nextIndex - next;
                    }
                }else {
                    return -1;
                }
            }
        }
        if(j==patternLen)
            return i-j;
        return -1;
    }

    private static int findBySunday2(String src, String pattern) {
        Map<Character, Integer> patternMap = new HashMap<Character, Integer>();
        for(int i=0; i<pattern.length(); i++) {
            patternMap.put(pattern.charAt(i), i);//后面覆盖前面
        }
        int i=0;
        int j=0;
        int srcLen = src.length();
        int patternLen = pattern.length();
        while(i<srcLen && j<patternLen) {
            if(src.charAt(i)==pattern.charAt(j)) {
                i++;
                j++;
            } else {
                int nextIndex = i+(patternLen-j);
                if(nextIndex<srcLen) {
                    char nextChar = src.charAt(nextIndex);
                    int next = patternMap.get(nextChar)==null ? -1 : patternMap.get(nextChar);
                    i = nextIndex - next;
                    j = 0;
                    if(i>=srcLen)
                        return -1;
                }else {
                    return -1;
                }
            }
        }
        if(j==patternLen)
            return i-j;
        return -1;
    }

    public static int findBySunday3(String src, String pattern) {
        int srcLen = src.length(), patternLen = pattern.length();
        //初始化pattern位置数组
        int[] occ = sundayInitOcc(pattern);
        //跳跃的步数
        int jump = 0;
        for (int i = 0; i <= srcLen - patternLen; i += jump) {
            int j = 0;
            //用pattern字符与text字符挨个匹配
            while (j < patternLen && src.charAt(i + j) == pattern.charAt(j))
                j++;
            if (j == patternLen)//此时匹配成功
                return i;

            //此时不匹配
            //主要看问号后边计算跳跃步数,pattern长度减去pattern对应下一个字符位置就是要跳跃距离,由于occ数组中不存在为-1,巧妙合并了两种情况
            jump = i + patternLen < srcLen ? patternLen - occ[src.charAt(i + patternLen)] : 1;
        }
        return -1;
    }

    public static int findByBm(String src, String pattern) {
        char[] srcArr = src.toCharArray();
        char[] patternArr = pattern.toCharArray();
        int srcLen = src.length();
        int patternLen = pattern.length();
        //坏字符
        int[] bc = getBadChar(patternArr,patternLen);
        //用来保存各种长度好后缀的最右位置的数组
        int[] suffix_index = new int[patternLen];
        //判断是否是头部，如果是头部则true
        boolean[] ispre = new boolean[patternLen];
        goodSuffix(patternArr, patternLen, suffix_index, ispre);

        int i = 0;//第一个匹配字符
        //注意结束条件
        while (i <= srcLen-patternLen) {
            int j;
            //从后往前匹配，匹配失败，找到坏字符
            for (j = patternLen - 1; j >= 0; --j) {
                if (srcArr[i+j] != patternArr[j]) break;
            }
            //模式串遍历完毕，匹配成功
            if (j < 0) {
                return i;
            }
            //下面为匹配失败时，如何处理
            //求出坏字符规则下移动的位数，就是我们坏字符下标减最右边的下标
            int x = j - bc[(int)srcArr[i+j]];
            int y = 0;
            //好后缀情况，求出好后缀情况下的移动位数,如果不含有好后缀的话，则按照坏字符来
            if (y < patternLen-1 && patternLen - 1 - j > 0) {
                y = move(j, patternLen, suffix_index,ispre);
            }
            //移动
            i = i + Math.max(x,y);

        }
        return -1;
    }

    //用来求坏字符情况下移动位数
    private static int[] getBadChar(char[] patternArr, int patternLen) {
        int[] bc = new int[256];//创建一个数组用来保存最右边字符的下标
        //初始化
        for (int i = 0; i < 256; ++i) {
            bc[i] = -1;
        }
        //m 代表模式串的长度，如果有两个 a,则后面那个会覆盖前面那个
        for (int i = 0; i < patternLen; ++i) {
            int ascii = (int)patternArr[i];
            bc[ascii] = i;//下标
        }
        return bc;
    }
    //用来求好后缀条件下的移动位数
    private static void goodSuffix (char[] patternArr, int patternLen, int[] suffix, boolean[] prefix) {
        //初始化
        for (int i = 0; i < patternLen; ++i) {
            suffix[i] = -1;
            prefix[i] = false;
        }
        for (int i = 0; i < patternLen - 1; ++i) {
            int j = i;
            int k = 0;
            while (j >= 0 && patternArr[j] == patternArr[patternLen-1-k]) {
                --j;
                ++k;
                suffix[k] = j + 1;
            }
            if (j == -1) prefix[k] = true;
        }
    }

    // j代表坏字符的下标
    private static int move (int j, int patternLen, int[] suffix_index, boolean[] ispre) {
        //好后缀长度
        int k = patternLen - 1 - j;
        //如果含有长度为 k 的好后缀，返回移动位数，
        if (suffix_index[k] != -1) return j - suffix_index[k] + 1;
        //找头部为好后缀子串的最大长度，从长度最大的子串开始
        for (int r = j + 2; r <= patternLen-1; ++r) {
            //如果是头部
            if (ispre[patternLen-r]) {
                return r;
            }
        }
        //如果没有发现好后缀匹配的串，或者头部为好后缀子串，则移动到 m 位，也就是匹配串的长度
        return patternLen;
    }


    private static int[] getNext(String pattern) {
        int patternLen = pattern.length();
        int[] next = new int[patternLen];
        next[0] = -1;

        for(int i=2; i<patternLen; i++) {
            int max = 0;
            int j=0;
            for(; j<i-1; j++) {
                if(pattern.substring(0,j+1).equals(pattern.substring((i-1)-j, i)))
                    if(max<j+1)
                        max = j+1;
            }
            next[i] = max;
        }
        return next;
    }

    /**
     * 参考 https://www.zhihu.com/question/21923021/answer/281346746
     * @param pattern
     * @return
     */
    private static int[] getNext2(String pattern) {
        int patternLen = pattern.length();
        int i=0;
        int j=-1;
        int[] next = new int[patternLen];
        next[0] = -1;

        while(i<patternLen-1) {
            if(j==-1 || pattern.charAt(i)==pattern.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        return next;
    }



    /**
     * 初始化occ数组,数组中存储pattern字符串中每个字符在pattern中出现的位置
     * 索引为ASCII码,内容为在pattern的位置,出现相同字符,靠后位置的覆盖前边的,不存在的字符在pattern位置为-1
     * @param p
     * @return
     */
    public static int[] sundayInitOcc(String p) {
        int[] occ = new int[128];
        for (int i = 0; i < occ.length; i++)
            occ[i] = -1;
        for (int i = 0; i < p.length(); i++)
            occ[p.charAt(i)] = i;
        return occ;
    }



}
