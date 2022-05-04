package com.eussi.data._05;

/**
 * @author wangxueming
 * @create 2019-11-02 14:45
 * @description
 */
public class LinklistPriorityQ {
    private OrderedLinklist<Long> sortedList;

    public LinklistPriorityQ() {
        sortedList = new OrderedLinklist<>();
    }

    public void insert(long item) {
        sortedList.insert(item);
    }

    public long remove() {
        return sortedList.remove().data;
    }

    public boolean isEmpty() {
        return sortedList.isEmpty();
    }

    public void display() {
        sortedList.displayList();
    }
}
