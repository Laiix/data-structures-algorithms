package com.eussi.ch03_simple_sort;

import com.eussi.data._02.DisorderedArray;

import static com.eussi.util.Func.*;
import static com.eussi.util.PrintUtil.*;

/**
 * @author wangxueming
 * @create 2019-10-22 13:52
 * @description 编程作业
 */
public class Exercise {

    public static void main(String[] args) {
        /**
         * 3.1 DisorderedArray.java程序的冒泡排序方法中，in索引变量都是从左到
         *     右移动的，直到找到最大数据项并把它移动到右边的out变量外。修改bubbleSort()方法，
         *     使它成为双向移动的。这样，in索引先像以前一样，将最大的数据项从左移到右，当它到
         *     达out变量位置时，它掉头并把最小的数据项从右移到左。需要两个外部索引变量，一个在
         *     右边（以前的out变量），另一个在左边。
         */
        printExercise("3.1");
        exercise_1();

        /**
         * 3.2 在 DisorderedArray.java 程序中给 DisorderedArray 类加一个median()方法.这个方法将返回
         *    数组的中间值.(回忆一下,数组中一半数据项比中间值大,一半数据项比中间值小。)
         */
        sepExercise("3.2");
        exercise_2();

        /**
         * 3.3 在 DisorderedArray.java 程序中增加一个名为noDups()的方法，这个方法从已经有
         *    序的数组中删掉重复的数据项而不破坏有序性。（可以用insertionSort()方法对数据排序，
         *    或者也可以简单地用main()方法将数据有序地插入到表中。）一种解决方法是每发现一个重
         *    复的数据，就从这个位置开始到数组结尾都向前移动一个位置，但这样就导致消耗很长的
         *    O(N2)的时间级，起码在有很多重复数据项的情况下是这样的。在设计的算法中，不论有多
         *    少重复数据，要确保数据项最多只能移动一次。这样算法只消耗O(N)数量级的时间。
         */
        sepExercise("3.3");
        println("参考 com.eussi.ch02_arrays.Exercise 中练习2.6");

        /**
         *3.4 有一种简单排序算法是奇偶排序。它的思路是在数组中重复两趟扫描。第一趟扫描选择所有
         *   的数据项对，a[j]和a[j+1]，j是奇数(j=1,3,5,……)。如果它们的关键字的值次序颠倒，就交
         *   换它们。第二趟扫描对所有的偶数数据项进行同样的操作（j=2,4,6,……）。重复进行这样两趟
         *   的排序直到数组全部有序。用oddEvenSort()方法替换 DisorderedArray.java 程序中的
         *   bubbleSort()方法。确保它可以在不同数据量的排序中运行，还需要算出两趟扫描的次数。
         *       奇偶排序实际上在多处理器环境中很有用，处理器可以分别同时处理每一个奇数对，然后又同时
         *   处理偶数对。因为奇数对是彼此独立的，每一对都可以用不同的处理器比较和交换。这样可以非
         *   常快速地排序。
         */
        sepExercise("3.4");
        exercise_4();

        /**
         *3.5 修改 DisorderedArray.java 程序中的insertionSort()方法，使它可以计算排序过
         *   程中复制和比较的次数并显示出总数。为计算比较的次数，要把内层while循环的两个条件分开。
         *   用这个程序测量各种数量的逆序数据排序的复制和比较次数。结果满足O(N2)吗？与已经基本有
         *   序的数据（仅有很少的数据无序）的情况一样吗？从对基本有序数据排序的表现中可得出关于这
         *   个算法效率的什么结论？
         */
        sepExercise("3.5");
        exercise_5();

        /**
         *3.6 有一个有趣的方法用来删除数组中相同的数据项。插入排序算法中用一个循环嵌套算法，将
         *  数组中的每一个数据项与其他数据项一一比较。如果要删除相同的数据项，可以这样做（参见第
         *  2章习题第2.6）。修改DisorderedArray.java 中的insertionSort()方法，使它可以在排序过程中
         *  删除相同的数据项。方法如下：当找到一个重复数据项的时候，通常用一个小于任何值的关键值
         *  来改写这个相同数据项（如果所有值都是正数，则可取-1）。于是，一般的插入排序算法就会像
         *  处理其他数据项一样，来处理这个修改了关键值的数据项，把它移到下标为0的位置。从现在开
         *  始，算法可以忽略这个数据项。下一个相同的数据项将被移到下标为1的位置，依此类推。排序
         *  完成后，所有相同的数据项（现在关键值为-1）都在数组的开头部分。可以改变数组的容量并
         *  把需要的数据前移动数组下标为0的位置。
         */
        sepExercise("3.6");
        exercise_6();
    }

    private static void exercise_1() {
        DisorderedArray arr = getDisorderedArray();
        arr.bubbleSort1();
        print("after two-way bubble sort: ");
        arr.display();                // display them again
    }

    private static void exercise_2() {
        DisorderedArray arr = getDisorderedArray();
        println("media: " + arr.median());
    }

    private static void exercise_4() {
        DisorderedArray arr = getDisorderedArray();
        arr.oddEvenSort();
        print("after oddEven sort: ");
        arr.display();                // display them again
    }

    private static void exercise_5() {
        DisorderedArray arr = getDisorderedArray();
        int count = arr.insertionSortAndCount();
        print("after insert sort: ");
        arr.display();                // display them again
        println("compare and move's count: " + count);
    }

    private static void exercise_6() {
        DisorderedArray arr = getDisorderedArrayWithDupData();
        arr.noDupByInsertionSort();
        print("after noDupByInsertionSort sort: ");
        arr.display();                // display them again
        println("反向插入排序，减少最后一次移动：");
        arr = getDisorderedArrayWithDupData();
        arr.noDupByInsertionSort2();
        print("after noDupByInsertionSort sort: ");
        arr.display();                // display them again
    }
}
