package com.eussi.data._12;


/**
 * @author wangxueming
 * @create 2020-03-04 16:23
 * @description
 */
public class PriorityQ {
    // array in sorted order, from max at 0 to min at size-1
    private int maxSize;
    private Heap<Integer> queHeap;

    public PriorityQ(int s) {
        maxSize = s;
        queHeap = new Heap<>(maxSize);
    }
    public void insert(int item) {
        queHeap.insert(item);
    }  // end insert()

    public int remove() {								// assumes non-empty queue
        return queHeap.remove().getData();
    }

    public int peek() {
        // assumes non-empty queue
        return queHeap.peek().getData();
    }

    public boolean isEmpty() {
        return queHeap.getCurrentSize() == 0;
    }

    public boolean isFull() {
        return queHeap.getCurrentSize() == maxSize;
    }

    public void display() {
        queHeap.displayHeap();
    }

}
