package com.eussi.ch05_Linklist.util;

/**
 * @author wangxueming
 * @create 2019-10-29 9:16
 * @description
 */
public class Link2 {
    public long dData;                 // data item
    public Link2 next;                  // next link in list
    // -------------------------------------------------------------
    public Link2(long d)                // constructor
    { dData = d; }
    // -------------------------------------------------------------
    public void displayLink()          // display this link
    { System.out.print(dData + " "); }
}
