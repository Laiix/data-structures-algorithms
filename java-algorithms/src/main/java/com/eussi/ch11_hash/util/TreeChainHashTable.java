package com.eussi.ch11_hash.util;

/**
 * @author wangxueming
 * @create 2020-03-03 21:11
 * @description
 */
public class TreeChainHashTable {
    private Tree[] hashArray;   // array of lists
    private int arraySize;

    // -------------------------------------------------------------
    public TreeChainHashTable(int size)        // constructor
    {
        arraySize = size;
        hashArray = new Tree[arraySize];  // create array
        for (int j = 0; j < arraySize; j++)
            // fill array
            hashArray[j] = new Tree();     // with lists
    }

    // -------------------------------------------------------------
    public void displayTable() {
        for (int j = 0; j < arraySize; j++) // for each cell,
        {
            System.out.print(j + ". "); // display cell number
            hashArray[j].displayTree(); // display list
        }
        System.out.println("TreeChainHash display end.\n");
    }

    // -------------------------------------------------------------
    public int hashFunc(int key)      // hash function
    {
        return key % arraySize;
    }

    // -------------------------------------------------------------
    public void insert(int key)  // insert a link
    {
        int hashVal = hashFunc(key);   // hash the key
        if (this.find(key) != null) {
            return;
        }
        hashArray[hashVal].insert(key); // insert at hashVal
    }  // end insert()
    // -------------------------------------------------------------

    public void delete(int key)       // delete a link
    {
        int hashVal = hashFunc(key);   // hash the key
        hashArray[hashVal].delete(key); // delete link
    }  // end delete()
    // -------------------------------------------------------------

    public Node find(int key)         // find link
    {
        int hashVal = hashFunc(key);   // hash the key
        Node theNode = hashArray[hashVal].find(key);  // get link
        return theNode;                // return link
    }
    // -------------------------------------------------------------
}  // end class HashTable