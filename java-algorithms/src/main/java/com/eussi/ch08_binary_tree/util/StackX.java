package com.eussi.ch08_binary_tree.util;

/**
 * @author wangxueming
 * @create 2020-01-23 14:03
 * @description
 */
public class StackX {
    private int maxSize;
    private CharTree[] stackArray;
    private int top;

    // --------------------------------------------------------------
    public StackX(int size)      // constructor
    {
        maxSize = size;
        stackArray = new CharTree[maxSize];
        top = -1;
    }

    // --------------------------------------------------------------
    public void push(CharTree j)     // put item on top of stack
    {
        stackArray[++top] = j;
    }

    // --------------------------------------------------------------
    public CharTree pop()            // take item from top of stack
    {
        return stackArray[top--];
    }

    // --------------------------------------------------------------
    public CharTree peek()           // peek at top of stack
    {
        return stackArray[top];
    }

    // --------------------------------------------------------------
    public boolean isEmpty()    // true if stack is empty
    {
        return (top == -1);
    }

    // --------------------------------------------------------------
    public boolean isFull()     // true if stack is full
    {
        return (top == maxSize - 1);
    }

    // --------------------------------------------------------------
    public int size()           // return size
    {
        return top + 1;
    }

    // --------------------------------------------------------------
    public CharTree peekN(int n)     // peek at index n
    {
        return stackArray[n];
    }

    // --------------------------------------------------------------
    public void displayStack(String s) {
        System.out.print(s);
        System.out.print("Stack (bottom-->top): ");
        for (int j = 0; j < size(); j++) {
            System.out.print(peekN(j));
            System.out.print(' ');
        }
        System.out.println("");
    }
    // --------------------------------------------------------------

}
