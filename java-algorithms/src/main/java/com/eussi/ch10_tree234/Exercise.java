package com.eussi.ch10_tree234;

import com.eussi.data._10.Tree23;
import com.eussi.data._10.Tree234;

import static com.eussi.util.PrintUtil.*;

/**
 * @author wangxueming
 * @create 2020-02-28 11:12
 * @description 编程作业
 */
public class Exercise {
    public static void main(String[] args) {
        /**
         *  10.1 这个作业很容易。编写一个方法能返回2-3-4树中的最小值.
         */
        printExercise("10.1");
        exercise_1();

        /**
         * 10.2 编写方法中序遍历2-3-4树。它能有序地显示所有的数据项。
         */
        sepExercise("10.2");
        exercise_2();

        /**
         * 10.3 2-3-4树可以用于排序。编写sort()方法，从main()方法中传入关键字的数组
         *      并在排序后把它们写回数组中去。
         */
        sepExercise("10.3");
        exercise_3();

        /**
         * 10.4 修改tree234程序，使它可以创建并操作2-3树。要求显示
         *      树并可以查找。还要能够插入数据项，但是只限在叶节点（要分裂的）的父节
         *      点不需要再分裂的情况。这就是说split()方法不需要递归。编写insert()方
         *      法，记得在找到要插入合适的叶节点之前不需要节点分裂。找到后，如果叶节
         *      点满了就要分裂。也需要能够分裂根，但只限它是叶节点的情况下。根据这个
         *      限制，只能插入少于9个数据项，否则程序就会起冲突了。
         */
        sepExercise("10.4");
        exercise_4();

        /**
         * 10.5 扩展编程作业10.4的程序，使split()方法是递归的并可以处理一个满的子节
         *  	点的满父节点的情况。这就允许不限个数地插入节点。注意在递归的split()
         *  	例程中，在决定数据项要转向何处和子节点要连接到哪个里之前要先分裂父节
         *  	点。
         */
        sepExercise("10.5");
        exercise_5();

    }

    public static void exercise_1() {
        Tree234<Integer> theTree = new Tree234<>();
        theTree.insert(20, 50, 40, 60, 30, 70, 10);
        theTree.displayTree();
        println("min:" + theTree.getMinValue());
    }

    //练习 10.2
    public static void exercise_2() {
        Tree234<Integer> theTree = new Tree234<>();
        theTree.insert(20, 50, 40, 60, 30, 70, 10);
        theTree.displayTree();
        theTree.inOrderIter();// 中序遍历
    }

    //练习 10.3
    public static void exercise_3() {
        Tree234<Integer> theTree = new Tree234<>();
        Integer[] array = { 10, 2, 33, 4, 8, 7, 1, 5 };
        for (int l : array) {
            print(l + " ");
        }
        println("\nOrder:");
        theTree.sort(array);
        for (long l : array) {
            print(l + " ");
        }
    }

    public static void exercise_4() {
        Tree23<Integer> theTree = new Tree23<>();

        theTree.simpleInsert(10, 40, 60, 30, 70, 80, 90, 65); //插入100依然会导致分裂
        theTree.displayTree();
        theTree.simpleInsert(20);//移除

        int found = theTree.find(60);
        if (found != -1)
            println("Found 60");
        else
            println("Could not find 60");

        found = theTree.find(111);
        if (found != -1)
            println("Found 111");
        else
            println("Could not find 111");
    }

    public static void exercise_5() {
        Tree23<Integer> theTree = new Tree23<>();

        println("insert 50, 40, 60, 30, 70, 80: ");
        theTree.insert(50, 40, 60, 30, 70, 80);
        theTree.displayTree();
        println();

        println("insert 90, 65, 100: ");
        theTree.insert(90, 65, 100);
        theTree.displayTree();
        println();

        println("insert 85, 95, 86: ");
        theTree.insert(85, 95, 86);
        theTree.displayTree();
        println();

        int found = theTree.find(60);
        if (found != -1)
            println("Found 60");
        else
            println("Could not find 60");

        found = theTree.find(111);
        if (found != -1)
            println("Found 111");
        else
            println("Could not find 111");
    }
}
