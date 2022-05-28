package com.eussi.data._11;


import com.eussi.data._08.Tree;
import com.eussi.data._08.TreeNode;

import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * @author wangxueming
 * @create 2020-03-03 21:11
 * @description
 */
public class TreeChainHashTable {
    private Tree<Integer>[] hashArray;   // array of lists
    private int arraySize;

    public TreeChainHashTable(int size) {
        arraySize = size;
        hashArray = new Tree[arraySize];  // create array
        for (int j = 0; j < arraySize; j++)
            // fill array
            hashArray[j] = new Tree<>();     // with lists
    }

    public void displayTable() {
        for (int j = 0; j < arraySize; j++) {
            print(j + ". "); // display cell number
            hashArray[j].traverse(2);//中序遍历
        }
        println("TreeChainHash display end.\n");
    }

    public int hashFunc(int key) {
        return key % arraySize;
    }

    public void insert(int key) {
        int hashVal = hashFunc(key);   // hash the key
        if (this.find(key) != null) {
            return;
        }
        hashArray[hashVal].insert(key); // insert at hashVal
    }  // end insert()

    public void delete(int key) {
        int hashVal = hashFunc(key);   // hash the key
        hashArray[hashVal].delete(key); // delete link
    }

    public TreeNode<Integer> find(int key) {
        int hashVal = hashFunc(key);   // hash the key
        return hashArray[hashVal].find(key);                // return link
    }
}