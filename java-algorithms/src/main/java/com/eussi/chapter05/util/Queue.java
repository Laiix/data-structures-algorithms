package com.eussi.chapter05.util;

/**
 * @author wangxueming
 * @create 2019-11-02 22:54
 * @description
 */
public class Queue {
    private CircleList circleList;
    private int nItems;

    // --------------------------------------------------------------
    public Queue(int s) // constructor
    {
        circleList = new CircleList();
        nItems = 0;
    }

    // --------------------------------------------------------------
    public void insert(long value) // put item at rear of queue
    {
        circleList.insert(value);
    }

    // --------------------------------------------------------------
    public long remove() // take item from front of queue
    {
        return circleList.remove();
    }

    // --------------------------------------------------------------
    public long peek() // peek at front of queue
    {
        return circleList.peek();
    }

    // --------------------------------------------------------------
    public boolean isEmpty() // true if queue is empty
    {
        return (circleList.size() == 0);
    }

    // --------------------------------------------------------------
    public int size() // number of items in queue
    {
        return circleList.size();
    }

    // --------------------------------------------------------------
    public void display() {
        circleList.display();
    }
}
