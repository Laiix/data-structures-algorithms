package com.eussi.ch02_arrays;

import com.eussi.ch02_arrays.util.HighArray;
import com.eussi.ch02_arrays.util.OrdArray;
import com.eussi.util.Util;

/**
 * @author wangxueming
 * @create 2019-10-18 10:57
 * @description
 */
public class Exercise {
    public static void main(String[] args) {
        /**
         * 编程作业
         *     2.1 向HighArray.java程序的HighArray类添加一个名为getMax()的方法，它返回
         *  		数组中最大关键字的值，当数组为空时返回-1。向main()中添加一些代码来使用这个方法。
         *  		可以假设所有关键字都是正数。
         *     2.2 修改编程作业2.1中的方法，使之不仅返回最大的关键字，而且还将该关键字从数组中删除。
         *     	    将这个方法命名为removeMax()。
         *     2.3 编程作业2.2中的removeMax()方法提供了一种通过关键字值进行数组排序的方法。实现一个
         *     	    排序方案，要求不修改HighArray类，只需对main()中的代码进行修改。这个方法需要第二个
         *     	    数组，在排序结束时数组数据项是逆序排列的。（这个方法是第3章“简单排序”中选择排序的
         *     	    一个变体。）
         *     2.4 修改OrdArray.java程序（清单2.4）使insert()、delete()与find()方法一样都使用
         *     	    二分查找，正如书中所建议的那样。
         *     2.5 向OrdArray.java程序（清单2.4）的OrdArray类加入一个merge()方法，使之可以将两个
         *  		有序的源数组合并成一个有序的目的数组。在main()中添加代码，向两个源数组中插入随机数，
         *  		调用merge()方法，并将结果目的数组显示出来。两个源数组的数据项个数可能不同。在算法中
         *  		需要先比较源数组中的关键字，从中选出最小的一个数据项复制到目的数组。同时还要考虑如何
         *  		解决当一个源数组的数据项已经取完而另一个还剩一些数据项情况。
         *     2.6 向HighArray.java程序（清单2.3）的HighArray类中加入一个noDup()方法，使之可以将数组中
         *  		的所有重复数据项删除。即如果数组中有三个数据项的关键字为17，noDup()方法会删除其中的
         *  		两个。不必考虑保持数据项的顺序。一种方法是先用每一个数据项同其他数据项比较，并用null
         *  		(或是一个不会用在真正的关键字中的特殊值)将重复的数据项覆盖掉。然后将所有的null删除，
         *  		当然还要缩小数组的大小。
         */

        testHighArray();
        Util.printSeparator();

        testOrdArray();


    }

    private static void testOrdArray() {
        int maxSize = 1000; // array size
        OrdArray arr; // reference to array
        arr = new OrdArray(maxSize); // create the array
        arr.insert(77); // insert 10 items
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(33);

        int searchKey = 55; // search for item
        if (arr.find(searchKey) != arr.size())
            System.out.println("Found " + searchKey);
        else
            System.out.println("Can't find " + searchKey);

        arr.display(); // display items

        arr.delete(00); // delete 3 items
        arr.delete(55);
        arr.delete(99);

        arr.display(); // display items again

        // ============================
        // 编程作业2.4 p50(69)
        arr = new OrdArray(maxSize); // create the array
        arr.insert1(4); // insert 10 items
        arr.insert1(6);
        arr.insert1(8);
        arr.insert1(1);

        arr.insert2(33);
        arr.insert2(0);
        arr.insert2(22);
        arr.insert2(13);
        arr.insert2(59);
        arr.insert2(9);


        arr.display(); // display items again

        // 编程作业2.5 p50(69)
        System.out.println("第二个数组:");
        OrdArray arr1 = new OrdArray(maxSize); // create the array
        arr1.insert(10);
        arr1.insert(20);
        arr1.insert(30);
        arr1.insert(40);
        arr1.insert(50);
        arr1.insert(60);
        arr1.insert(70);
        arr1.display();
        System.out.println("合并两个数组，生成新的数组:");
        OrdArray arr2 = arr.merge(arr1);
        arr2.display();
        arr2 = arr.merge2(arr1);
        arr2.display();
        // ============================
    }

    private static void testHighArray() {
        int maxSize = 100; // array size
        HighArray arr; // reference to array
        arr = new HighArray(maxSize); // create the array

        arr.insert(77); // insert 10 items
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(33);

        arr.display(); // display items

        int searchKey = 35; // search for item
        if (arr.find(searchKey))
            System.out.println("Found " + searchKey);
        else
            System.out.println("Can't find " + searchKey);

        arr.delete(00); // delete 3 items
        arr.delete(55);
        arr.delete(99);

        arr.display(); // display items again

        // =======================================================
        // p50(69) 编程作业2.1
        long max = arr.getMax();
        System.out.println("Found max is " + max);
        // =======================================================
        // p50(69) 编程作业2.2
        arr.removeMax();
        arr.display();
        // =======================================================
        // p50(69) 编程作业2.3
        HighArray sortedArr = new HighArray(maxSize);
        int i = 0;
        max = arr.removeMax();
        while (max != -1) {
            sortedArr.insert(max);
            max = arr.removeMax();
        }
        System.out.println("逆序排列:");
        sortedArr.display();
        // =======================================================
        arr.insert(77); // insert 10 items
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        // 重复值
        arr.insert(44);
        arr.insert(77);
        arr.insert(44);
        arr.insert(66);

        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(33);

        System.out.println("加入重复值后：");
        arr.display();
        arr.noDup();
        System.out.println("去掉重复值后：");
        arr.display();

        System.out.println("加入重复值后：");
        arr.insert(3);
        arr.insert(5);
        arr.insert(3);
        arr.insert(3);
        arr.insert(5);
        arr.display();
        arr.noDup1();
        System.out.println("去掉重复值后：");
        arr.display();
        // =======================================================
    }
}
