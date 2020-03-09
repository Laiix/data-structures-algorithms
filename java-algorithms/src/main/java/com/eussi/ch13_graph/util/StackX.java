package com.eussi.ch13_graph.util;

/**
 * @author wangxueming
 * @create 2020-03-04 23:35
 * @description
 */
public class StackX<T> {
    private final int SIZE = 20;
    private T[] st;
    private int top;

    // ------------------------------------------------------------
    public StackX()           // constructor
    {
        st = (T[])new Object[SIZE];    // make array；创建想法来自thinking in java 4th P384
        top = -1;
    }

    public StackX(int size)           // constructor
    {
        st = (T[])new Object[size];    // make array；创建想法来自thinking in java 4th P384
        top = -1;
    }

    // ------------------------------------------------------------
    public void push(T j)   // put item on stack
    {
        st[++top] = j;
    }

    // ------------------------------------------------------------
    public T pop()          // take item off stack
    {
        return st[top--];
    }

    // ------------------------------------------------------------
    public T peek()         // peek at top of stack
    {
        return st[top];
    }

    // ------------------------------------------------------------
    public boolean isEmpty()  // true if nothing on stack
    {
        return (top == -1);
    }
// ------------------------------------------------------------
}  // end class StackX