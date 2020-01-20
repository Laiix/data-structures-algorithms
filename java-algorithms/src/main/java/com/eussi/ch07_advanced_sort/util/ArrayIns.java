package com.eussi.ch07_advanced_sort.util;

// ArrayIns.java
// demonstrates simple version of quick sort
////////////////////////////////////////////////////////////////
public class ArrayIns {
    private long[] theArray;          // ref to array theArray
    private int nElems;               // number of data items

    public ArrayIns(int max)          // constructor
    {
        theArray = new long[max];      // create the array
        nElems = 0;                    // no items yet
    }

    public void insert(long value)    // put element into array
    {
        theArray[nElems] = value;      // insert it
        nElems++;                      // increment size
    }

    public void display()             // displays array contents
    {
        System.out.print("A=");
        for (int j = 0; j < nElems; j++)    // for each element,
            System.out.print(theArray[j] + " ");  // display it
        System.out.println("");
    }

    //------------------------------------------------------------------------------------
    //快速排序一，选取最右侧为pivot
    public void quickSort() {
        recQuickSort(0, nElems - 1);
    }

    public void recQuickSort(int left, int right) {
        if (right - left <= 0)              // if size <= 1,
            return;                      //    already sorted
        else                             // size is 2 or larger
        {
            long pivot = theArray[right];      // rightmost item
            // partition range
            int partition = partitionIt(left, right, pivot);
            recQuickSort(left, partition - 1);   // sort left side
            recQuickSort(partition + 1, right);  // sort right side
        }
    }  // end recQuickSort()

    public int partitionIt(int left, int right, long pivot) {
        int leftPtr = left - 1;           // left    (after ++)
        int rightPtr = right;           // right-1 (after --)  //枢纽排除在外
        while (true) {                            // find bigger item
            while (theArray[++leftPtr] < pivot)
                ;  // (nop)
            // find smaller item
            while (rightPtr > 0 && theArray[--rightPtr] > pivot)
                ;  // (nop)

            if (leftPtr >= rightPtr)      // if pointers cross,
                break;                    //    partition done
            else                         // not crossed, so
                swap(leftPtr, rightPtr);  //    swap elements
        }  // end while(true)
        swap(leftPtr, right);           // restore pivot
        return leftPtr;                 // return pivot location
    }  // end partitionIt()
    //-------------------------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------------------------
    //快速排序二，选取三个数字中位数为pivot
    public void quickSort2() {
        recQuickSort2(0, nElems - 1);
    }

    public void recQuickSort2(int left, int right) {
        int size = right - left + 1;
        if (size <= 3)                  // manual sort if small
            manualSort(left, right);
        else                           // quicksort if large
        {
            long median = medianOf3(left, right);
            int partition = partitionIt2(left, right, median);
            recQuickSort2(left, partition - 1);
            recQuickSort2(partition + 1, right);
        }
    }  // end recQuickSort()

    public void manualSort(int left, int right) {
        int size = right - left + 1;
        if (size <= 1)
            return;         // no sort necessary
        if (size == 2) {               // 2-sort left and right
            if (theArray[left] > theArray[right])
                swap(left, right);
            return;
        } else               // size is 3
        {               // 3-sort left, center, & right
            if (theArray[left] > theArray[right - 1])
                swap(left, right - 1);                // left, center
            if (theArray[left] > theArray[right])
                swap(left, right);                  // left, right
            if (theArray[right - 1] > theArray[right])
                swap(right - 1, right);               // center, right
        }
    }  // end manualSort()

    public long medianOf3(int left, int right) {
        int center = (left + right) / 2;
        // order left & center
        if (theArray[left] > theArray[center])
            swap(left, center);
        // order left & right
        if (theArray[left] > theArray[right])
            swap(left, right);
        // order center & right
        if (theArray[center] > theArray[right])
            swap(center, right);

        swap(center, right - 1);             // put pivot on right 两头的数据暂不考虑
        return theArray[right - 1];          // return median value
    }  // end medianOf3()

    public int partitionIt2(int left, int right, long pivot) {
        int leftPtr = left;             // right of first elem 左边已经是第一个数据，且不需要考虑
        int rightPtr = right - 1;       // left of pivot right-1是pivot的位置

        while (true) {
            while (theArray[++leftPtr] < pivot)  // find bigger
                ;                                  //    (nop)
            while (theArray[--rightPtr] > pivot) // find smaller
                ;                                  //    (nop)
            if (leftPtr >= rightPtr)      // if pointers cross,
                break;                    //    partition done
            else                         // not crossed, so
                swap(leftPtr, rightPtr);  // swap elements
        }  // end while(true)
        swap(leftPtr, right - 1);         // restore pivot
        return leftPtr;                 // return pivot location
    }  // end partitionIt()
    //-------------------------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------------------------
    //插入排序处理小于10个数据项的子数组
    public void quickSort3() {
        recQuickSort3(0, nElems - 1);
    }
    public void recQuickSort3(int left, int right) {
        int size = right - left + 1;
        if (size < 10)                   // insertion sort if small
            insertionSort(left, right);
        else                            // quicksort if large
        {
            long median = medianOf3(left, right);
            int partition = partitionIt2(left, right, median);
            recQuickSort3(left, partition - 1);
            recQuickSort3(partition + 1, right);
        }
    }  // end recQuickSort()
    public void insertionSort(int left, int right) {
        int in, out;
        //  sorted on left of out
        for (out = left + 1; out <= right; out++) {
            long temp = theArray[out];    // remove marked item
            in = out;                     // start shifts at out
            // until one is smaller,
            while (in > left && theArray[in - 1] >= temp) {
                theArray[in] = theArray[in - 1]; // shift item to right
                --in;                      // go left one position
            }
            theArray[in] = temp;          // insert marked item
        }  // end for
    }  // end insertionSort()

    public void swap(int dex1, int dex2)  // swap two elements
    {
        long temp = theArray[dex1];        // A into temp
        theArray[dex1] = theArray[dex2];   // B into A
        theArray[dex2] = temp;             // temp into B
    }  // end swap(
}  // end class ArrayIns

