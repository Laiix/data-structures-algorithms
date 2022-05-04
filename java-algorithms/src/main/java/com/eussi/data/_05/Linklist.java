package com.eussi.data._05;


import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

public class Linklist<T> {
    private Link<T> first;            // ref to first link on list

    public Linklist() {
        first = null;               // no links on list yet
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void setFirst(Link<T> f)  {
        first = f;
    }

    public Link<T> getFirst() {
        return first;
    }

    // insert at start of list
    public void insertFirst(T data) {                           // make new link
        Link<T> newLink = new Link<>(data);
        newLink.next = first;       // newLink --> old first
        first = newLink;            // first --> newLink
    }

    public Link<T> deleteFirst() { // (assumes list not empty)
        Link<T> temp = first;          // save reference to link
        first = first.next;         // delete it: first-->old next
        return temp;                // return deleted link
    }

    public ListIterator<T> getIterator() {
        return new ListIterator<>(this);  // initialized with
    }

    public Link<T> find(T data) {                           // (assumes non-empty list)
        Link<T> current = first;              // start at 'first'
        while(!current.data.equals(data))  {
            if(current.next == null)        // if end of list,
                return null;                 // didn't find it
            else                            // not end of list,
                current = current.next;      // go to next link
        }
        return current;                    // found it
    }

    public Link<T> delete(T data) {                           // (assumes non-empty list)
        Link<T> current = first;              // search for link
        Link<T> previous = null;
        while(!current.data.equals(data)) {
            if(current.next == null)
                return null;                 // didn't find it
            else {
                previous = current;          // go to next link
                current = current.next;
            }
        }                               // found it
        if(current == first)               // if first link,
            first = first.next;             //    change first
        else                               // otherwise,
            previous.next = current.next;   //    bypass it
        return current;
    }

    public void displayList() {
        print("List (first-->last): ");
        Link<T> current = first;       // start at beginning of list
        while (current != null) {
            current.displayLink();   // print data
            current = current.next;  // move to next link
        }
        println();
    }
}
