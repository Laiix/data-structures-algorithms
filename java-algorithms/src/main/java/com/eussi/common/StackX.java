package com.eussi.common;

import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * 栈数据结构
 * @param <T>
 */
public class StackX<T> {
    private int maxSize;        // size of stack array
    private T[] stackArray;
    private int top;            // top of stack

    public StackX(int s) {       // constructor
        maxSize = s;             // set array size
        stackArray = (T[]) new Object[maxSize];  // create array
        top = -1;                // no items yet
    }

    public void push(T j) {   // put item on top of stack
        stackArray[++top] = j;     // increment top, insert item
    }

    public T pop() {           // take item from top of stack
        return stackArray[top--];  // access item, decrement top
    }

    public T peek() {       // peek at top of stack
        return stackArray[top];
    }

    public boolean isEmpty() {    // true if stack is empty
        return (top == -1);
    }

    public boolean isFull() {  // true if stack is full
        return (top == maxSize - 1);
    }

    public int size() {       // return size
        return top + 1;
    }

    public T peekN(int n) { // return item at index n
        return stackArray[n];
    }

    public void displayStack(String s) {
        print(s);
        print("Stack (bottom-->top): ");
        for (int j = 0; j < size(); j++) {
            print(peekN(j));
            print(' ');
        }
        println("");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Stack(bottom-->top): [");
        int i;
        for (i = 0; i < this.size() - 1; i++) {
            sb.append(this.stackArray[i]).append(", ");
        }
        return sb.append(this.stackArray[i]) + "]";
    }
}
