package com.eussi.data._04;

import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * 基于数组的插入快，移除慢的优先级队列
 */
public class PriorityQ2 {
    // array in sorted order, from max at 0 to min at size-1
    private int maxSize;
    private long[] queArray;
    private int nItems;

    public PriorityQ2(int s) {
        maxSize = s;
        queArray = new long[maxSize];
        nItems = 0;
    }

    // 编程作业 4.4
    public void insert(long item) {
        queArray[nItems++] = item; // insert at 0
    } // end insert()

    // 编程作业 4.4
    public long remove() {
        int highPriority = 0;
        for (int i = 1; i < nItems; i++) {
            if (queArray[i] < queArray[highPriority]) {
                highPriority = i;
            }
        }
        long temp = queArray[highPriority];
        for (int i = highPriority; i < nItems - 1; i++) { // 数组后面部份往前移
            queArray[i] = queArray[i + 1];
        }
        nItems--;
        return temp;
    }

    // 编程作业 4.4 //题目有 歧义
    // 方法一 ：如果按插入的顺序显示
    public void display() {
        System.out.print("队列为：");
        for (int i = 0; i < nItems; i++) {
            System.out.print(queArray[i] + " ");
        }
        System.out.println();
    }

    // 方法二：如果按按优先级顺序显示
    public void display1() {
        long[] temp = new long[nItems];// 临时表
        System.arraycopy(queArray, 0, temp, 0, nItems); // 复制到临时表
        int out, in;
        for (out = 1; out < nItems; out++) {
            in = out;
            long t = temp[out];
            while (in > 0 && t < temp[in - 1]) {
                temp[in] = temp[in - 1];
                in--;
            }
            temp[in] = t;
        }
        System.out.print("队列为：");
        for (int i = 0; i < nItems; i++) {
            print(temp[i] + " ");
        }
        println();
    }


    public long peekMin() {
        return queArray[nItems - 1];
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public boolean isFull() {
        return (nItems == maxSize);
    }
}