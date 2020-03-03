package com.eussi.ch12_heap.util;

/**
 * @author wangxueming
 * @create 2020-03-04 1:25
 * @description
 */
public class Node {
    private int iData;             // data item (key)

    // -------------------------------------------------------------
    public Node(int key)           // constructor
    {
        iData = key;
    }

    // -------------------------------------------------------------
    public int getKey() {
        return iData;
    }

    // -------------------------------------------------------------
    public void setKey(int id) {
        iData = id;
    }

    @Override
    public String toString() {
        return  iData + "";
    }
// -------------------------------------------------------------
}