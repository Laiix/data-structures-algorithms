package com.eussi.data._05;

import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * 有序链表
 */
public class OrderedLinklist<T extends Comparable<T>> {
    private Link<T> first;                 // ref to first item

    public OrderedLinklist() {
        first = null;
    }

    public OrderedLinklist(Link<T>[] linkArr) {                               // as argument)
        first = null;                        // initialize list
        for (int j = 0; j < linkArr.length; j++)  // copy array
            insert(linkArr[j].data);             // to list
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insert(T key) {
        Link<T> newLink = new Link<>(key);    // make new link
        Link<T> previous = null;            // start at first
        Link<T> current = first;
        // until end of list,
        while (current != null
                && key.compareTo(current.data) > 0) {                             // or key > current,
            previous = current;
            current = current.next;       // go to next item
        }
        if (current == first)               // at beginning of list
            first = newLink;              // first --> newLink
        else                             // not at beginning
            previous.next = newLink;      // old prev --> newLink
        newLink.next = current;          // newLink --> old currnt
    }  // end insert()

    public Link<T> remove() {  // (assumes non-empty list)
        Link<T> temp = first;               // save first
        first = first.next;              // delete first
        return temp;                     // return value
    }

    public void displayList() {
        print("List (first-->last): ");
        Link<T> current = first;       // start at beginning of list
        while (current != null) {
            current.displayLink();   // print data
            current = current.next;  // move to next link
        }
        println("");
    }

    public void delete(T key) {
        Link<T> previous = null;          // start at first
        Link<T> current = first;
        // until end of list,
        while (current != null && key.compareTo(current.data)!=0) {                           // or key == current,
            previous = current;
            current = current.next;     // go to next link
        }
        // disconnect link
        if (previous == null)             //   if beginning of list
            first = first.next;         //      delete first link
        else                           //   not at beginning
            previous.next = current.next; //    delete current link
    }

    public Link<T> find(T key) {
        Link<T> current = first;          // start at first
        // until end of list,
        while (current != null && current.data.compareTo(key)<=0) {                           // or key too small,
            if (current.data == key)    // is this the link?
                return current;          // found it, return link
            current = current.next;     // go to next item
        }
        return null;                   // didn't find it
    }
}