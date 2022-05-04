package com.eussi.data._05;


import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * 双端链表
 * @param <T>
 */
public class FirstLastLinkList<T> {
    private Link<T> first;               // ref to first link
    private Link<T> last;                // ref to last link

    public FirstLastLinkList() {
        first = null;                  // no links on list yet
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(T d) {
        Link<T> newLink = new Link<>(d);   // make new link

        if (isEmpty())                // if empty list,
            last = newLink;             // newLink <-- last
        newLink.next = first;          // newLink --> old first
        first = newLink;               // first --> newLink
    }

    public void insertLast(T d) {
        Link<T> newLink = new Link<>(d);   // make new link
        if (isEmpty())                // if empty list,
            first = newLink;            // first --> newLink
        else
            last.next = newLink;        // old last --> newLink
        last = newLink;                // newLink <-- last
    }

    public T deleteFirst() { // (assumes non-empty list)
        T temp = first.data;
        if (first.next == null)         // if only one item
            last = null;                // null <-- last
        first = first.next;            // first --> old next
        return temp;
    }

    public void displayList() {
        print("List (first-->last): ");
        Link<T> current = first;          // start at beginning
        while (current != null) {
            current.displayLink();      // print data
            current = current.next;     // move to next link
        }
        println("");
    }
}
