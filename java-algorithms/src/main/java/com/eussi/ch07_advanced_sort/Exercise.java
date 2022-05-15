package com.eussi.ch07_advanced_sort;

import com.eussi.data._07.RadixSort;
import com.eussi.data._02.SimpleArray;

import static com.eussi.util.Func.getRandomSimpleArray;
import static com.eussi.util.PrintUtil.*;

/**
 * @author wangxueming
 * @create 2020-01-21 10:00
 * @description 编程作业
 */
public class Exercise {
    public static void main(String[] args) {
        /**
         *  7.1	修改 SimpleArray.java程序,使partitionIt()方法总是用具有最大的
         *  	下标值的数组(最右)数据项作为枢纽，而不是任意一个数据项。(这和清单7.3
         *  	的 快速排序方法一 程序相似。)确保程序对三个或少于三个数据项的数组也能
         *  	执行。为了达到这个目的，需要增加一些额外的语句。
         */
        printExercise("7.1");
        exercise_1();

        /**
         *  7.2	修改 SimpleArray.java 程序以计算排序中复制和比较的次数,然后显
         *  	示总数。这个程序应该和QuickSort2专题applet的执行性能相同，因此对逆序
         *  	排列数据的复制和比较次数是一致的。(记住一次交换是三个复制。)
         *      （未做，无法确定作者的意图，哪里算上复制，哪里算上比较）
         */

        /**
         *  7.3	在第3章练习题3.2中，提示可以通过排序数据和挑选中间数据项来求一个数据
         *  	集的中心值。读者可能会想，使用快速排序然后选择一个中间的数据项是找中
         *  	心值最快的方法，但是还有一个更快的方法。利用划分算法求中心值，而不必
         *  	对所有的数据项排序。为了理解它是如何实现的，假设在划分数据时，偶然发
         *  	现枢纽最后停在数组中间的位置上。工作就完成了！枢纽右边的所有数据项都
         *  	大于（或等于）枢纽，而所有左边的数据项都小于（或等于）枢纽，所以如果
         *  	枢纽停在数组正中间的位置上，那么它就是中心值。枢纽通常是不会停在数组
         *  	中间位置上的，但是可以通过再划分包含了中间位置数据项的分组来找到它。
         *  	假设数组有7个数据项分布在数组的关键字从0到6的单元中。中间位置的数据项
         *  	的下标为3。如果划分这个数组，并且枢纽停在下标为4的位置上，那么需要对
         *  	下标为0到4的数据项重新划分（这个划分包含了下标为3的数据项），而不包括
         *  	下标为5到6的数据项。如果枢纽停在下标为2的分区，总是检查枢纽是否在中间
         *  	的位置。最后，枢纽会停在中间位置上的，程序也就结束。因为所需要的划分
         *  	次数比快速排序少，所以这个算法更快。
         *  	扩展编程作业7.1，来查找一个数组的中心值数据项。可以使用类似快速排序的
         *  	递归调用，但是只用来划分子数组，而不是对整个数组排序。当找到中心值数
         *  	据项时这个过程就停止，而不必等数组排序完成之后才停止。
         */
        sepExercise("7.3");
        exercise_3(5);

        /**
         *  7.4	选择的意思是从一个数组中找出第k大或者第k小的数据项。例如，要选择第7大
         *  	的数据项。找中心值（如编程作业7.2）是选择的一种特殊情况。可以使用同样
         *  	的划分过程，但不是找中间位置的数据项，而是找一个指定下标的数据项。修
         *  	改编程作业7.2中的程序，允许选择任意一个指定数据项。你的程序能处理多少
         *  	的数组呢？
         */
        sepExercise("7.4");
        exercise_4(7, 2);

        /**
         *  7.5	实现本章最后一节所讲的基数排序算法。它应该能处理不同数据量的数据项，
         *  	以及关键字位数不同的数据。同时，也应该能够实现不同基数的排序（可以是
         *  	10以外的其他数），但是，除非编写的程序能显示不同的基数值，否则会很难
         *  	看清运行的情况。
         *
         */
        sepExercise("7.5");
        radixSort(10);
    }

    private static void exercise_4(int maxSize, int index) {
        SimpleArray arr = getRandomSimpleArray(maxSize, 199);
        println("index [" + index + "] is :" + arr.findKth2(index));
        //排序看中心值
        println("排序：");
        arr.insertionSort();
        arr.display();
        println("============");

        arr = getRandomSimpleArray(maxSize, 199);
        println("index [" + index + "] is :" + arr.findKth2(index));
        //排序看中心值
        println("排序：");
        arr.insertionSort();
        arr.display();
    }

    private static void exercise_1() {
        partion(1);
        println("============");
        partion(2);
        println("============");
        partion(3);
        println("============");
        partion(10);
    }

    public static void radixSort(int maxSize) {
        long[] array = new long[maxSize];
        print("A=");
        for(int i=0; i<maxSize; i++) {                          // random numbers
            long n = (int)(Math.random()*199);
            print(n + " ");
            array[i] = n;
        }
        println();

        RadixSort.radixSort(array, 10, 3);
        print("A=");
        for (int i = 0; i < array.length; i++) {
            print(array[i] + " ");
        }
    }

    public static void exercise_3(int maxSize) {
        SimpleArray arr = getRandomSimpleArray(maxSize, 199);
        println("Median is :" + arr.medianByPartition());
        //排序看中心值
        println("排序：");
        arr.insertionSort();
        arr.display();
        println("============");

        arr = getRandomSimpleArray(maxSize, 199);
        println("Median is :" + arr.findMedianByPartition2());
        //排序看中心值
        println("排序：");
        arr.insertionSort();
        arr.display();
    }

    public static void partion(int maxSize) {
        SimpleArray arr = getRandomSimpleArray(maxSize, 199);
        int size = arr.size();
        print("Pivot is " + arr.get(size-1));
        int partDex = arr.partitionIt(0, size-1);
        println(", Partition is at index " + partDex);
        arr.display();                // display partitioned array
    }
}
