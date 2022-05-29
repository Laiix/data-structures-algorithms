package com.eussi.data._12;

/**
 * @author wangxueming
 * @create 2020-03-04 1:25
 * @description
 */
public class HeapNode<T extends Comparable<T>> {
    private T data;             // data item (key)

    public HeapNode(T data) {
        this.data = data;
    }



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data + "";
    }
}