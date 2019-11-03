package com.eussi.chapter05.util;

// SortedList.java
// demonstrates sorted list
// to run this program: C>java SortedListApp
////////////////////////////////////////////////////////////////
public class SortedList2 {
    private Link2 first;            // ref to first item on list

    // -------------------------------------------------------------
    public SortedList2()            // constructor (no args)
    {
        first = null;
    }                    // initialize list

    // -------------------------------------------------------------
    public SortedList2(Link2[] linkArr)  // constructor (array
    {                               // as argument)
        first = null;                        // initialize list
        for (int j = 0; j < linkArr.length; j++)  // copy array
            insert(linkArr[j]);             // to list
    }

    // -------------------------------------------------------------
    public void insert(Link2 k)     // insert (in order)
    {
        Link2 previous = null;            // start at first
        Link2 current = first;
        // until end of list,
        while (current != null && k.dData > current.dData) {                             // or key > current,
            previous = current;
            current = current.next;       // go to next item
        }
        if (previous == null)               // at beginning of list
            first = k;                    // first --> k
        else                             // not at beginning
            previous.next = k;            // old prev --> k
        k.next = current;                // k --> old currnt
    }  // end insert()

    // -------------------------------------------------------------
    public Link2 remove()           // return & delete first link
    {                           // (assumes non-empty list)
        Link2 temp = first;               // save first
        first = first.next;              // delete first
        return temp;                     // return value
    }
}  // end class SortedList
////////////////////////////////////////////////////////////////
