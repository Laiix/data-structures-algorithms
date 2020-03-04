package com.eussi.ch12_heap.util;

/**
 * @author wangxueming
 * @create 2020-03-04 16:48
 * @description
 */
public class TreePriorityQ {
    // array in sorted order, from max at 0 to min at size-1
    private Tree queTree;
    private int currentSize;

    // -------------------------------------------------------------
    public TreePriorityQ(int s)          // constructor
    {
        queTree = new Tree();
    }

    // -------------------------------------------------------------
    public void insert(int item)    // insert item
    {
        queTree.insert(item);
    }  // end insert()

    // -------------------------------------------------------------
    public int remove()             // remove maximum item
    {                                // assumes non-empty queue
        return queTree.removeMax().getKey();
    }

    // -------------------------------------------------------------
    public int peek()            // peek at minimum item
    {
        // assumes non-empty queue
        return queTree.peekMax().getKey();
    }

    // -------------------------------------------------------------
    public boolean isEmpty()         // true if queue is empty
    {
        return queTree.isEmpty();
    }

    // -------------------------------------------------------------
    public boolean isFull()          // true if queue is full
    {
        return false;
    }
    // -------------------------------------------------------------

}
