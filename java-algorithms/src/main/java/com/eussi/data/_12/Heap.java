package com.eussi.data._12;


import static com.eussi.util.Func.repeatPrint;
import static com.eussi.util.Func.repeatPrintln;
import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

public class Heap<T extends Comparable<T>> {
    private HeapNode<T>[] heapArray;
    private int maxSize;           // size of array
    private int currentSize;       // number of nodes in array

    private Sort sort = Sort.desc; //默认降序

    public enum Sort{//堆升序或者降序排列
        asc, desc
    }

    public Heap(int mx) {
        maxSize = mx;
        currentSize = 0;
        heapArray = new HeapNode[maxSize];  // create array
    }

    public Heap(int mx, Sort sort) {
        maxSize = mx;
        currentSize = 0;
        heapArray = new HeapNode[maxSize];  // create array
        this.sort = sort;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void insert(T... data) {
        for (T datum : data) {
            insert(datum);
        }
    }

    public boolean insert(T data) {
        if (currentSize == maxSize)
            return false;
        HeapNode<T> newNode = new HeapNode<>(data);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    }

    public void trickleUp(int index) {
        int parent = (index - 1) / 2;
        HeapNode<T> bottom = heapArray[index];

        while (index > 0 &&     //到根节点，但是根节点不能进入循环
                (sort==Sort.asc
                        ? heapArray[parent].getData().compareTo(bottom.getData()) > 0
                        : heapArray[parent].getData().compareTo(bottom.getData()) < 0
                )
        ) {
            heapArray[index] = heapArray[parent];  // move it down
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapArray[index] = bottom;
    }

    public HeapNode<T> remove() {
        HeapNode<T> root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    public void trickleDown(int index) {
        int largerChild;
        HeapNode<T> top = heapArray[index];       // save root
        //index < currentSize / 2 根据完全树的最后一层节点特点判断 ，1,2,4,8,16
        while (index < currentSize / 2) {                               //    least one child,
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            // find larger child
            if (rightChild < currentSize &&  // (rightChild exists?)
                    (sort==Sort.asc
                            ? heapArray[leftChild].getData().compareTo(heapArray[rightChild].getData())>0
                            : heapArray[leftChild].getData().compareTo(heapArray[rightChild].getData())<0
                    )
            )
                largerChild = rightChild;
            else
                largerChild = leftChild;
            // top >= largerChild?
            if (sort==Sort.asc
                    ? top.getData().compareTo(heapArray[largerChild].getData())<=0
                    : top.getData().compareTo(heapArray[largerChild].getData())>=0)
                break;
            // shift child up
            heapArray[index] = heapArray[largerChild];
            index = largerChild;            // go down
        }
        heapArray[index] = top;            // root to index
    }

    public void displayHeap() {
        print("heapArray: ");    // array format
        for (int m = 0; m < currentSize; m++)
            if (heapArray[m] != null)
                print(heapArray[m].getData() + " ");
            else
                print("-- ");
        println();

        // heap format
        println("treeHeap: ");
        int dotWidth = 64;
        int nBlanks = dotWidth/2;
        int itemsPerRow = 1;    //记录每行中应该打印的元素数
        int column = 0;   //当前行数目
        int j = 0;                          // current item
        repeatPrintln(dotWidth, ".");

        while (currentSize > 0) {
            if (column == 0)                  // first item in row?
                repeatPrint(nBlanks, " ");
            // display item
            print(heapArray[j].getData());

            if (++j == currentSize)           // done?
                break;

            if (++column == itemsPerRow) {
                nBlanks /= 2;                 // half the blanks
                itemsPerRow *= 2;             // twice the items
                column = 0;                   // start over on
                println();         //    new row
            } else                             // next item on row
                repeatPrint(nBlanks * 2 - 2, " ");
        }  // end for
        println(); // dotted bottom line
        repeatPrintln(dotWidth, ".");
        println();
    }

    public boolean change(int index, T newValue) {
        if (index < 0 || index >= currentSize)
            return false;
        T oldValue = heapArray[index].getData(); // remember old
        heapArray[index].setData(newValue);  // change to new

        if (sort==Sort.asc
                ? oldValue.compareTo(newValue)>0
                : oldValue.compareTo(newValue)<0)             // if raised,
            trickleUp(index);                // trickle it up
        else                                // if lowered,
            trickleDown(index);              // trickle it down
        return true;
    }

    public void insertAt(int index, HeapNode<T> newNode) {
        heapArray[index] = newNode;
    }

    public void incrementSize() {
        currentSize++;
    }

    public void displayArray() {
        for (int j = 0; j < maxSize; j++)
            print(heapArray[j].getData() + " ");
        println("");
    }

    // 编程作业 12.2
    public void toss(T key) {
        this.heapArray[currentSize++] = new HeapNode<>(key);
    }

    // 编程作业 12.2
    public void restoreHeap() {
        for (int j = currentSize / 2 - 1; j >= 0; j--) {
            trickleDown(j);
        }
    }

    // 编程作业 12.3
    public HeapNode<T> peek() {
        return heapArray[0];
    }

    // 编程作业 12.3
    public int getCurrentSize() {
        return currentSize;
    }
}
