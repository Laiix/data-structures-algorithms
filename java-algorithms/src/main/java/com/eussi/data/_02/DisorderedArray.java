package com.eussi.data._02;

import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * @author wangxueming
 * @create 2019-10-18 10:58
 * @description 无序数组
 */
public class DisorderedArray {
    private long[] a; // ref to array a
    private int nElems; // number of data items

    public DisorderedArray(int max) {
        a = new long[max]; // create the array
        nElems = 0; // no items yet
    }

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

    public void insert(long value) {
        a[nElems] = value; // insert it
        nElems++; // increment size
    }

    public boolean delete(long value) {
        int j;
        for (j = 0; j < nElems; j++) {
            // look for it
            if (value == a[j])
                break;
        }

        if (j == nElems) // can't find it
            return false;
        else {
            for (int k = j; k < nElems; k++)
                // move higher ones down
                a[k] = a[k + 1]; // 这里不是多移了一次 当k=nElems-1时, a[nElems-1] = a[nElems]; 此时a[nElems]为空
            nElems--; // decrement size
            return true;
        }
    }

    public void display() {
        print("[");
        for (int j = 0; j < nElems-1; j++)
            // for each element,
            print(a[j] + ", "); // display it
        print(a[nElems-1]);
        println("]");
    }

    /**
     * 查找最大元素
     * @return
     */
    public long getMax() {
        long max = -1;// 最大元素的值
        for (int j = 0; j < nElems; j++) {
            if (a[j] > max) {
                max = a[j];
            }
        }
        return max;
    }

    /**
     * 移除最大元素
     * @return
     */
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

    /**
     * 去重数据，移动较多次数
     */
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

    /**
     * 去重数据
     */
    public void noDup1() {
        int NULL = -1; // 用-1作特殊值 ，假设用户不会输入负的元素
        for (int j = 0; j < nElems; j++) {
            for (int i = j + 1; i < nElems; i++) {
                if (a[j] != NULL && a[j] == a[i]) {
                    a[i] = NULL;
                }
            }
        }

        int index = 0;//最终数组数字应该在的正确位置
        for (int i = 0; i < nElems; i++) {
            if (a[i] != NULL) {
                if (i > index) { //不大于没必要移动
                    a[index] = a[i];
                }
                index++;
            }
        }
        nElems = index;
    }

    /**
     * 通过插入排序的方式，去重数据
     */
    public void noDupByInsertionSort() {
        int in, out, count = 0;
        for (out = 1; out < nElems; out++) { // out is dividing line
            long temp = a[out]; // remove marked item
            in = out; // start shifts at out
            while (in > 0 && a[in - 1] >= temp && a[in - 1] != -1)  {
                if (a[in - 1] == temp) {
                    temp = -1;
                    count++;
                }
                a[in] = a[in - 1]; // shift item to right
                --in; // go left one position
            }
            a[in] = temp; // insert marked item
        } // end for
        nElems -= count;
        for (int i = 0; i < nElems; i++) {
            a[i] = a[i + count]; // 把排好序的元素向前移动count个位置
        }
    }

    public void noDupByInsertionSort2() {
        int count = 0;
        for(int out=nElems-1-1; out>=0; out--) {
            long temp = a[out];
            int in = out;
            while(in<nElems-1 && a[in+1]<=temp && a[in+1]!=Long.MAX_VALUE) {
                if(temp==a[in+1]) {
                    temp = Long.MAX_VALUE;
                    count ++;//这里犯了错误，用nElems--，nElems是循环的条件呀
                }
                a[in] = a[in+1];
                ++ in;
            }
            a[in] = temp;
        }
        nElems -= count;
    }

    /**
     * 冒泡排序
     */
    public void bubbleSort() {
        for(int out = nElems - 1; out > 0; out--) { // outer loop (backward) 刚开始要比较到倒数第二个数据,注意最后结束的条件，一直减到0
            for(int in=0; in<out; in++) { // inner loop (forward)
                if( a[in] > a[in+1] ) {  // out of order?
                    swap(in, in+1);          // swap them
                }
            }
        }
    }


    /**
     * 双向移动的冒泡排序
     */
    public void bubbleSort1() {
        int left = 0; int right = nElems-1;
        for(;left<right;left++,right--) {
            int in;
            for(in=left; in<right; in++) {
                if(a[in]>a[in+1]) {
                    swap(in, in+1);
                }
            }
            for(in = right-1; in>left; in--) { //注意，in=right-1,因为right已经是最大了
                if(a[in]<a[in-1]) {
                    swap(in, in-1);
                }
            }
        }
    }

    /**
     * 选择排序
     */
    public void selectionSort() {
        for(int out = 0; out<nElems-1; out++) { // outer loop (backward) 刚开始要比较到倒数第二个数据,注意最后结束的条件，一直减到0
            int min = out;
            for(int in=out; in<nElems; in++) { // inner loop (forward)
                if( a[min] > a[in] ) {  // out of order?
                    min = in;
                }
            }
            if(min!=out) {
                swap(out, min);
            }

        }
    }  // end bubbleSort()

    /**
     * 插入排序
     */
    public void insertionSort() {
        for (int out = 0; out < nElems; out++) {
            long temp = a[out];
            int in = out;
            while(in>0 && a[in-1]>temp) {
                a[in] = a[in-1];
                -- in;
            }
            a[in] = temp;
        }
    }

    /**
     * 插入排序，并返回比较和移动次数
     * @return
     */
    public int insertionSortAndCount() {
        int compare = 0; // 比较次数
        int copy = 0; // 复制次数
        for (int out = 1; out < nElems; out++)  {
            long temp = a[out]; // remove marked item
            int in = out; // start shifts at out
            while (in > 0) // until one is smaller,
            {
                if (a[in - 1] > temp) {
                    a[in] = a[in - 1]; // shift item to right
                    --in; // go left one position
                    compare++;
                    copy++;
                } else {
                    compare++;
                    break;
                }
            }
            a[in] = temp; // insert marked item
        } // end for
        return compare + copy;
    }

    /**
     * 交换数组中两个元素
     * @param src
     * @param dest
     */
    private void swap(int src, int dest) {
        long temp = a[src];
        a[src] = a[dest];
        a[dest] = temp;
    }

    public long median() {
        this.insertionSort(); // 先排序，再取中间值 //这个中间值是大小上的中间值.
        return a[nElems / 2];
    }

    /**
     * 奇偶排序
     */
    public void oddEvenSort() {
        int num = 0;
        while(!isOrder()){
            for(int j=1; j<nElems-1; j+=2) {
                if(a[j]>a[j+1]) {
                    swap(j, j+1);
                }
            }
            for(int j=0; j<nElems-1; j+=2) {
                if(a[j]>a[j+1]) {
                    swap(j, j+1);
                }
            }
            num++;
        }
        println("Sort times: " + num);
    }
    private boolean isOrder() {
        boolean b = true;
        for(int j=0; j<nElems-1; j++) {
            if(a[j+1]<a[j]) {
                b = false;
            }
        }
        return b;
    }


    /**
     * 归并排序
     */
    public void mergeSort() {
        long[] workSpace = new long[nElems];
        recMergeSort(workSpace, 0, nElems-1);
    }

    private void recMergeSort(long[] workSpace,
                              int lowerBound,
                              int upperBound) {
        if(lowerBound == upperBound)            // if range is 1,
            return;                              // no use sorting
        else {                                    // find midpoint
            int mid = (lowerBound+upperBound) / 2;
            // sort low half
            recMergeSort(workSpace, lowerBound, mid);
            // sort high half
            recMergeSort(workSpace, mid+1, upperBound);
            // merge them
            merge(workSpace, lowerBound, mid+1, upperBound);
        }  // end else
    }

    private void merge(long[] workSpace,
                       int lowPtr,
                       int midPtr,
                       int upperBound) {
        int j = 0;                             // workspace index
        int lowerBound = lowPtr;
        int lowerUpperBound = midPtr-1;              //左边数组最大索引
        int n = upperBound-lowerBound+1;       // 计算此次排序的数据个数



        while(lowPtr <= lowerUpperBound && midPtr <= upperBound)
            if( a[lowPtr] < a[midPtr] )
                workSpace[j++] = a[lowPtr++];
            else
                workSpace[j++] = a[midPtr++];

        while(lowPtr <= lowerUpperBound)
            workSpace[j++] = a[lowPtr++];

        while(midPtr <= upperBound)
            workSpace[j++] = a[midPtr++];

        for(j=0; j<n; j++)
            a[lowerBound+j] = workSpace[j];
    }

}