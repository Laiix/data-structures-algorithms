package com.eussi.data._04;

import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * @author wangxueming
 * @create 2019-10-28 20:29
 * @description
 */
public class Deque {
    private int maxSize;
    private long[] queArray;
    private int front;
    private int rear;
    private int nItems;

    public Deque(int size) {
        maxSize = size;
        queArray = new long[maxSize];
        front = 0;
        rear = 0;
        nItems = 0;
    }

    public void insertLeft(long value) {//左侧插入时，front为队尾
        if (front == 0) {
            front = maxSize;
        }
        queArray[--front] = value;
        nItems++;
    }

    public void insertRight(long value) {
        queArray[rear++] = value;
        if (rear == maxSize) {
            rear = 0;
        }
        nItems++;
    }

    public long removeLeft() {
        long temp = queArray[front++];
        if (front == maxSize) {
            front = 0;
        }
        nItems--;
        return temp;
    }

    public long removeRight() {
        if (rear == 0) {
            rear = maxSize;
        }
        long temp = queArray[--rear];

        nItems--;
        return temp;
    }

    public long peekLeft() {
        return queArray[front];
    }

    public long peekRight() {
        return queArray[rear-1];
    }

    public boolean isEmpty() {
        return nItems == 0;
    }

    public boolean isFull() {
        return nItems == maxSize;
    }

    public int size() {
        return nItems;
    }

    public void display() {
        print("Deque: ");
        for (int i = 0; i < size(); i++) {
            print(queArray[(front + i) % maxSize] + " ");
        }
        println();
    }
}
