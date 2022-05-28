package com.eussi.data._11;



import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * @author wangxueming
 * @create 2020-03-03 0:23
 * @description
 */
public class HashTable {
    private DataItem<Integer>[] hashArray;     // array is the hash table
    private int arraySize;
    private DataItem<Integer> nonItem;         // for deleted items

    private final FuncEnum funcEnum;//探测方式

    public enum FuncEnum {
        linear, //线性探测
        quadratic, //二次探测
        rehash //再哈希
    }

    public HashTable(int size, FuncEnum funcEnum) {
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem<>(-1);
        this.funcEnum = funcEnum;
    }

    public void displayTable() {
        print("Table: ");
        for (int j = 0; j < arraySize; j++) {
            if (hashArray[j] != null)
                print(hashArray[j].getKey() + " ");
            else
                print("** ");
        }
        println("");
    }

    public int hashFunc(int key) {
        return key % arraySize;
    }

    public int stepSize(int key, int index) {
        switch (funcEnum) {
            case linear:
                return 1;
            case quadratic:
                return (index*index)-(index-1)*(index-1); //1, 3, 5, 7...
            case rehash:
                // non-zero, less than array size, different from hF1
                // array size must be relatively prime to 5, 4, 3, and 2
                return 5 - key % 5;
        }
        throw new RuntimeException("invalid funcEnum. ");
    }

    // insert a DataItem
    public void insert(DataItem<Integer> item) {
        int key = item.getKey();      // extract key
        int hashVal = hashFunc(key);  // hash the key

        // until empty cell or -1
        int index = 0;
        while (hashArray[hashVal] != null &&
                hashArray[hashVal].getKey() != -1) {
            hashVal += stepSize(key, ++index);        // add the step
            hashVal %= arraySize;       // for wraparound
        }
        hashArray[hashVal] = item;     // insert item
    }  // end insert()

    public DataItem<Integer> delete(int key) {
        int hashVal = hashFunc(key);      // hash the key

        int index = 0;
        while (hashArray[hashVal] != null) {                               // is correct hashVal?
            if (hashArray[hashVal].getKey() == key) {
                DataItem<Integer> temp = hashArray[hashVal]; // save item
                hashArray[hashVal] = nonItem;       // delete item
                return temp;                        // return item
            }
            hashVal += stepSize(key, ++index);            // add the step
            hashVal %= arraySize;           // for wraparound
        }
        return null;                   // can't find item
    }  // end delete()

    public DataItem<Integer> find(int key) {
        int hashVal = hashFunc(key);      // hash the key

        int index = 0;
        while (hashArray[hashVal] != null) {                               // is correct hashVal?
            if (hashArray[hashVal].getKey() == key)
                return hashArray[hashVal];   // yes, return item
            hashVal += stepSize(key, ++index);            // add the step
            hashVal %= arraySize;           // for wraparound
        }
        return null;                   // can't find item
    }
}