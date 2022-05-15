package com.eussi.util;

import com.eussi.data._02.SimpleArray;

import java.util.Arrays;

import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

public class Func {


    /**
     * 获取一个填充的无序数组，并打印
     * @return
     */
    public static SimpleArray getSimpleArray() {
        int maxSize = 100; // array size
        SimpleArray arr; // reference to array
        arr = new SimpleArray(maxSize); // create the array

        arr.insert(77, 99, 44, 55, 22, 88, 11, 0, 66, 33);
        print("No order ");
        arr.display(); // display items
        return arr;
    }

    /**
     * 获取一个填充的有重复数据的无序数组，并打印
     * @return
     */
    public static SimpleArray getSimpleArrayWithDupData() {
        int maxSize = 100; // array size
        SimpleArray arr; // reference to array
        arr = new SimpleArray(maxSize); // create the array

        arr.insert(33, 33, 44, 22, 11, 0, 22, 33);
        print("No order with dup data ");
        arr.display(); // display items
        return arr;
    }

    /**
     * 获取一个随机数填充的int数组
     * @param size
     * @param max
     * @return
     */
    public static int[] getRandomIntArr(int size, int max) {
        int[] arr = new int[size];
        for(int j = 0; j< size; j++) {                          // random numbers
            int n = (int)(Math.random()*max);
            arr[j] = n;
        }
        print("random int arr: ");
        println(Arrays.toString(arr));
        return arr;
    }

    /**
     * 获取一个数据，填充随机数据
     * @param maxSize
     * @param max
     * @return
     */
    public static SimpleArray getRandomSimpleArray(int maxSize, int max) {
        SimpleArray arr = new SimpleArray(maxSize);   // create the array

        for(int j = 0; j< maxSize; j++) {                          // random numbers
            long n = (int)(Math.random()*max);
            arr.insert(n);
        }
        print("No order with random data ");
        arr.display();
        return arr;
    }

}
