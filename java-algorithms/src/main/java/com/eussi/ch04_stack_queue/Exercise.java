package com.eussi.ch04_stack_queue;

import com.eussi.data._04.*;

import java.io.IOException;

import static com.eussi.util.PrintUtil.*;

/**
 * @author wangxueming
 * @create 2019-10-28 20:15
 * @description 编程作业
 */
public class Exercise {
    public static void main(String[] args) throws IOException {
        /**
         * 4.1 为 Queue.java 程序的Queue类中写一个方法，显示队列的
         *      内容。注意这并不是要简单的显示出数组的内容。它要求按数据项插入的队
         *      列的顺序，从第一个插入的数据项到最后一个插入的数据项显示出来。不要
         *      输出因为在数组末端回绕而折成两半的样子。注意无论front和rear在什么
         *      位置上，都要正确显示出一个数据项和没有数据项的情况。
         */
        printExercise("4.1");
        exercise_1();

        /**
         *4.2 根据本章里对双端队列的讨论编写一个Deque类，它应该包括
         *     insertLeft()、insertRight()、removeLeft()、removeRight()、
         *     isEmpty()、isFull()方法。要求像队列那样支持在数据末端的回绕。
         */
        sepExercise("4.2");
        exercise_2();

        /**
         * 4.3 编写一个基于上机作业4.2的Deque类的栈类。这个栈类应该与
         * 	StackX.java程序（清单4.1）中的StackX类具有机同的方法和功能。
         */
        sepExercise("4.3");
        exercise_3();

        /**
         *4.4 优先级队列能够快速地删除最高优先级的数据项，但是
         *	插入数据项较慢。编写修改PriorityQ类，使他插入的时间复杂度为O(1),
         *	但是删除最高优先级的数据项时间较慢。还要包括一个显示优先级队列内容
         *	的方法，要求和上机作业4.1中一样。
         */
        sepExercise("4.4");
        exercise_4();

        /**
         * 4.5 队列通用于模拟人、汽车、飞机、业务等等的流动情况。应用queue.java
         * 	程序（清单4.4）的Queue类，编写一个程序模拟超市的收款队列。可以用上机
         * 	作业4.1的display()方法，显示出顾客的几条队列。可以通过敲击一个键插入
         * 	一个新的顾客。为顾客选择在哪一个队列上。收银员为每个顾客服务的时间是
         * 	随机的（可假定为按照顾客买了多少东西而定）。一旦结完账，就从队列中删
         * 	除该顾客。为了简单起见，通过敲击键模拟时间的流逝。可能每点击一下键表
         * 	示时间过去了1分钟。（当然，java有更复杂的方式来处理时间。）
         */
        sepExercise("4.5");
        testSupermarket();
    }

    private static void exercise_1() {
        Queue<Integer> theQueue = new Queue<>(5);  // queue holds 5 items

        theQueue.insert(10);            // insert 4 items
        theQueue.insert(20);
        theQueue.insert(30);
        theQueue.insert(40);

        theQueue.remove();              // remove 3 items
        theQueue.remove();              //    (10, 20, 30)

        theQueue.insert(50);            // insert 4 more items
        theQueue.insert(60);            //    (wraps around)
        theQueue.insert(70);
        theQueue.display();
    }

    private static void testSupermarket() throws IOException {
        SuperMarket sm = new SuperMarket();
        sm.simulate();
    }

    private static void exercise_4() {
        PriorityQ2 thePQ = new PriorityQ2(5);
        thePQ.insert(30);
        thePQ.insert(50);
        thePQ.insert(10);
        thePQ.insert(40);
        thePQ.insert(20);
        thePQ.display();
        //thePQ.display1();
        while (!thePQ.isEmpty()) {
            long item = thePQ.remove();
            print(item + " "); // 10, 20, 30, 40, 50
        } // end while
        println("");
    }

    private static void exercise_3() {
        StackY theStack = new StackY(5); // make new stack
        println("Stack is Empty : " + theStack.isEmpty());
        println("Stack is Full : " + theStack.isFull());
        theStack.push(20); // push items onto stack
        theStack.push(40);
        theStack.push(60);
        theStack.push(80);
        theStack.push(90);
        println("Stack is Empty : " + theStack.isEmpty());
        println("Stack is Full : " + theStack.isFull());
        while (!theStack.isEmpty()) // until it's empty,
        { // delete item from stack
            long value = theStack.pop();
            print(value); // display it
            print(" ");
        } // end while
        println("");
    }

    private static void exercise_2() {
        Deque theQueue = new Deque(5); // queue holds 5 items
        println("队列是否为空：" + theQueue.isEmpty());
        println("队列是否为满：" + theQueue.isFull());
        println("队列的大小为：" + theQueue.size());
        theQueue.display();
        theQueue.insertRight(10); // insert 4 items
        theQueue.insertRight(20);
        theQueue.insertRight(30);
        theQueue.insertRight(40);
        println("队列的大小为：" + theQueue.size());
        theQueue.display();

        theQueue.removeLeft(); // remove 3 items
        theQueue.removeLeft(); // (10, 20, 30)
        theQueue.removeLeft();
        println("队列的大小为：" + theQueue.size());
        theQueue.display();

        theQueue.insertLeft(50); // insert 4 more items
        theQueue.insertLeft(60); // (wraps around)
        theQueue.insertLeft(70);
        theQueue.insertLeft(80);
        println("队列是否为空：" + theQueue.isEmpty());
        println("队列是否为满：" + theQueue.isFull());
        println("队列的大小为：" + theQueue.size());
        theQueue.display();

        theQueue.removeRight(); // remove 3 items
        theQueue.removeRight(); // (10, 20, 30)
        theQueue.removeRight();
        println("队列的大小为：" + theQueue.size());
        theQueue.display();

    }

}
