package com.eussi.ch07_advanced_sort.util;

// ArraySh.java
// demonstrates shell sort
//--------------------------------------------------------------
public class ArraySh {
    private long[] theArray;          // ref to array theArray
    private int nElems;               // number of data items

    //--------------------------------------------------------------
    public ArraySh(int max)           // constructor
    {
        theArray = new long[max];      // create the array
        nElems = 0;                    // no items yet
    }

    //--------------------------------------------------------------
    public void insert(long value)    // put element into array
    {
        theArray[nElems] = value;      // insert it
        nElems++;                      // increment size
    }

    //--------------------------------------------------------------
    public void display()             // displays array contents
    {
        System.out.print("A=");
        for (int j = 0; j < nElems; j++)    // for each element,
            System.out.print(theArray[j] + " ");  // display it
        System.out.println("");
    }

    //--------------------------------------------------------------
    public void shellSort() {
        int inner, outer;
        long temp;

        int h = 1;                     // find initial value of h
        while (h <= nElems / 3)
            h = h * 3 + 1;                // (1, 4, 13, 40, 121, ...)

        while (h > 0)                     // decreasing h, until h=1
        {
            // h-sort the file
            for (outer = h; outer < nElems; outer++) {  //以第一个h的值作为分界线向后移动，以h为间隔进行插入排序
                temp = theArray[outer];
                inner = outer;
                while (inner > h - 1 && theArray[inner - h] >= temp) { //第一个条件，满足进入循环，需要inner至少在第一个outer（即 h）坐标上
                    theArray[inner] = theArray[inner - h];
                    inner -= h;
                }
                theArray[inner] = temp;
            }  // end for
            h = (h - 1) / 3;              // decrease h
        }  // end while(h>0)
    }  // end shellSort()
//--------------------------------------------------------------
}  // end class ArraySh
////////////////////////////////////////////////////////////////

