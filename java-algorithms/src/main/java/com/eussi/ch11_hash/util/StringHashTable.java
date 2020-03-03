package com.eussi.ch11_hash.util;

/**
 * @author wangxueming
 * @create 2020-03-03 20:24
 * @description
 */
public class StringHashTable {
    private StringDataItem[] hashArray;    // array holds hash table
        private int arraySize;
        private StringDataItem nonItem;        // for deleted items

        // -------------------------------------------------------------

        public StringHashTable(int size)       // constructor
        {
            arraySize = size;
            hashArray = new StringDataItem[arraySize];
            nonItem = new StringDataItem("--");   // deleted item key is ""
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

        // =====================================================
        // 编程作业 11.2
        public int hashFunc(String key) {
            int hashVal = 0;
            for (int j = 0; j < key.length(); j++) {
                int letter = key.charAt(j) - 96;
                hashVal = (hashVal * 26 + letter) % arraySize;
            }
            return hashVal;       // hash function
        }

        // =====================================================

        // -------------------------------------------------------------
        public void insert(StringDataItem item) // insert a DataItem
        // (assumes table not full)
        {
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
        // -------------------------------------------------------------

        public StringDataItem delete(String key)  // delete a DataItem
        {
            int hashVal = hashFunc(key);  // hash the key

            while (hashArray[hashVal] != null)  // until empty cell,
            {                               // found the key?
                if (hashArray[hashVal].getKey().equals(key)) {
                    StringDataItem temp = hashArray[hashVal]; // save item
                    hashArray[hashVal] = nonItem;       // delete item
                    return temp;                        // return item
                }
                ++hashVal;                 // go to next cell
                hashVal %= arraySize;      // wraparound if necessary
            }
            return null;                  // can't find item
        }  // end delete()
        // -------------------------------------------------------------

        public StringDataItem find(String key)    // find item with key
        {
            int hashVal = hashFunc(key);  // hash the key

            while (hashArray[hashVal] != null)  // until empty cell,
            {                               // found the key?
                if (hashArray[hashVal].getKey().equals(key))
                    return hashArray[hashVal];   // yes, return item
                ++hashVal;                 // go to next cell
                hashVal %= arraySize;      // wraparound if necessary
            }
            return null;                  // can't find item
        }
// -------------------------------------------------------------
}