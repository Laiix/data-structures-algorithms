package com.eussi.chapter02;

import com.eussi.chapter02.helper.OrdArray;

/**
 * @author wangxueming
 * @create 2019-10-17 22:16
 * @description
 */
public class Arrays {
    public static void main(String[] args) {
        /**
         * 数组操作平均比较次数和移动次数（N是数据项个数，插入一个数据为一次移动）
         *
         *                          不允许重复                       允许重复
         * 查找                       N/2次比较                      N次比较
         * 插入（简单插入一个空位上）   无比较,一次移动 （？需要比较吧） 无比较,一次移动
         * 删除                       N/2次比较,N/2次移动             N次比较,多于N/2次移动
         *
         * 通常认为N和N/2之间的差异不很重要,除非正在对程序进行微调以达到最优。
         * 更重要的是操作是否需要执行一步、N步、log(N)步或N²步
         *
         */

        /**
         * 有序数组常用操作，重点在二分查找
         *
         */
        System.out.println("Order Array:");
        int maxSize = 100;             // array size
        OrdArray arr;                  // reference to array
        arr = new OrdArray(maxSize);   // create the array
        // insert 10 items
        arr.insert(77);
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(33);
        // search for item
        int searchKey = 55;
        if( arr.find(searchKey) != arr.size() )
            System.out.println("Found " + searchKey);
        else
            System.out.println("Can't find " + searchKey);
        // display items
        arr.display();
        // delete 3 items
        arr.delete(00);
        arr.delete(55);
        arr.delete(99);
        // display items again
        arr.display();

        /**
         * 有序数组的优点
         *      使用有序数组会给我们带来什么好处?最主要的好处是查找的速度比无序数组快多了。不好的
         * 方面是在插入操作中由于所有靠后的数据都需移动以腾开空间,所以速度较慢。有序数组和无序数
         * 组中的删除操作都很慢,这是因为数据项必须向前移动来填补已删除数据项的洞
         *      有序数组在查找频繁的情况下十分有用,但若是插入与删除较为频繁时,则无法高效工作。例
         * 如,有序数组适合于公司雇员的数据库。雇用和解雇雇员同读取一个已存在雇员的有关信息或更改
         * 薪水、住址等信息相比,前两者是不经常发生的。
         *      另一方面,零售商店的存货清单不适合用有序数组来实现,这是由于与频繁的进货和出货相应
         * 的插入与删除操作都会执行得很慢
         */
    }
}
