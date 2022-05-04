package com.eussi.data._04;


import static com.eussi.util.PrintUtil.*;

/**
 * 对列数据结构，内部计数使用一个字段表示
 */
public class QueueWithCount<T> {
    private int maxSize;
    private T[] queArray;
    private int front;
    private int rear;
    private int nItems;

    public QueueWithCount(int s) {
        maxSize = s;
        queArray = (T[]) new Object[maxSize];
        front = 0;
        rear = 0;
        nItems = 0;
    }

    public void insert(T j) {  // put item at rear of queue

        queArray[rear++] = j;         // increment rear and insert
        if (rear == maxSize)         // deal with wraparound
            rear = 0;
        nItems++;                     // one more item
    }

    public T remove() {         // take item from front of queue
        T temp = queArray[front++]; // get value and incr front
        if (front == maxSize)           // deal with wraparound
            front = 0;
        nItems--;                      // one less item
        return temp;
    }

    public T peekFront() {     // peek at front of queue
        return queArray[front];
    }

    public boolean isEmpty() {   // true if queue is empty
        return (nItems == 0);
    }

    public boolean isFull() {   // true if queue is full
        return (nItems == maxSize);
    }

    public int size() {        // number of items in queue
        return nItems;
    }

    public void display() {
        print("Queue:");
        for (int i = 0; i < nItems; i++) {
            print(queArray[(front + i) % maxSize] + " ");
        }
        println();
    }
}