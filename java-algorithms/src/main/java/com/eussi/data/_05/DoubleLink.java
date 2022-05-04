package com.eussi.data._05;

import static com.eussi.util.PrintUtil.print;

/**
 * @author wangxueming
 * @create 2019-11-01 0:14
 * @description
 */
public class DoubleLink<T> {
    public T data;                 // data item
    public DoubleLink<T> next;                  // next link in list
    public DoubleLink<T> previous;              // previous link in list

    //用于练习中的电子表格，命名清晰更好理解代码
    public DoubleLink<T> goRight;
    public DoubleLink<T> goDown;

    public DoubleLink(T d) {
        data = d;
    }

    public void displayLink() {
        print("{" + data.toString() + "} ");
    }
}
