package com.eussi.data._02;

import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * @author wangxueming
 * @create 2019-10-18 10:58
 * @description 无序数组
 */
public class SimpleArray {
    private long[] a; // ref to array a
    private int nElems; // number of data items

    public SimpleArray(int max) {
        a = new long[max]; // create the array
        nElems = 0; // no items yet
    }

    public int size() {
        return nElems;
    }

    public long get(int index) {
        return a[index];
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

    public void insert(long ...values) {
        for (long value : values) {
            insert(value);
        }
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
        println(this.getClass().getSimpleName() + ": ");
        println("\tSize: " + nElems);

        print("\tData: ");
        print("[");
        if(nElems>0) {
            for (int j = 0; j < nElems-1; j++)
                // for each element,
                print(a[j] + ", "); // display it
            print(a[nElems-1]);
        }
        println("]");

        //是否有序，大于1才有意义
        if(nElems>1) {
            boolean isOrder = true;
            Boolean asc = null;
            for(int i=1; i<nElems; i++) {
                if(asc==null) {//确定逆序还是顺序
                    if(a[i-1]<a[i]) {
                        asc = true;
                    } else if(a[i-1]>a[i]){
                        asc = false;
                    }
                } else { //确定后，查看后续数据是否依然有序
                    if(asc && a[i-1]>a[i]) {
                        isOrder = false;
                    } else if(!asc && a[i-1]<a[i]) {
                        isOrder = false;
                    }
                }
            }
            if(asc==null) {
                //重复序列
                println("\tOrder: repetitive sequence");
            } else {
                println("\tOrder: " + (isOrder?( asc?"asc":"desc" ):"disorder" ));
            }

        } else {
            println("\tOrder: --");
        }

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

    /**
     * 希尔排序
     */
    public void shellSort() {
        int inner, outer;
        long temp;
        int h = 1;                     // find initial value of h
        while (h <= nElems / 3)
            h = h * 3 + 1;                // (1, 4, 13, 40, 121, ...)
        while (h > 0) {
            // h-sort the file
            for (outer = h; outer < nElems; outer++) {  //以第一个h的值作为分界线向后移动，以h为间隔进行插入排序
                temp = a[outer];
                inner = outer;
                while (inner > h - 1 && a[inner - h] >= temp) { //第一个条件，满足进入循环，需要inner至少在第一个outer（即 h）坐标上
                    a[inner] = a[inner - h];
                    inner -= h;
                }
                a[inner] = temp;
            }  // end for
            h = (h - 1) / 3;              // decrease h
        }  // end while(h>0)
    }

    /**
     * 划分
     * @param left
     * @param right
     * @return
     */
    public int partitionIt(int left, int right, long pivot) {
        int leftPtr = left - 1;           // right of first elem
        int rightPtr = right + 1;         // left of pivot
        while(true) {
            while(leftPtr < right && a[++leftPtr] < pivot)
                ;  // (nop)

            while(rightPtr > left && a[--rightPtr] > pivot)
                ;  // (nop)
            if(leftPtr >= rightPtr)        // if pointers cross,
                break;                      //    partition done
            else                           // not crossed, so
                swap(leftPtr, rightPtr);    //    swap elements
        }
        return leftPtr;                   // return partition
    }

    /**
     * 划分，不适用空操作的循环 154, 142, 165, 135, 131, 180, 154 这种数组会一直循环
     * @param left
     * @param right
     * @return
     */
    public int _partitionIt(int left, int right, long pivot) {
        int leftPtr = left;           // right of first elem
        int rightPtr = right;         // left of pivot
        while(true) {
            while(leftPtr < right && a[leftPtr] <= pivot)
                leftPtr ++;  // (nop)

            while(rightPtr > left && a[rightPtr] >= pivot)
                rightPtr--;  // (nop)

            if(leftPtr >= rightPtr)        // if pointers cross,
                break;                      //    partition done
            else                           // not crossed, so
                swap(leftPtr, rightPtr);    //    swap elements
        }
        return leftPtr;                   // return partition
    }

    /**
     * 快速排序一，选取最右侧为pivot
     */
    public void quickSort1() {
        recQuickSort1(0, nElems - 1);
    }

    public void recQuickSort1(int left, int right) {
        if (right - left <= 0)              // if size <= 1,
            return;                      //    already sorted
        else {
            long pivot = a[right];      // rightmost item
            // partition range
            int partition = partitionIt1(left, right, pivot);
            recQuickSort1(left, partition - 1);   // sort left side
            recQuickSort1(partition + 1, right);  // sort right side
        }
    }

    /**
     * 快速排序1，划分
     * @param left
     * @param right
     * @return
     */
    public int partitionIt1(int left, int right, long pivot) {
        int leftPtr = left - 1;           // right of first elem
        int rightPtr = right;         // left of pivot
        while(true) {
            while(a[++leftPtr] < pivot)
                ;  // (nop)

            while(rightPtr > left && a[--rightPtr] > pivot)
                ;  // (nop)
            if(leftPtr >= rightPtr)        // if pointers cross,
                break;                      //    partition done
            else                           // not crossed, so
                swap(leftPtr, rightPtr);    //    swap elements
        }
        swap(leftPtr, right);
        return leftPtr;                   // return partition
    }

    /**
     * 快速排序二，选取三个数字中位数为pivot
     */
    public void quickSort2() {
        recQuickSort2(0, nElems - 1);
    }

    public void recQuickSort2(int left, int right) {
        int size = right - left + 1;
        if (size <= 3)                  // manual sort if small
            manualSort(left, right);
        else {
            long median = medianOf3(left, right);
            int partition = partitionIt2(left, right, median);
            recQuickSort2(left, partition - 1);
            recQuickSort2(partition + 1, right);
        }
    }

    public void manualSort(int left, int right) {
        int size = right - left + 1;
        if (size <= 1)
            return;         // no sort necessary
        if (size == 2) {               // 2-sort left and right
            if (a[left] > a[right])
                swap(left, right);
            return;
        } else {               // 3-sort left, center, & right
            swapAndSort(left, right - 1, right);
        }
    }

    public long medianOf3(int left, int right) {
        int center = (left + right) / 2;
        swapAndSort(left, center, right);
        swap(center, right - 1);             // put pivot on right 两头的数据暂不考虑
        return a[right-1];          // return median value
    }

    /**
     * 交换数组中两个元素
     * @param left
     * @param center
     * @param right
     */
    private void swapAndSort(int left, int center, int right) {
        if (a[left] > a[center]) // order left & center
            swap(left, center);
        if (a[left] > a[right]) // order left & right
            swap(left, right);
        if (a[center] > a[right]) // order center & right
            swap(center, right);
    }

    public int partitionIt2(int left, int right, long pivot) {
        int leftPtr = left;             // right of first elem 左边已经是第一个数据，且不需要考虑
        int rightPtr = right -1 ;       // left of pivot right-1是pivot的位置

        while (true) {
            while (a[++leftPtr] < pivot)  // find bigger
                ;                                  //    (nop)
            while (a[--rightPtr] > pivot) // find smaller
                ;                                  //    (nop)
            if (leftPtr >= rightPtr)      // if pointers cross,
                break;                    //    partition done
            else                         // not crossed, so
                swap(leftPtr, rightPtr);  // swap elements
        }  // end while(true)
        swap(leftPtr, right - 1);         // restore pivot
        return leftPtr;                 // return pivot location
    }

    /**
     * 插入排序处理小于10个数据项的子数组
     */
    public void quickSort3() {
        recQuickSort3(0, nElems - 1);
    }

    public void recQuickSort3(int left, int right) {
        int size = right - left + 1;
        if (size < 10)                   // insertion sort if small
            insertionSort(left, right);
        else {
            long median = medianOf3(left, right);
            int partition = partitionIt2(left, right, median);
            recQuickSort3(left, partition - 1);
            recQuickSort3(partition + 1, right);
        }
    }

    public void insertionSort(int left, int right) {
        int in, out;
        //  sorted on left of out
        for (out = left + 1; out <= right; out++) {
            long temp = a[out];    // remove marked item
            in = out;                     // start shifts at out
            // until one is smaller,
            while (in > left && a[in - 1] >= temp) {
                a[in] = a[in - 1]; // shift item to right
                --in;                      // go left one position
            }
            a[in] = temp;          // insert marked item
        }
    }

    // 编程作业 7.1
    public int partitionIt(int left, int right) {
        int leftPtr = left - 1;           // right of first elem
        int rightPtr = right;         	  // left of pivot
        long pivot = a[right];
        while (true) {
            while (a[++leftPtr] < pivot)
                ;  // (nop)

            while (rightPtr > left && a[--rightPtr] > pivot)
                ;  // (nop)
            if (leftPtr >= rightPtr)        // if pointers cross,
                break;                      // partition done
            else
                swap(leftPtr, rightPtr);    // swap elements
        }  // end while(true)
        swap(leftPtr, right);
        return leftPtr;                   // return partition
    }

    /**
     * 通过划分查找中位数
     * @return
     */
    public long medianByPartition() {
        int midianIndex = nElems/2;
        return findMedianByPartition(0, nElems-1, midianIndex);
    }

    private long findMedianByPartition(int left, int right, int index) {
        int pivotIndex = partitionIt(left, right);
        if(pivotIndex==index) {
            return a[pivotIndex];
        } else if(pivotIndex>index){
            return findMedianByPartition(left, right-1, index);
        } else {
            return findMedianByPartition(left+1, right, index);
        }
    }

    public long findMedianByPartition2() {
        return a[findMedian(0, a.length - 1)];
    }

    public int findMedian(int left, int right) {
        int leftPtr = left - 1;           // right of first elem
        int rightPtr = right;         	  // left of pivot
        long pivot = a[right];
        while (true) {
            while (a[++leftPtr] < pivot)
                ;  // (nop)

            while (rightPtr > left && a[--rightPtr] > pivot)
                ;  // (nop)
            if (leftPtr >= rightPtr)        // if pointers cross,
                break;                      // partition done
            else
                swap(leftPtr, rightPtr);    // swap elements
        }  // end while(true)
        swap(leftPtr, right);

        int midindex = a.length / 2; // 中间位置

        if (leftPtr == midindex) {
            return leftPtr;
        } else if (leftPtr > midindex) {
            return findMedian(left, leftPtr - 1);
        } else {
            return findMedian(leftPtr + 1, right);
        }

    }

    public long findKth(int index) {
        return findMedianByPartition(0, nElems-1, index);
    }

    public long findKth2(int index) {
        if (index < 1 || index > a.length) {
            return -1;
        }
        return a[findIndex(0, a.length - 1, index)];
    }

    public int findIndex(int left, int right, int index) {
        int leftPtr = left - 1;           // right of first elem
        int rightPtr = right;         	  // left of pivot
        long pivot = a[right];
        while (true) {
            while (leftPtr < right && a[++leftPtr] < pivot)
                ;  // (nop)

            while (rightPtr > left && a[--rightPtr] > pivot)
                ;  // (nop)
            if (leftPtr >= rightPtr)        // if pointers cross,
                break;                      // partition done
            else
                // not crossed, so
                swap(leftPtr, rightPtr);    // swap elements
        }
        swap(leftPtr, right);

        if (leftPtr == index) {
            return leftPtr;
        } else if (leftPtr > index) {
            return findIndex(left, leftPtr - 1, index);
        } else {
            return findIndex(leftPtr + 1, right, index);
        }
    }
}