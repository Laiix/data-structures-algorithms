package com.eussi.data._05;


/**
 * @author wangxueming
 * @create 2019-11-02 14:52
 * @description
 */
public class DuQueue<T> {
    private DoublyLinkedList<T> doublyLinkedList;

    public DuQueue() {
        doublyLinkedList = new DoublyLinkedList<>();
    }

    public void insertLeft(T value) {
        doublyLinkedList.insertFirst(value);
    }

    public void insertRight(T value) {
        doublyLinkedList.insertLast(value);
    }

    public T removeLeft() {
        return doublyLinkedList.deleteFirst().data;
    }

    public T removeRight() {
        return doublyLinkedList.deleteLast().data;
    }

    public boolean isEmpy() {
        return doublyLinkedList.isEmpty();
    }

    public void display() {
        doublyLinkedList.displayForward();
    }

}
