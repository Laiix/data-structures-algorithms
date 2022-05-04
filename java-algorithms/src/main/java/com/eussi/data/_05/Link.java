package com.eussi.data._05;

import static com.eussi.util.PrintUtil.print;

/**
 * @author wangxueming
 * @create 2019-10-29 9:16
 * @description
 */
public class Link<T> {
    public T data;              // data item
    public Link<T> next;              // next link in list

    public Link(T data) {
        this.data = data;
    }

    public void displayLink() {
        print("{" + data.toString() + "} ");
    }
}
