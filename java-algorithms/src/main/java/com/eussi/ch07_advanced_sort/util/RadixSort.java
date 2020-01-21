package com.eussi.ch07_advanced_sort.util;

import com.eussi.ch05_Linklist.util.LinkList3;
import com.eussi.ch05_Linklist.util.ListIterator;

/**
 * @author wangxueming
 * @create 2020-01-21 13:56
 * @description
 */
public class RadixSort {
    // ==============================================
    // 基数排序算法
    // ==============================================
    // 过程如下:
    // 初数组 8 12 22 15 20 7 25 18 212 基数为10
    // 首先按个位排序
    // 结果是 (20)(12 22 212)(15 25)(7)(8 18)
    // 然后按十位排序
    // 结果是 (7 8) (12 212 15 18) (20 22 25)
    // 然后按百位排序
    // 结果是 (7 8 12 15 18 20 22 25) 212
    // 排序结束
    // ==============================================
    // 此方法用链表暂存元素
    // ==============================================
    public static void radixSort(long[] array, int radix, int distance) {
        // array 为待排序数组
        // radix 代表基数
        // distance 代表排序元素的位数 //大于或等于最大的元素的位数
        int length = array.length;
        ListIterator[] temp = new ListIterator[radix];// 用于暂存元素
        for (int x = 0; x < radix; x++) { // 初始化数组
            temp[x] = new LinkList3().getIterator();
        }
        int divide = 1;

        for (int i = 0; i < distance; i++) {
            // 个人觉的运用基数排序实现基数排序的重点在下面这些代码
            for (int j = 0; j < length; j++) { // 按各元素相应位上的数字分组
                int tempindex = (int) (array[j] / divide) % radix;
                temp[tempindex].insertAfter(array[j]);
            }
            int l = 0;
            for (int k = 0; k < temp.length; k++) { // 把分好组的元素复制回原数组
                if(temp[k].getCurrent()!=null) {
                    temp[k].reset();
                    array[l++] = temp[k].getCurrent().dData;

                    while (!temp[k].atEnd()) {
                        temp[k].nextLink();
                        array[l++] = temp[k].getCurrent().dData;
                    }
                    temp[k].clearAll();
                }
            }
            divide = divide * radix;
        }
    }
}
