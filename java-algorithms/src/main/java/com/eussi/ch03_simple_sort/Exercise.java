package com.eussi.ch03_simple_sort;

import com.eussi.ch03_simple_sort.util.ArrayBub;
import com.eussi.ch03_simple_sort.util.ArrayIns;
import com.eussi.ch03_simple_sort.util.ArrayOddEven;
import com.eussi.util.PrintUtil;

/**
 * @author wangxueming
 * @create 2019-10-22 13:52
 * @description
 */
public class Exercise {

    public static void main(String[] args) {
        /**
         * 编程作业:
         *      3.1 ArrayBub.java程序，in索引变量都是从左到
         *   		右移动的，直到找到最大数据项并把它移动到右边的out变量外。修改bubbleSort()方法，
         *   		使它成为双向移动的。这样，in索引先像以前一样，将最大的数据项从左移到右，当它到
         *   		达out变量位置时，它掉头并把最小的数据项从右移到左。需要两个外部索引变量，一个在
         *   		右边（以前的out变量），另一个在左边。
         *   	3.2	在ArrayIns.java程序(清单3.3)中给ArrayIns类加一个median()方法.这个方法将返回
         *   		数组的中间值.(回忆一下,数组中一半数据项比中间值大,一半数据项比中间值小。)
         *  	3.3	在insertSort.java程序（清单3.3）中增加一个名为noDups()的方法，这个方法从已经有
         *  		序的数组中删掉重复的数据项而不破坏有序性。（可以用insertionSort()方法对数据排序，
         *  		或者也可以简单地用main()方法将数据有序地插入到表中。）一种解决方法是每发现一个重
         *  		复的数据，就从这个位置开始到数组结尾都向前移动一个位置，但这样就导致消耗很长的
         *  		O(N2)的时间级，起码在有很多重复数据项的情况下是这样的。在设计的算法中，不论有多
         *  		少重复数据，要确保数据项最多只能移动一次。这样算法只消耗O(N)数量级的时间。
         *  	3.4  还有一种简单排序算法是奇偶排序。它的思路是在数组中重复两趟扫描。第一趟扫描选择所有
         *  		的数据项对，a[j]和a[j+1]，j是奇数(j=1,3,5,……)。如果它们的关键字的值次序颠倒，就交
         *  		换它们。第二趟扫描对所有的偶数数据项进行同样的操作（j=2,4,6,……）。重复进行这样两趟
         *  		的排序直到数组全部有序。用oddEvenSort()方法替换bubbleSort.java程序（清单3.1）中的
         *  		bubbleSort()方法。确保它可以在不同数据量的排序中运行，还需要算出两趟扫描的次数。
         *  		    奇偶排序实际上在多处理器环境中很有用，处理器可以分别同时处理每一个奇数对，然后又同时
         *  		处理偶数对。因为奇数对是彼此独立的，每一对都可以用不同的处理器比较和交换。这样可以非
         *  		常快速地排序。
         *  	3.5	修改ArrayIns.java程序中的insertionSort()方法，使它可以计算排序过
         *  		程中复制和比较的次数并显示出总数。为计算比较的次数，要把内层while循环的两个条件分开。
         *  		用这个程序测量各种数量的逆序数据排序的复制和比较次数。结果满足O(N2)吗？与已经基本有
         *  		序的数据（仅有很少的数据无序）的情况一样吗？从对基本有序数据排序的表现中可得出关于这
         *  		个算法效率的什么结论？
         *  	3.6	有一个有趣的方法用来删除数组中相同的数据项。插入排序算法中用一个循环嵌套算法，将
         *  		数组中的每一个数据项与其他数据项一一比较。如果要删除相同的数据项，可以这样做（参见第
         *  		2章第2.6小节）。修改insertSort.java中的insertionSort()方法，使它可以在排序过程中
         *  		删除相同的数据项。方法如下：当找到一个重复数据项的时候，通常用一个小于任何值的关键值
         *  		来改写这个相同数据项（如果所有值都是正数，则可取-1）。于是，一般的插入排序算法就会像
         *  		处理其他数据项一样，来处理这个修改了关键值的数据项，把它移到下标为0的位置。从现在开
         *  		始，算法可以忽略这个数据项。下一个相同的数据项将被移到下标为1的位置，依此类推。排序
         *  		完成后，所有相同的数据项（现在关键值为-1）都在数组的开头部分。可以改变数组的容量并
         *  		把需要的数据前移动数组下标为0的位置。
         *
         */

        //习题3.1
        testBubbleSort1();
        PrintUtil.sep();

        //测试去重 习题3.3
        testInsertSort();
        PrintUtil.sep();

        //测试奇偶排序 习题3.4
        /**
         * 思路是在数组中重复两趟扫描。第一趟扫描选择所有的数据项对，a[j]和a[j+1]，j是奇数。如果他们的关键字的
         * 值次序颠倒就交换他们。第二趟扫描对所有的偶数数据项进行同样的操作，j是偶数。重复进行这样的两趟的排序
         * 直到数组全部有序。
         */
        testOddEvenSort();
        PrintUtil.sep();
        /**
         * 奇偶排序实际上在多处理器环境中很有用，处理器可以分别同时处理每一个奇数时，然后又同时处理偶数对。
         * 因为奇数对是彼此独立的，每一对都可以用不同的处理器比较和交换，这样可以非常快速的排序。
         */

        testInsertSort23();

    }

    private static void testInsertSort23() {
        // ======================================
        // 编程作业3.5 P79(98)
        ArrayIns arr = new ArrayIns(100); // create the array
        int count;
        for (int i = 19; i >= 0; i--) {// 初始化为逆序数组
            arr.insert(i);
        }
        arr.insert(19);
        arr.insert(9);
        arr.insert(0);
        arr.display();
        count = arr.insertionSort1();
        arr.display();
        System.out.println("逆序数组比较复制总数:" + count); // 满足O(N^2)

        arr = new ArrayIns(100); // create the array
        for (int i = 0; i <= 19; i++) {// 初始化为顺序数组
            arr.insert(i);
        }
        arr.insert(19);
        arr.insert(9);
        arr.insert(0);
        arr.display();
        count = arr.insertionSort1();
        arr.display();
        System.out.println("顺序数组比较复制总数:" + count); // 满足O(N)
        // ======================================
        // 编程作业3.6 P79(98)
        arr = new ArrayIns(100); // create the array
        arr.insert(2);
        arr.insert(3);
        arr.insert(4);
        arr.insert(3);
        arr.insert(3);
        arr.insert(1);
        arr.insert(2);
        arr.insert(1);
        arr.insert(1);
        arr.insert(1);
        System.out.println("插入重复值后：");
        arr.display();
        arr.insertionSort2();
        System.out.println("删除重复值后：");
        arr.display();
        // ======================================

    }

    private static void testOddEvenSort() {
        int maxSize = 100;
        ArrayOddEven arr;
        arr = new ArrayOddEven(maxSize);

        arr.insert(77);
        arr.insert(99);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(44);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(33);

        arr.display();

        arr.oddEvenSort();

        arr.display();
    }

    private static void testInsertSort() {
        // 编程作业3.3 P78(97)
        ArrayIns arr = new ArrayIns(100); // create the array
        arr.insert(2);
        arr.insert(3);
        arr.insert(4);
        arr.insert(3);
        arr.insert(3);
        arr.insert(1);
        arr.insert(2);
        arr.insert(1);
        arr.insert(1);
        arr.insert(1);
        System.out.println("插入重复值后：");
        arr.display();
        arr.noDups();
        System.out.println("删除重复值后：");
        arr.display();
    }

    private static void testBubbleSort1() {
        int maxSize = 100;            // array size
        ArrayBub arr;                 // reference to array
        arr = new ArrayBub(maxSize);  // create the array

        arr.insert(77);               // insert 10 items
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(0);

        arr.display();                // display items

        //变成作业3.1
//        arr.bubbleSort1();             // bubble sort them
        arr.bubbleSort2();             // bubble sort them

        arr.display();                // display them again
    }
}
