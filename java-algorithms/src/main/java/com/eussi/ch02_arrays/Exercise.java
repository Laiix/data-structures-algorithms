package com.eussi.ch02_arrays;

import com.eussi.data._02.SimpleArray;
import com.eussi.data._02.OrderedArray;

import static com.eussi.util.Func.getSimpleArray;
import static com.eussi.util.PrintUtil.*;

/**
 * @author wangxueming
 * @create 2019-10-18 10:57
 * @description 编程作业
 */
public class Exercise {
    public static void main(String[] args) {
        SimpleArray simpleArray = getSimpleArray();

        /**
         * 2.1 向 DisorderedArray.java程序的 DisorderedArray 类添加一个名为getMax()的方法，它返回
         *  数组中最大关键字的值，当数组为空时返回-1。向main()中添加一些代码来使用这个方法。
         *  可以假设所有关键字都是正数。
         */
        sepExercise("2.1");
        exercise_1(simpleArray);

        /**
         * 2.2 修改编程作业2.1中的方法，使之不仅返回最大的关键字，而且还将该关键字从数组中删除。
         *     将这个方法命名为removeMax()。
         */
        sepExercise("2.2");
        exercise2(simpleArray);

        /**
         * 2.3 编程作业2.2中的removeMax()方法提供了一种通过关键字值进行数组排序的方法。实现一个
         *     排序方案，要求不修改SimpleArray类，只需对main()中的代码进行修改。这个方法需要第二个
         *     数组，在排序结束时数组数据项是逆序排列的。（这个方法是第3章“简单排序”中选择排序的
         *     一个变体。）
         */
        sepExercise("2.3");
        exercise_3(simpleArray);

        /**
         * 2.4 修改 OrderedArray.java程序（清单2.4）使insert()、delete()与find()方法一样都使用
         *     二分查找，正如书中所建议的那样。
         */
        sepExercise("2.4");
        exercise_4();

        /**
         * 2.5 向 OrderedArray.java程序（清单2.4）的 OrderedArray 类加入一个merge()方法，使之可以将两个
         *   有序的源数组合并成一个有序的目的数组。在main()中添加代码，向两个源数组中插入随机数，
         *	 调用merge()方法，并将结果目的数组显示出来。两个源数组的数据项个数可能不同。在算法中
         *	 需要先比较源数组中的关键字，从中选出最小的一个数据项复制到目的数组。同时还要考虑如何
         *	 解决当一个源数组的数据项已经取完而另一个还剩一些数据项情况。
         */
        sepExercise("2.5");
        exercise_5();

        /**
         *2.6 向 DisorderedArray.java程序（清单2.3）的 DisorderedArray 类中加入一个noDup()方法，使之可以将数组中
         *	的所有重复数据项删除。即如果数组中有三个数据项的关键字为17，noDup()方法会删除其中的
         *	两个。不必考虑保持数据项的顺序。一种方法是先用每一个数据项同其他数据项比较，并用null
         *	(或是一个不会用在真正的关键字中的特殊值)将重复的数据项覆盖掉。然后将所有的null删除，
         *	当然还要缩小数组的大小。
         */
        sepExercise("2.6");
        exercise_6();

    }

    private static void exercise_3(SimpleArray highArray) {
        // p50(69) 编程作业2.3
        int maxSize = 1000; // array size
        SimpleArray sortedArr = new SimpleArray(maxSize);
        long max = highArray.removeMax();
        while (max != -1) {//max初始值
            sortedArr.insert(max);
            max = highArray.removeMax();
        }
        print("逆序排列:");
        sortedArr.display();
    }

    private static void exercise2(SimpleArray disorderedArray) {
        // p50(69) 编程作业2.2
        disorderedArray.removeMax();
        print("After remove max: ");
        disorderedArray.display();
    }

    private static void exercise_1(SimpleArray disorderedArray) {
        // p50(69) 编程作业2.1
        long max = disorderedArray.getMax();
        println("Found max is " + max);
    }


    private static void exercise_4() {
        // 编程作业2.4 p50(69)
        int maxSize = 100; // array size
        OrderedArray arr = new OrderedArray(maxSize); // create the array
        arr.insertByBinarySearch(4); // insert 10 items
        arr.insertByBinarySearch(6);
        arr.insertByBinarySearch(8);
        arr.insertByBinarySearch(1);
        arr.insertByBinarySearch(33);
        arr.insertByBinarySearch(0);
        arr.insertByBinarySearch(22);
        arr.insertByBinarySearch(13);
        arr.insertByBinarySearch(59);
        arr.insertByBinarySearch(9);

        print("Arr after insert: ");
        arr.display(); // display items again

        long deleteKey = 33;
        arr.delete(deleteKey);
        print("After delete " + deleteKey + ": ");
        arr.display(); // display items again
    }

    private static void exercise_5() {
        // 编程作业2.4 p50(69)
        int maxSize = 100; // array size
        OrderedArray arr = new OrderedArray(maxSize); // create the array
        arr.insert(4, 6, 8, 1, 33, 0, 22, 13, 59, 9);
        print("Arr1 after insert: ");
        arr.display(); // display items again

        // 编程作业2.5 p50(69)
        OrderedArray arr1 = new OrderedArray(maxSize); // create the array
        arr1.insert(10, 20, 30, 40, 50, 60, 70);
        print("Arr2 after insert: ");
        arr1.display();

        println("合并两个数组，生成新的数组");
        print("实现一：");
        OrderedArray arr2 = arr.merge(arr1);
        arr2.display();
        print("实现二：");
        arr2 = arr.merge2(arr1);
        arr2.display();
        print("实现三：");
        arr2 = arr.merge3(arr1);
        arr2.display();
    }

    private static void exercise_6() {
        int maxSize = 100; // array size
        SimpleArray arr; // reference to array
        arr = new SimpleArray(maxSize); // create the array
        arr.insert(5, 5, 5, 5, 5); // insert 10 items
        println("加入重复值后：");
        arr.display();
        arr.noDup();
        println("去掉重复值后：");
        arr.display();


        // 重复值
        arr.insert(3, 3, 4, 5);

        println("加入重复值后：");
        arr.display();
        arr.noDup1();
        println("去掉重复值后：");
        arr.display();
    }
}
