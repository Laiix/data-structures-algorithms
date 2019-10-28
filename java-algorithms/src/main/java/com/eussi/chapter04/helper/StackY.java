package com.eussi.chapter04.helper;

/**
 * @author wangxueming
 * @create 2019-10-28 20:58
 * @description
 */
public class StackY {
    private Deque stackQueue;

    public StackY(int size) {
        stackQueue = new Deque(size);
    }

    public void push(long value) {
        stackQueue.insertRight(value);
    }

    public long pop() {
        return stackQueue.removeRight();
    }

    public long peek() {
        return stackQueue.peekRight();
    }

    public boolean isEmpty() {
        return stackQueue.isEmpty();
    }

    public boolean isFull() {
        return stackQueue.isFull();
    }
}
