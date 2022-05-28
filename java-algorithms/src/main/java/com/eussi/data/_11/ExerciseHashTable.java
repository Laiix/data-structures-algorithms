package com.eussi.data._11;

import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * @author wangxueming
 * @create 2020-03-03 20:09
 * @description 练习题中使用对象
 */
public class ExerciseHashTable {
    private DataItem<Integer>[] hashArray;    // array holds hash table
    private int arraySize;
    private DataItem<Integer> nonItem;        // for deleted items
    private int number;                // DataItem数目


    public ExerciseHashTable(int size) {
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem(-1);   // deleted item key is -1
        number = 0;
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


    // 编程作业 11.3
    public int hashFunc(int key) {
        int hashVal = 0;
        String skey = String.valueOf(key);
        int i = 0;
        for (; i + 2 <= skey.length(); i += 2) {
            String str = skey.substring(i, i + 2);
            hashVal += Integer.parseInt(str);
        }
        if (i < skey.length()) { //若是奇数位
            String str = skey.substring(i);
            hashVal += Integer.parseInt(str);
        }
        return hashVal % arraySize;
    }

    public int stepSize(int key, int index) {
        return 1;
    }

    // 编程作业 11.4
    public void rehash() {
        int resize = getPrime(arraySize * 2);
        println("rehash size:" + resize);
        ExerciseHashTable ht = new ExerciseHashTable(resize);
        for (int i = 0; i < arraySize; i++) {
            if (hashArray[i] != null && hashArray[i].getKey() != -1) {
                ht.insert(hashArray[i]);
            }
        }
        this.hashArray = ht.hashArray;
        this.arraySize = resize;
    }

    private int getPrime(int min) {
        for (int j = min + 1; true; j++) {
            if (isPrime(j)) {
                return j;
            }
        }
    }

    private boolean isPrime(int j) {
        for (int i = 2; i * i < j; i += 2) {
            if (j % i == 0) {
                return false;
            }
        }
        return true;
    }

    // insert a DataItem
    public void insert(DataItem<Integer> item) {
        if (number / (float) arraySize > 0.5) {
            rehash();
        }
        
        int key = item.getKey();      // extract key
        int hashVal = hashFunc(key);  // hash the key

        // until empty cell or -1
        int index = 0;
        while (hashArray[hashVal] != null &&
                hashArray[hashVal].getKey() != -1) {
            hashVal += stepSize(key, ++index);        // add the step
            hashVal %= arraySize;       // for wraparound
        }
        number++;
        hashArray[hashVal] = item;     // insert item
    }  // end insert()

    public DataItem<Integer> delete(int key) {
        int hashVal = hashFunc(key);      // hash the key

        int index = 0;
        while (hashArray[hashVal] != null) {                               // is correct hashVal?
            if (hashArray[hashVal].getKey() == key) {
                DataItem<Integer> temp = hashArray[hashVal]; // save item
                hashArray[hashVal] = nonItem;       // delete item
                number--;
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