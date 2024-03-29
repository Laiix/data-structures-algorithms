package com.eussi.ch12_heap;

import com.eussi.data._12.Heap;
import com.eussi.data._12.PriorityQ;
import com.eussi.data._12.TreeHeap;
import com.eussi.data._12.TreePriorityQ;

import static com.eussi.util.PrintUtil.*;

/**
 * @author wangxueming
 * @create 2020-03-04 12:48
 * @description 编程作业
 */
public class Exercise {
    public static void main(String[] args) {
        /**
         *12.1 修改heap.java程序，使堆变为升序的，而不是降序的堆。（这
         *	 就是说，根节点上的数据项是最小的，机时不是最大的。）确保所有的操作都
         *	 能正确地执行。
         */
        printExercise("12.1");
        exercise_1();

        /**
         * 12.2 用heap.java程序中的insert()方法在堆中插入一个新节点,确保不违背堆的
         * 	 条件。另编写toss()方法，把新节点放到堆数组中，不需要保持堆的条件。
         * 	 （可能每个新节点只是简单地放在数组的未端。）再编写一个restoreHeap()
         * 	 方法，它能在全堆恢复堆的条件。重复应用toss()，一次插入大量的数据项，
         * 	 然后再用一次restoreHeap()，比反复应用insert()的效率要高。查看堆排序
         * 	 的描述找到暗示。用insert()插入几个数据，再用toss()方法放置几个数据，
         * 	 然后再用restoreHeap()恢复堆，以此测试编写的程序。
         */
        sepExercise("12.2");
        exercise_2();

        /**
         * 12.3 应用堆取代数组来实现priorityQ.java程序（清单4.6）中PriorityQ类。可
         * 	 以不用修改heap.java程序（清单12.1）中的Heap类，直接应用它。把它作成
         * 	 一个降序队列（移除最大数据项）。
         */
        sepExercise("12.3");
        exercise_3();

        /**
         *12.4 用基于数组的堆实现的优先级队列存在一个问题，就是数组的大小为固定的。
         *	 如果数据量超过了数组容量，就需要扩展数组，就像在第11章的编程作业11.4
         *	 中对哈希表所做的修改一样。用普通的二叉搜索树而不是堆来实现优先级队列
         *	 就可以避免这个问题。一棵树可以要多在就有多大（只要不超过系统内存的容
         *	 量就行）。从tree.java程序（清单8.1）中的Tree类出发，修改这个类，使
         *	 它支持优先级队列，增加移除最大数据项的方法removeMax()。这一点在堆中
         *	 是很容易作到的。但是在树中稍微有点困难。如何在树中找到最大的数据项呢?
         *	 当移除一个节点时需要考虑它的子节点吗？编写change()方法是一种选择。用
         *	 它在二叉搜索树中移除老数据项和插入另一个不同关键字的新数据项是很容易
         *	 的。
         */
        sepExercise("12.4");
        exercise_4();

        /**
         * 12.5 编写程序实现本单中所讨论过的树堆（基于树而实现的堆），确保能够进行移
         *  	 除最大的数据项，插入数据项，以及修改数据项关键字的操作。
         */
        sepExercise("12.5");
        exercise_5();
    }

    public static void exercise_5() {
        TreeHeap theHeap = new TreeHeap();  // make a HeapApp; max size 31
        boolean success;

        theHeap.insert(70);           // insert 10 items
        theHeap.insert(40);
        theHeap.insert(50);
        theHeap.insert(20);
        theHeap.insert(60);
        theHeap.insert(100);
        theHeap.insert(80);
        theHeap.insert(30);
        theHeap.insert(10);
        theHeap.insert(90);

        theHeap.displayHeap();

        int key = 11;
        println("Enter value to insert: " + key);
        success = theHeap.insert(key);
        if( !success )
            println("Can't insert; heap full:" + key);
        theHeap.displayHeap();

        key = 12;
        println("Enter value to insert: " + key);
        success = theHeap.insert(key);
        if( !success )
            println("Can't insert; heap full:" + key);
        theHeap.displayHeap();

        if( !theHeap.isEmpty() ) {
            println("remove:" + theHeap.remove());
            theHeap.displayHeap();
        } else
            println("Can't remove; heap empty");

        if( !theHeap.isEmpty() ) {
            println("remove:" + theHeap.remove());
            theHeap.displayHeap();
        } else
            println("Can't remove; heap empty");

    }

    public static void exercise_4() {
        TreePriorityQ thePQ = new TreePriorityQ(5);
        thePQ.insert(30);
        thePQ.insert(20);
        thePQ.insert(10);
        thePQ.insert(40);
        thePQ.insert(50);
        println("Remove :" + thePQ.remove());
        println("Peek :" + thePQ.peek());
        println("Remove all:");
        while (!thePQ.isEmpty()) {
            print(thePQ.remove() + " ");
        }
        println();
    }

    public static void exercise_3() {
        PriorityQ thePQ = new PriorityQ(5);
        thePQ.insert(20);
        thePQ.insert(40);
        thePQ.insert(30);
        thePQ.insert(50);
        thePQ.insert(10);
        thePQ.display();
        println("Remove :" + thePQ.remove());
        thePQ.display();
        println("Peek :" + thePQ.peek());
        println("Remove all:");
        while (!thePQ.isEmpty()) {
            print(thePQ.remove() + " ");
        }
        println();
    }

    public static void exercise_2() {
        Heap<Integer> theHeap = new Heap<>(31);  // make a HeapApp; max size 31
        theHeap.toss(70);           // insert 10 items
        theHeap.toss(40);
        theHeap.toss(50);
        theHeap.toss(20);
        theHeap.toss(60);
        theHeap.toss(100);
        theHeap.toss(80);
        theHeap.toss(30);
        theHeap.toss(10);
        theHeap.toss(90);

        println("不合法堆：");
        theHeap.displayHeap();

        println("恢复堆：");
        theHeap.restoreHeap();
        theHeap.displayHeap();
    }

    public static void exercise_1() {
        HeapApp.testHeap(Heap.Sort.asc);
    }
}
