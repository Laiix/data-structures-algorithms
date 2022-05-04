package com.eussi.ch05_linklist.util;

/**
 * @author wangxueming
 * @create 2019-11-02 14:52
 * @description
 */
public class DuQueue {
    private DoublyLinkedList doublyLinkedList;

    public DuQueue() {
        doublyLinkedList = new DoublyLinkedList();
    }

    public void insertLeft(long value) {
        doublyLinkedList.insertFirst(value);
    }

    public void insertRight(long value) {
        doublyLinkedList.insertLast(value);
    }

    public long removeLeft() {
        return doublyLinkedList.deleteFirst().dData;
    }

    public long removeRight() {
        return doublyLinkedList.deleteLast().dData;
    }

    public boolean isEmpy() {
        return doublyLinkedList.isEmpty();
    }

    public void display() {
        doublyLinkedList.displayForward();
    }

}
