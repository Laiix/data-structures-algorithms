package com.eussi.data._10;

import static com.eussi.util.PrintUtil.println;

/**
 * @author wangxueming
 * @create 2020-02-26 1:41
 * @description
 */
public class Tree234Node<T extends Comparable<T>> {
    private static final int ORDER = 4;
    private int numItems;
    private Tree234Node<T> parent;
    private Tree234Node<T> childArray[] = new Tree234Node[ORDER];
    private DataItem<T> itemArray[] = new DataItem[ORDER - 1];

    public void connectChild(int childNum, Tree234Node<T> child) {
        childArray[childNum] = child;
        if (child != null)
            child.parent = this;
    }

    public Tree234Node<T> disconnectChild(int childNum) {
        Tree234Node<T> tempNode = childArray[childNum];
        childArray[childNum] = null;
        return tempNode;
    }

    public Tree234Node<T> getChild(int childNum) {
        return childArray[childNum];
    }

    public Tree234Node<T> getParent() {
        return parent;
    }

    public boolean isLeaf() {
        return childArray[0] == null;
    }

    public int getNumItems() {
        return numItems;
    }

    public DataItem<T> getItem(int index) {
        return itemArray[index];
    }

    public boolean isFull() {
        return numItems == ORDER - 1;
    }

    public int findItem(T key) {                                    // item (within node)
        for (int j = 0; j < ORDER - 1; j++) {                                 // otherwise,
            if (itemArray[j] == null)          // return -1
                break;
            else if (itemArray[j].data.equals(key))
                return j;
        }
        return -1;
    }

    public int insertItem(DataItem<T> newItem) {
        // assumes node is not full
        numItems++;                          // will add new item
        T newKey = newItem.data;         // key of new item

        for (int j = ORDER - 2; j >= 0; j--) {                                 //    examine items
            if (itemArray[j] != null) {         // if item null,
                T itsKey = itemArray[j].data;
                if (newKey.compareTo(itsKey)<0)            // if it's bigger
                    itemArray[j + 1] = itemArray[j]; // shift it right
                else {
                    itemArray[j + 1] = newItem;   // insert new item
                    return j + 1;                 // return index to
                }                           //    new item
            }  // end else (not null)
        }  // end for                     // shifted all items,
        itemArray[0] = newItem;              // insert new item
        return 0;
    }

    public DataItem<T> removeItem() {
        // assumes node not empty
        DataItem<T> temp = itemArray[numItems - 1];  // save item
        itemArray[numItems - 1] = null;           // disconnect it
        numItems--;                             // one less item
        return temp;                            // return item
    }

    public void displayNode() {
        for (int j = 0; j < numItems; j++)
            itemArray[j].displayItem();   // "/56"
        println("/");         // final "/"
    }
}
