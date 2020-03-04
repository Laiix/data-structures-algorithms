package com.eussi.ch12_heap.util;

/**
 * @author wangxueming
 * @create 2020-03-04 16:33
 * @description
 */
public class Node1 {
    public int iData;              // data item (key)
    public Node1 leftChild;         // this node's left child
    public Node1 rightChild;        // this node's right child

    public Node1() {
    }

    public Node1(int key) {
        this.iData = key;
    }

    public void displayNode()      // display ourself
    {
        System.out.print(iData);
    }

    public int getKey() {
        return iData;
    }
}  // end class Node