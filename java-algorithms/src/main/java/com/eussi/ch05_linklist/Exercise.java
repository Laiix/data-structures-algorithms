package com.eussi.ch05_linklist;

import com.eussi.data._05.*;

import static com.eussi.util.PrintUtil.*;

/**
 * @author wangxueming
 * @create 2019-11-02 0:24
 * @description 编程作业
 */
public class Exercise {
    public static void main(String[] args) {
        /**
         *5.1	实现一个基于有序链表的优先级队例。队列的删除操作应该删除具有最小
         *	关键字的链结点。
         */
        printExercise("5.1");
        exercise_1();

        /**
         *5.2 实现一个基于双向链表的双端队列。（参考前一章练习4.2。）使用
         *	都应该能够执行双端队列的基本操作。
         */
        sepExercise("5.2");
        exercise_2();

        /**
         * 	5.3	循环链表是一种链表，它的最后一个链结点指向第一个链结点。设计循环
         * 		链表有许多方法。有时，用一个指向链表"开始"的指针。然面这么做使链
         * 		表不像一个真正的环，而更像一个传统的链表，只不过这个链表的表头和
         * 		表尾系在了一起。编写一个类代表循环单链表，它没有表头也没有表尾。
         * 		访问这个链表的惟一方式是一个引用current，它能指向任意一个链结点。
         * 		这个引用在需要的时候可以沿链表移动。（参考上机作业5.5，那种情况
         * 		下用循环链表很合适。）你的链表应该能处理插入、查找和删除。可能会
         * 		发现如果这些操作发生在current指向的下一个链结点时将会非常方便。
         * 		（因为上一个链结点是单向连接的，不遍历整个循环链表，不可能得到它。
         * 		）你也应该可以显示链表（尽管需要在循环链表的某处切断环，以把它们
         * 		打印到屏幕上）。step()方法可以把current移动到下一个链结点，在这
         * 		个程序中可能也会派上用场。
         */
        sepExercise("5.3");
        exercise_3();

        /**
         * 	5.4	实现一个基于上题循环链表的栈类。这个练习不是太难。（然而，实现一
         * 		个队列有些难度。除非把循环链表做成双向的。）
         */
        sepExercise("5.4");
        println("这个题目有问题，题目要求实现的是栈，这里实现的是队列，栈用单向循环链表实现比较麻烦");
        exercise_4();

        /**
         * 	5.5	Josephus问题是古代一个著名的数学难题。围绕这个问题有很多故事。其
         * 		中一个说Josephus是一群被罗马人抓获的犹太人中的一个，为了不被奴役，
         * 		他们选择自杀。他们排成一个圆圈，从某个人开始，沿着圆圈计数。每报
         * 		第n个数的人就要离开圆圈去自杀。Josephus不想死，所以他制定了规则，
         * 		以使他成为最后一个离开圆圈的人。如果有（例如）20个人的话，他就是
         * 		从开头数第7个人，那么他让他们用什么数来进行报数呢？这个问题会越来
         * 		越复杂，因为随着过程进行，圆圈在缩小。使用5.3题的循环链表创建一个
         * 		应用来模拟这个问题。输入是组成圆圈的人数，要报的数和第一个开始报的
         * 		人的位置（通常是1）。输出是被消去的人的列表。当一个人出了圆圈，再
         * 		继续从他左边那个人开始计数（假设沿顺时针旋转）。这有一个例子。有7
         * 		个人，从1到7，从第一个人开始报数，报到4出圆圈，最后被消去的人的顺
         * 		序是4，1，6，5，7，3。最后剩下的人是2。
         */
        sepExercise("5.5");
        exercise_5(7);

        /**
         * 	5.6	下面尝试一些不同的事情：二维链表，通常叫矩阵。这是二维数组的链表
         * 		版本。它可以用于某些应用，例如电子表格程序。如果电子表格是基于数
         * 		组的，那么在顶端附近插入一个新行时，需要移动下面N*M个单元，这可
         * 		能是一个非常缓慢的过程。如果电子表格用矩阵实现，只需要改变N个指针。
         * 		    为了简单起见，假定用单链表方法（尽管双向链表方法可能对电子表格更
         * 		为适合）。每个链结点（除了在第一行和左侧面的链结点）都由它上面和
         * 		左面的链结点指着。就是说，可以从左上角的链结点开始移动，找到第三
         * 		行第五列的链结点，只要沿着指针向下两行，向右四列即可。假定矩阵有
         * 		固定的大小（例如7*10）。应该可以在某个链结点处插入值，并显示矩阵
         * 		的内容。
         */
        sepExercise("5.6");
        exercise_6();

    }

    private static void exercise_6() {
        TwoDimensionLinklist list = new TwoDimensionLinklist(5, 5);
        println("init: ");
        list.display();
        list.addRow(1);
        list.addColumn(1);
        println("addRow & andColumn: ");
        list.display();

        list.insert(1, 1, 5);
        list.insert(2, 2, 6);
        list.insert(3, 3, 7);
        list.insert(4, 4, 8);
        list.insert(5, 5, 9);
        println("insert data: ");
        list.display();

        list.deleteColumn(1);
        list.deleteRow(1);
        println("deleteColumn & deleteRow: ");
        list.display();

    }

    private static void exercise_5(int number) {
        CircleLinklist<Integer> circleList = new CircleLinklist<>();
        for (int i = 1; i <= number; i++) { // number个人排成环
            circleList.insert(i);
        }
        print("原始圈子：");
        circleList.display();
        print("出圈顺序：");
        for (int i = 1; i < number; i++) { // 要删掉number-1个人
            for (int j = 1; j < 4; j++) { //不能数到四，因为remove()方法删掉的是下一个
                circleList.step();
            }
            print(circleList.remove() + " ");
        }
        println();
        println("Josephus的编号是:" + circleList.peek());
    }

    private static void exercise_4() {
        LinklistQueue<Integer> theQueue = new LinklistQueue<>(5); // queue holds 5 items

        theQueue.insert(10); // insert 4 items
        theQueue.insert(20);
        theQueue.insert(30);
        theQueue.insert(40);
        theQueue.display();

        theQueue.remove(); // remove 3 items
        theQueue.remove(); // (10, 20, 30)
        theQueue.remove();

        theQueue.display();

        theQueue.insert(50); // insert 4 more items
        theQueue.insert(60); // (wraps around)
        theQueue.insert(70);
        theQueue.insert(80);

        theQueue.display();
        long n = theQueue.remove(); // (40, 50, 60, 70, 80)
        println("删掉" + n);
        println("队头元素是" + theQueue.peek());
        theQueue.display();

    }

    private static void exercise_3() {
        CircleLinklist<Integer> theList = new CircleLinklist<>(); // make new list

        theList.insert(10); // insert four items
        theList.insert(20); // insert four items
        theList.insert(40); // insert four items
        theList.insert(30); // insert four items
        theList.display(); // display list
        System.out.println("最早插入的元素" + theList.peek());
        Link<Integer> link = theList.find(40);
        if (link != null) {
            println("find 40!");
        } else {
            println("not find 40!");
        }
        long aLink = theList.remove(); // delete link
        println("Deleted " + aLink); // display it

        theList.display(); // display list
    }

    private static void exercise_2() {
        DuQueue<Integer> theQueue = new DuQueue<>(); // queue holds 5 items
        theQueue.display();

        theQueue.insertRight(10); // insert 4 items
        theQueue.insertRight(20);
        theQueue.insertRight(30);
        theQueue.insertRight(40);
        theQueue.display();

        theQueue.removeLeft(); // remove 3 items
        theQueue.removeLeft(); // (10, 20, 30)
        theQueue.removeLeft();
        theQueue.display();

        theQueue.insertLeft(50); // insert 4 more items
        theQueue.insertLeft(60); // (wraps around)
        theQueue.insertLeft(70);
        theQueue.insertLeft(80);
        theQueue.display();

        theQueue.removeRight(); // remove 3 items
        theQueue.removeRight(); // (10, 20, 30)
        theQueue.removeRight();
        theQueue.display();
    }

    private static void exercise_1() {
        LinklistPriorityQ thePQ = new LinklistPriorityQ();
        thePQ.insert(30);
        thePQ.insert(50);
        thePQ.insert(10);
        thePQ.insert(40);
        thePQ.insert(20);
        thePQ.display();
        while (!thePQ.isEmpty()) {
            long item = thePQ.remove();
            print(item + " "); // 10, 20, 30, 40, 50
        } // end while
        println("");
    }
}
