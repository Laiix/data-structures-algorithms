package com.eussi.chapter02.util;

/**
 * @author wangxueming
 * @create 2019-10-18 10:58
 * @description
 */
public class HighArray {
    private long[] a; // ref to array a
    private int nElems; // number of data items

    // -----------------------------------------------------------
    public HighArray(int max) // constructor
    {
        a = new long[max]; // create the array
        nElems = 0; // no items yet
    }

    // -----------------------------------------------------------
    public boolean find(long searchKey) { // find specified value
        int j;
        for (j = 0; j < nElems; j++)
            // for each element,
            if (a[j] == searchKey) // found item?
                break; // exit loop before end
        if (j == nElems) // gone to end?
            return false; // yes, can't find it
        else
            return true; // no, found it
    } // end find()

    // -----------------------------------------------------------
    public void insert(long value) // put element into array
    {
        a[nElems] = value; // insert it
        nElems++; // increment size
    }

    // -----------------------------------------------------------
    public boolean delete(long value) {
        int j;
        for (j = 0; j < nElems; j++)
            // look for it
            if (value == a[j])
                break;
        if (j == nElems) // can't find it
            return false;
        else // found it
        {
            for (int k = j; k < nElems; k++)
                // move higher ones down
                a[k] = a[k + 1]; // 这里不是多移了一次 当k=nElems-1时, a[nElems-1] = a[nElems]; 此时a[nElems]为空
            nElems--; // decrement size
            return true;
        }
    } // end delete()

    // -----------------------------------------------------------
    public void display() // displays array contents
    {
        for (int j = 0; j < nElems; j++)
            // for each element,
            System.out.print(a[j] + " "); // display it
        System.out.println("");
    }

    // -----------------------------------------------------------

    // ==========================================================
    // p50(69) 编程作业2.1
    public long getMax() {
        long max = -1;// 最大元素的值
        for (int j = 0; j < nElems; j++) {
            if (a[j] > max) {
                max = a[j];
            }
        }
        return max;
    }

    // ==========================================================
    // p50(69) 编程作业2.2
    public long removeMax() {
        long max = -1; // 最大元素的值
        int index = -1; // 最大元素的索引号
        for (int j = 0; j < nElems; j++) {
            if (a[j] > max) {
                max = a[j];
                index = j;
            }
        }
        if (index != -1) { // 找到最大元素
            for (int i = index + 1; i < nElems; i++) {
                a[i - 1] = a[i];
            }
            nElems--;
        }
        return max;
    }

    // ==========================================================
    // p50(69) 编程作业2.6
    // 第一种方法
    public void noDup() {
        int NULL = -1; // 用-1作特殊值
        for (int j = 0; j < nElems; j++) {
            for (int i = j + 1; i < nElems; i++) {
                if (a[j] != NULL && a[j] == a[i]) {
                    a[i] = NULL;
                }
            }
        }

        for (int i = 0; i < nElems;) {
            if (a[i] == NULL) {// 注意:移动完成后不要i++，再次检查当前位置是否为NULL
                for (int j = i + 1; j < nElems; j++) {
                    a[j - 1] = a[j];
                }
                nElems--;
            } else {
                i++; // 不是NULL，直接i++;
            }
        }
    }

    // 第二种方法
    public void noDup1() {
        int NULL = -1; // 用-1作特殊值 ，假设用户不会输入负的元素
        for (int j = 0; j < nElems; j++) {
            for (int i = j + 1; i < nElems; i++) {
                if (a[j] != NULL && a[j] == a[i]) {
                    a[i] = NULL;
                }
            }
        }

        int order = 0;
        for (int temp = 0; temp < nElems; temp++) {
            if (a[temp] != NULL) {// 因为a[0]不可能等于NULL所以才可以用这种方法? 等于NULL也可以吧？
                if (temp > order) {
                    a[order] = a[temp];
                }
                order++;
            }
        }
        nElems = order;
    }

    // ==========================================================
} // end class HighArray
// //////////////////////////////////////////////////////////////