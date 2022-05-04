package com.eussi.data._05;// ListIterator.java


public class ListIterator<T> {
    private Link<T> current;          // current link
    private Link<T> previous;         // previous link
    private Linklist<T> ourList;      // our linked list

    public ListIterator(Linklist<T> list) {
        ourList = list;
        reset();
    }

    public void clearAll() {
        ourList.setFirst(null);
        reset();
    }

    public void reset() {
        current = ourList.getFirst();
        previous = null;
    }

    public boolean atEnd() {
        return (current.next == null);
    }

    public void nextLink() {
        previous = current;
        current = current.next;
    }

    public Link<T> getCurrent() {
        return current;
    }

    public void insertAfter(T dd) {                                 // current link
        Link<T> newLink = new Link<>(dd);

        if (ourList.isEmpty()) {
            ourList.setFirst(newLink);
            current = newLink;
        } else {
            newLink.next = current.next;
            current.next = newLink;
            nextLink();              // point to new link
        }
    }

    public void insertBefore(T dd) {                                 // current link
        Link<T> newLink = new Link<>(dd);

        if (previous == null) {                        // (or empty list)
            newLink.next = ourList.getFirst();
            ourList.setFirst(newLink);
            reset();
        } else {
            newLink.next = previous.next;
            previous.next = newLink;
            current = newLink;
        }
    }

    public T deleteCurrent() {
        T value = current.data;
        if (previous == null) {
            ourList.setFirst(current.next);
            reset();
        } else {
            previous.next = current.next;
            if (atEnd())
                reset();
            else
                current = current.next;
        }
        return value;
    }
}