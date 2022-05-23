package com.eussi.data._10;

import static com.eussi.util.PrintUtil.print;

/**
 * @author wangxueming
 * @create 2020-02-26 1:41
 * @description
 */
public class DataItem<T> {
    public T data;          // one data item

    public DataItem(T data) {
        this.data = data;
    }

    public void displayItem() {
        print("/" + data);
    }
}
