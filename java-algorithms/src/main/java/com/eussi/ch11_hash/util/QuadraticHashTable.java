package com.eussi.ch11_hash.util;

/**
 * @author wangxueming
 * @create 2020-03-03 20:09
 * @description
 */
public class QuadraticHashTable {
    private DataItem[] hashArray;    // array holds hash table
    private int arraySize;
    private DataItem nonItem;        // for deleted items
    private int number;                // DataItem数目

    // -------------------------------------------------------------

    public QuadraticHashTable(int size)       // constructor
    {
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem(-1);   // deleted item key is -1
        number = 0;
    }

    // -------------------------------------------------------------
    public void displayTable() {
        System.out.print("Table: ");
        for (int j = 0; j < arraySize; j++) {
            if (hashArray[j] != null)
                System.out.print(hashArray[j].getKey() + " ");
            else
                System.out.print("** ");
        }
        System.out.println("");
    }

    // -------------------------------------------------------------
    public int hashFunc(int key) {
        return key % arraySize;       // hash function
    }

    // =====================================================
    // 编程作业 11.3
    public int hashFunc1(int key) {
        int hashVal = 0;
        String skey = String.valueOf(key);
        int i = 0;
        for (; i + 2 < skey.length(); i += 2) {
            String str = skey.substring(i, i + 2);
            hashVal += Integer.valueOf(str).intValue();
        }
        if (i < skey.length()) { //若是奇数位
            String str = skey.substring(i);
            hashVal += Integer.valueOf(str).intValue();
        }
        return hashVal % arraySize;
    }

    // =====================================================
    // 编程作业 11.4
    public void rehash() {
        int resize = getPrime(arraySize * 2);
        System.out.println("rehash size:" + resize);
        QuadraticHashTable ht = new QuadraticHashTable(resize);
        for (int i = 0; i < arraySize; i++) {
            if (hashArray[i] != null && hashArray[i].getKey() != -1) {
                ht.insert(hashArray[i]);
            }
        }
        this.hashArray = ht.hashArray;
        this.arraySize = resize;
    }

    // =====================================================
    private int getPrime(int min) {
        for (int j = min + 1; true; j++) {
            if (isPrime(j)) {
                return j;
            }
        }
    }

    // =====================================================
    private boolean isPrime(int j) {
        for (int i = 2; i * i < j; i += 2) {
            if (j % i == 0) {
                return false;
            }
        }
        return true;
    }

    // =====================================================

    // -------------------------------------------------------------
    public void insert(DataItem item) // insert a DataItem
    // (assumes table not full)
    {
        if (number / (float) arraySize > 0.5) {
            rehash();
        }
        int key = item.getKey();      // extract key
        // int hashVal = hashFunc(key); // hash the key
        int hashVal = hashFunc1(key); // hash the key
        // until empty cell or -1,
        int i = 1, index = hashVal;
        while (hashArray[index] != null && hashArray[index].getKey() != -1) {
            // =================================
            // 编程作业 11.1
            index = hashVal + i * i;
            i++;
            // =================================
            index %= arraySize;      // wraparound if necessary
        }
        number++;
        hashArray[index] = item;    // insert item
    }  // end insert()
    // -------------------------------------------------------------

    public DataItem delete(int key)  // delete a DataItem
    {
        // int hashVal = hashFunc(key); // hash the key
        int hashVal = hashFunc1(key); // hash the key

        int i = 1, index = hashVal;
        while (hashArray[index] != null)  // until empty cell,
        {                               // found the key?
            if (hashArray[index].getKey() == key) {
                DataItem temp = hashArray[index]; // save item
                hashArray[index] = nonItem;       // delete item
                number--;
                return temp;                        // return item
            }
            // =================================
            // 编程作业 11.1
            index = hashVal + i * i;
            i++;
            // =================================
            index %= arraySize;      // wraparound if necessary
        }
        return null;                  // can't find item
    }  // end delete()
    // -------------------------------------------------------------

    public DataItem find(int key)    // find item with key
    {
        // int hashVal = hashFunc(key); // hash the key
        int hashVal = hashFunc1(key); // hash the key

        int i = 1, index = hashVal;
        while (hashArray[index] != null)  // until empty cell,
        {                               // found the key?
            if (hashArray[index].getKey() == key)
                return hashArray[index];   // yes, return item

            // =================================
            // 编程作业 11.1
            index = hashVal + i * i;
            i++;
            // =================================
            index %= arraySize;      // wraparound if necessary
        }
        return null;                  // can't find item
    }
    // -------------------------------------------------------------
}