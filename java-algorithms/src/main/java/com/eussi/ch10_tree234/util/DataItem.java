package com.eussi.ch10_tree234.util;

/**
 * @author wangxueming
 * @create 2020-02-26 1:41
 * @description
 */
public class DataItem {
    public long dData;          // one data item

    //--------------------------------------------------------------
    public DataItem(long dd)    // constructor
    {
        dData = dd;
    }

    //--------------------------------------------------------------
    public void displayItem()   // display item, format "/27"
    {
        System.out.print("/" + dData);
    }
}
