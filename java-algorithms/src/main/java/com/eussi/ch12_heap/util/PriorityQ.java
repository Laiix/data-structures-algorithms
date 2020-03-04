package com.eussi.ch12_heap.util;

/**
 * @author wangxueming
 * @create 2020-03-04 16:23
 * @description
 */
public class PriorityQ {
    // array in sorted order, from max at 0 to min at size-1
    private int maxSize;
    private Heap1 queHeap;

    // -------------------------------------------------------------
    public PriorityQ(int s)          // constructor
    {
        maxSize = s;
        queHeap = new Heap1(maxSize);
    }

    // -------------------------------------------------------------
    public void insert(int item)    // insert item
    {
        queHeap.insert(item);
    }  // end insert()

    // -------------------------------------------------------------
    public int remove()             // remove minimum item
    {								// assumes non-empty queue
        return queHeap.remove().getKey();
    }

    // -------------------------------------------------------------
    public int peek()            // peek at minimum item
    {
        // assumes non-empty queue
        return queHeap.peek().getKey();
    }

    // -------------------------------------------------------------
    public boolean isEmpty()         // true if queue is empty
    {
        return queHeap.getCurrentSize() == 0;
    }

    // -------------------------------------------------------------
    public boolean isFull()          // true if queue is full
    {
        return queHeap.getCurrentSize() == maxSize;
    }

    public void display() {
        queHeap.displayHeap();
    }
    // -------------------------------------------------------------

}
