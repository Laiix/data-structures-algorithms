package com.eussi.data._05;

import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * 双向对列
 * @param <T>
 */
public class DoublyLinkedList<T> {
    private DoubleLink<T> first;               // ref to first item
    private DoubleLink<T> last;                // ref to last item

    public DoublyLinkedList() {
        first = null;                  // no items on list yet
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(T dd) {
        DoubleLink<T> newLink = new DoubleLink<>(dd);   // make new link

        if (isEmpty())                // if empty list,
            last = newLink;             // newLink <-- last
        else
            first.previous = newLink;   // newLink <-- old first
        newLink.next = first;          // newLink --> old first
        first = newLink;               // first --> newLink
    }

    public void insertLast(T dd) {
        DoubleLink<T> newLink = new DoubleLink<>(dd);   // make new link
        if (isEmpty())                // if empty list,
            first = newLink;            // first --> newLink
        else {
            last.next = newLink;        // old last --> newLink
            newLink.previous = last;    // old last <-- newLink
        }
        last = newLink;                // newLink <-- last
    }

    public DoubleLink<T> deleteFirst() {  // (assumes non-empty list)
        DoubleLink<T> temp = first;
        if (first.next == null)         // if only one item
            last = null;                // null <-- last
        else
            first.next.previous = null; // null <-- old next
        first = first.next;            // first --> old next
        return temp;
    }

    public DoubleLink<T> deleteLast() {  // (assumes non-empty list)
        DoubleLink<T> temp = last;
        if (first.next == null)         // if only one item
            first = null;               // first --> null
        else
            last.previous.next = null;  // old previous --> null
        last = last.previous;          // old previous <-- last
        return temp;
    }

    // insert dd just after key
    public boolean insertAfter(T key, T dd) { // (assumes non-empty list)
        DoubleLink<T> current = first;          // start at beginning
        while (!current.data.equals(key)) {
            current = current.next;     // move to next link
            if (current == null)
                return false;            // didn't find it
        }
        DoubleLink<T> newLink = new DoubleLink<>(dd);   // make new link

        if (current == last) {
            newLink.next = null;        // newLink --> null
            last = newLink;             // newLink <-- last
        } else {
            newLink.next = current.next; // newLink --> old next
            // newLink <-- old next
            current.next.previous = newLink;
        }
        newLink.previous = current;    // old current <-- newLink
        current.next = newLink;        // old current --> newLink
        return true;                   // found it, did insertion
    }

    public DoubleLink<T> deleteKey(T key) {                              // (assumes non-empty list)
        DoubleLink<T> current = first;          // start at beginning
        while (!current.data.equals(key)) {
            current = current.next;     // move to next link
            if (current == null)
                return null;             // didn't find it
        }
        if (current == first)             // found it; first item?
            first = current.next;       // first --> old next
        else                           // not first
            // old previous --> old next
            current.previous.next = current.next;

        if (current == last)              // last item?
            last = current.previous;    // old previous <-- last
        else                           // not last
            // old previous <-- old next
            current.next.previous = current.previous;
        return current;                // return value
    }

    public void displayForward() {
        print("List (first-->last): ");
        DoubleLink<T> current = first;          // start at beginning
        while (current != null)         // until end of list,
        {
            current.displayLink();      // display data
            current = current.next;     // move to next link
        }
        println();
    }

    public void displayBackward() {
        print("List (last-->first): ");
        DoubleLink<T> current = last;           // start at end
        while (current != null)         // until start of list,
        {
            current.displayLink();      // display data
            current = current.previous; // move to previous link
        }
        println("");
    }
}
