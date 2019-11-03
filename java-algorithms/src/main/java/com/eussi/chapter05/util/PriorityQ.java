package com.eussi.chapter05.util;

/**
 * @author wangxueming
 * @create 2019-11-02 14:45
 * @description
 */
public class PriorityQ {
    private SortedList sortedList;

    public PriorityQ() {
        sortedList = new SortedList();
    }

    public void insert(long item) {
        sortedList.insert(item);
    }

    public long remove() {
        return sortedList.remove().dData;
    }

    public boolean isEmpty() {
        return sortedList.isEmpty();
    }

    public void display() {
        sortedList.displayList();
    }
}
