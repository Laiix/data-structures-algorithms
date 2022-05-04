package com.eussi.util;

import com.eussi.data._02.DisorderedArray;

import static com.eussi.util.PrintUtil.print;

public class Func {


    /**
     * 获取一个填充的无序数组，并打印
     * @return
     */
    public static DisorderedArray getDisorderedArray() {
        int maxSize = 100; // array size
        DisorderedArray arr; // reference to array
        arr = new DisorderedArray(maxSize); // create the array

        arr.insert(77); // insert 10 items
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(0);
        arr.insert(66);
        arr.insert(33);
        print("No order after insert: ");
        arr.display(); // display items
        return arr;
    }

    /**
     * 获取一个填充的有重复数据的无序数组，并打印
     * @return
     */
    public static DisorderedArray getDisorderedArrayWithDupData() {
        int maxSize = 100; // array size
        DisorderedArray arr; // reference to array
        arr = new DisorderedArray(maxSize); // create the array

        arr.insert(33); // insert 10 items
        arr.insert(33);
        arr.insert(44);
        arr.insert(22);
        arr.insert(11);
        arr.insert(0);
        arr.insert(22);
        arr.insert(33);
        print("No order with dup data after insert: ");
        arr.display(); // display items
        return arr;
    }

}
