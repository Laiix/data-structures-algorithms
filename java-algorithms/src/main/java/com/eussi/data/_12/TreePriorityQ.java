package com.eussi.data._12;


import com.eussi.data._08.Tree;

/**
 * @author wangxueming
 * @create 2020-03-04 16:48
 * @description
 */
public class TreePriorityQ {
    // array in sorted order, from max at 0 to min at size-1
    private Tree<Integer> queTree;
    private int currentSize;

    public TreePriorityQ(int s) {
        queTree = new Tree<>();
    }

    public void insert(int item) {
        queTree.insert(item);
    }  // end insert()

    public int remove() {                                // assumes non-empty queue
        return queTree.removeMax().data;
    }

    public int peek() {
        // assumes non-empty queue
        return queTree.peekMax().data;
    }

    public boolean isEmpty() {
        return queTree.isEmpty();
    }

    public boolean isFull() {
        return false;
    }
}
