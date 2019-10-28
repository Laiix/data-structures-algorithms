package com.eussi.chapter04.helper;

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
        rear = -1;
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
        if (rear == maxSize - 1) {
            rear = -1;
        }
        queArray[++rear] = value;
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
        long temp = queArray[rear--];
        if (rear == -1) {
            rear = maxSize - 1;
        }
        nItems--;
        return temp;
    }

    public long peekLeft() {
        return queArray[front];
    }

    public long peekRight() {
        return queArray[rear];
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
        System.out.print("队列为：");
        if (nItems == 0) {
            System.out.println("空。");
            return;
        }
        if (rear >= front) {
            for (int i = front; i <= rear; i++) {
                System.out.print(queArray[i] + " ");
            }
        } else {
            for (int i = front; i < maxSize; i++) {
                System.out.print(queArray[i] + " ");
            }
            for (int i = 0; i <= rear; i++) {
                System.out.print(queArray[i] + " ");
            }
        }
        System.out.println();
    }
}
