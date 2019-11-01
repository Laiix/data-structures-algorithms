package com.eussi.chapter05.helper;

/**
 * @author wangxueming
 * @create 2019-11-01 0:14
 * @description
 */
public class DoubleLink {
    public long dData;                 // data item
    public DoubleLink next;                  // next link in list
    public DoubleLink previous;              // previous link in list
    // -------------------------------------------------------------
    public DoubleLink(long d)                // constructor
    { dData = d; }
    // -------------------------------------------------------------
    public void displayLink()          // display this link
    { System.out.print(dData + " "); }
// -------------------------------------------------------------
}
