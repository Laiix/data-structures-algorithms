package com.eussi.data._11;


import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * @author wangxueming
 * @create 2020-03-03 20:24
 * @description
 */
public class StringHashTable {
    private DataItem<String>[] hashArray;    // array holds hash table
    private int arraySize;
    private DataItem<String> nonItem;        // for deleted items


    public StringHashTable(int size) {
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem<>("--");   // deleted item key is ""
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

    // 编程作业 11.2
    public int hashFunc(String key) {
        int hashVal = 0;
        for (int j = 0; j < key.length(); j++) {
            int letter = key.charAt(j) - 96;
            hashVal = (hashVal * 26 + letter) % arraySize;
        }
        return hashVal;       // hash function
    }

    public void insert(DataItem<String> item) {
        String key = item.getKey();      // extract key
        int hashVal = hashFunc(key);  // hash the key
        // until empty cell or -1,
        while (hashArray[hashVal] != null
                && !hashArray[hashVal].getKey().equals("--")) {
            ++hashVal;                 // go to next cell
            hashVal %= arraySize;      // wraparound if necessary
        }
        hashArray[hashVal] = item;    // insert item
    }  // end insert()

    public DataItem<String> delete(String key) {
        int hashVal = hashFunc(key);  // hash the key

        while (hashArray[hashVal] != null) {                               // found the key?
            if (hashArray[hashVal].getKey().equals(key)) {
                DataItem<String> temp = hashArray[hashVal]; // save item
                hashArray[hashVal] = nonItem;       // delete item
                return temp;                        // return item
            }
            ++hashVal;                 // go to next cell
            hashVal %= arraySize;      // wraparound if necessary
        }
        return null;                  // can't find item
    }

    public DataItem<String> find(String key) {
        int hashVal = hashFunc(key);  // hash the key

        while (hashArray[hashVal] != null) {                               // found the key?
            if (hashArray[hashVal].getKey().equals(key))
                return hashArray[hashVal];   // yes, return item
            ++hashVal;                 // go to next cell
            hashVal %= arraySize;      // wraparound if necessary
        }
        return null;                  // can't find item
    }
}