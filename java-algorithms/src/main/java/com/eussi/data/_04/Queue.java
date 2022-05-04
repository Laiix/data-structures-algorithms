package com.eussi.data._04;

import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * 对列数据结构
 */
public class Queue<T> {
    private int maxSize;
    private T[] queArray;
    private int front;
    private int rear;
//   private int nItems;

    public Queue(int s) {         // constructor
        maxSize = s + 1;
        queArray = (T[]) new Object[maxSize];
        front = 0;
        rear = 0;
//      nItems = 0;
    }

    public void insert(T j) {  // put item at rear of queue
        queArray[rear++] = j;         // increment rear and insert
        if (rear == maxSize) {        // deal with wraparound
            rear = 0;
        }
//      nItems++;                     // one more item
    }

    public T remove() {         // take item from front of queue
        T temp = queArray[front++]; // get value and incr front
        if (front == maxSize) {          // deal with wraparound
            front = 0;
        }
//      nItems--;                      // one less item
        return temp;
    }

    public T peekFront() {   // peek at front of queue
        return queArray[front];
    }

    public boolean isEmpty() {   // true if queue is empty
//      return (nItems==0);
        return rear == front;
    }

    public boolean isFull() {    // true if queue is full
//      return (nItems==maxSize);
        return (rear + 1 == front //截断
                || front + maxSize - 1 == rear); //未截断
    }

    public int size() {          // number of items in queue
//      return nItems;
        if (rear >= front) {
            return rear - front;
        } else {
            return rear + maxSize - front;
        }
    }

    public void display() {
        print("Queue:");
        for (int i = 0; i < size(); i++) {
            print(queArray[(front + i) % maxSize] + " ");
        }
        println();
    }

    public void status() {
        println("status: [");
        print("\tcapacity: " + (maxSize-1));
        print(",maxSize: " + maxSize);
        print(",size: " + size());
        print(",front: " + front);
        print(",rear: " + rear);
        print(",isFull: " + isFull());
        println(",isEmpty: " + isEmpty());
        print("\tdata: ");
        for (int i = 0; i < size(); i++) {
            print(queArray[(front + i) % maxSize] + " ");
        }
        println();
        println("]");
    }
}