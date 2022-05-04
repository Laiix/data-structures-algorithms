package com.eussi.data._05;


/**
 * @author wangxueming
 * @create 2019-11-02 22:54
 * @description
 */
public class LinklistQueue<T> {
    private CircleLinklist<T> circleList;
    private int nItems;

    public LinklistQueue(int s) {
        circleList = new CircleLinklist<>();
        nItems = 0;
    }

    public void insert(T value) {
        circleList.insert(value);
    }

    public T remove() {
        return circleList.remove();
    }

    public T peek() {
        return circleList.peek();
    }

    public boolean isEmpty() {
        return (circleList.size() == 0);
    }

    public int size() {
        return circleList.size();
    }

    public void display() {
        circleList.display();
    }
}
