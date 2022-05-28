package com.eussi.data._11;

/**
 * @author wangxueming
 * @create 2020-02-29 2:05
 * @description
 */
public class DataItem<T> {
    private T key;

    public DataItem(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }
}