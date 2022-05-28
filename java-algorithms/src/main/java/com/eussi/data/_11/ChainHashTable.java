package com.eussi.data._11;

import com.eussi.data._05.Link;
import com.eussi.data._05.OrderedLinklist;

import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * @author wangxueming
 * @create 2020-03-03 11:03
 * @description
 */
public class ChainHashTable {
    private OrderedLinklist<Integer>[] hashArray;   // array of lists
    private int arraySize;

    public ChainHashTable(int size) {
        arraySize = size;
        hashArray = new OrderedLinklist[arraySize];  // create array
        for (int j = 0; j < arraySize; j++)          // fill array
            hashArray[j] = new OrderedLinklist<>();     // with lists
    }

    public void displayTable() {
        for (int j = 0; j < arraySize; j++) {
            print(j + ". "); // display cell number
            hashArray[j].displayList(); // display list
        }
        println("Display end. \n");
    }

    public int hashFunc(int key) {
        return key % arraySize;
    }

    public void insert(Integer key) {
        int hashVal = hashFunc(key);   // hash the key
        hashArray[hashVal].insert(key); // insert at hashVal
    }

    public void delete(int key) {
        int hashVal = hashFunc(key);   // hash the key
        hashArray[hashVal].delete(key); // delete link
    }  // end delete()

    public Integer find(int key) {
        int hashVal = hashFunc(key);   // hash the key
        Link<Integer> theLink = hashArray[hashVal].find(key);  // get link
        return theLink==null?null:theLink.data;
    }
}