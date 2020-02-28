package com.eussi.ch10_tree234;

import com.eussi.ch10_tree234.util.Tree23;
import com.eussi.ch10_tree234.util.Tree234;
import com.eussi.util.Util;

import java.io.IOException;

/**
 * @author wangxueming
 * @create 2020-02-28 11:12
 * @description
 */
public class Exercise {
    public static void main(String[] args) {
        /**
         * 编程作业
         *  	10.1 这个作业很容易。编写一个方法能返回2-3-4树中的最小值.
         * 	    10.2 编写方法中序遍历2-3-4树。它能有序地显示所有的数据项。
         * 	    10.3 2-3-4树可以用于排序。编写sort()方法，从main()方法中传入关键字的数组
         * 		 并在排序后把它们写回数组中去。
         * 	    10.4 修改tree234程序，使它可以创建并操作2-3树。要求显示
         * 		 树并可以查找。还要能够插入数据项，但是只限在叶节点（要分裂的）的父节
         * 		 点不需要再分裂的情况。这就是说split()方法不需要递归。编写insert()方
         * 		 法，记得在找到要插入合适的叶节点之前不需要节点分裂。找到后，如果叶节
         * 		 点满了就要分裂。也需要能够分裂根，但只限它是叶节点的情况下。根据这个
         * 		 限制，只能插入少于9个数据项，否则程序就会起冲突了。
         *  	10.5 扩展编程作业10.4的程序，使split()方法是递归的并可以处理一个满的子节
         *  	点的满父节点的情况。这就允许不限个数地插入节点。注意在递归的split()
         *  	例程中，在决定数据项要转向何处和子节点要连接到哪个里之前要先分裂父节
         *  	点。
         */
        // 编程作业 10.1
        test10_1();
        Util.printSeparator();

        // 编程作业 10.2
        test10_2();
        Util.printSeparator();

        // 编程作业 10.3
        test10_3();
        Util.printSeparator();

        // 编程作业 10.4
        test10_4();
        Util.printSeparator();

        // 编程作业 10.5
        test10_5();

    }

    //练习 10.1
    public static void test10_1() {
        Tree234 theTree = new Tree234();

        theTree.insert(20);
        theTree.insert(50);
        theTree.insert(40);
        theTree.insert(60);
        theTree.insert(30);
        theTree.insert(70);
        theTree.insert(10);
        theTree.displayTree();

        System.out.println("min:" + theTree.getMinValue());
    }

    //练习 10.2
    public static void test10_2() {
        Tree234 theTree = new Tree234();

        theTree.insert(20);
        theTree.insert(50);
        theTree.insert(40);
        theTree.insert(60);
        theTree.insert(30);
        theTree.insert(70);
        theTree.insert(10);
        theTree.insert(80);
        theTree.displayTree();

        // 编程作业 10.2
        theTree.inOrderIter();// 中序遍历
    }

    //练习 10.3
    public static void test10_3() {
        Tree234 theTree = new Tree234();

        // 编程作业 10.3
        long[] array = { 10, 2, 33, 4, 8, 7, 1, 5 };
        for (long l : array) {
            System.out.print(l + " ");
        }
        System.out.println("\nOrder:");
        theTree.sort(array);
        for (long l : array) {
            System.out.print(l + " ");
        }
        // ========================================

    }

    public static void test10_4() {
        Tree23 theTree = new Tree23();

        theTree.insert(10);
        theTree.insert(40);
        theTree.insert(60);
        theTree.insert(30);
        theTree.insert(70);
        theTree.insert(80);
        theTree.insert(90);
        theTree.insert(65); //插入100依然会导致分裂
        theTree.displayTree();
//          theTree.insert(20); //只能插入少于9个数据项

        // ==================================================

        int found = theTree.find(60);
        if (found != -1)
            System.out.println("Found 60");
        else
            System.out.println("Could not find 60");

        found = theTree.find(111);
        if (found != -1)
            System.out.println("Found 111");
        else
            System.out.println("Could not find 111");

    }  // end main()

    public static void test10_5() {
        Tree23 theTree = new Tree23();

        theTree.insert1(50);
        theTree.insert1(40);
        theTree.insert1(60);
        theTree.insert1(30);
        theTree.insert1(70);
        theTree.insert1(80);
        theTree.displayTree();
        theTree.insert1(90);
        theTree.insert1(65);
        theTree.insert1(100);
        theTree.displayTree();
        theTree.insert1(85);
        theTree.insert1(95);
        theTree.insert1(86);
        theTree.displayTree();

        int found = theTree.find(60);
        if (found != -1)
            System.out.println("Found 60");
        else
            System.out.println("Could not find 60");

        found = theTree.find(111);
        if (found != -1)
            System.out.println("Found 111");
        else
            System.out.println("Could not find 111");

    }  // end main()
}
