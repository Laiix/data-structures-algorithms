package com.eussi.ch11_hash.util;

/**
 * @author wangxueming
 * @create 2020-03-03 21:10
 * @description
 */
public class Node {
    public int iData;              // data item (key)
    public Node leftChild;         // this node's left child
    public Node rightChild;        // this node's right child

    public Node() {
    }

    public Node(int key) {
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