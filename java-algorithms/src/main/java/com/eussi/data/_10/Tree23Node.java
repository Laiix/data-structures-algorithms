package com.eussi.data._10;

/**
 * @author wangxueming
 * @create 2020-02-28 15:43
 * @description
 */
public class Tree23Node<T extends Comparable<T>> {
    private static final int ORDER = 3;
    private int numItems;
    private Tree23Node<T> parent;
    private Tree23Node<T> childArray[] = new Tree23Node[ORDER];
    private DataItem<T> itemArray[] = new DataItem[ORDER - 1];

    public void connectChild(int childNum, Tree23Node<T> child) {
        childArray[childNum] = child;
        if (child != null)
            child.parent = this;
    }

    public Tree23Node<T> disconnectChild(int childNum) {
        Tree23Node<T> tempNode = childArray[childNum];
        childArray[childNum] = null;
        return tempNode;
    }

    public Tree23Node<T> getChild(int childNum) {
        return childArray[childNum];
    }

    public Tree23Node<T> getParent() {
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

        for (int j = ORDER - 2; j >= 0; j--) {                                 // examine items
            if (itemArray[j] == null)          // if item null,
                continue;                      // go left one cell
            else {                              // get its key
                T itsKey = itemArray[j].data;
                if (newKey.compareTo(itsKey) < 0)            // if it's bigger
                    itemArray[j + 1] = itemArray[j]; // shift it right
                else {
                    itemArray[j + 1] = newItem;   // insert new item
                    return j + 1;                 // return index to
                }                           // new item
            }  // end else (not null)
        }  // end for // shifted all items,
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
        System.out.println("/");         // final "/"
    }
}
