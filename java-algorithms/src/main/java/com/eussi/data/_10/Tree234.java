package com.eussi.data._10;

import static com.eussi.util.PrintUtil.print;

public class Tree234<T extends Comparable<T>> {
    private Tree234Node<T> root = new Tree234Node<>();

    public int find(T key) {
        Tree234Node<T> curNode = root;
        int childNumber;
        while (true) {
            if ((childNumber = curNode.findItem(key)) != -1)
                return childNumber;               // found it
            else if (curNode.isLeaf())
                return -1;                        // can't find it
            else                                 // search deeper
                curNode = getNextChild(curNode, key);
        }
    }

    public void insert(T... data) {
        for (T datum : data) {
            insert(datum);
        }
    }

    public void insert(T data) {
        Tree234Node<T> curNode = root;
        DataItem<T> tempItem = new DataItem<>(data);

        while (true) {
            if (curNode.isFull()) {
                split(curNode);                   // split it
                curNode = curNode.getParent();    // back up
                // search once
                curNode = getNextChild(curNode, data);
            } else if (curNode.isLeaf())          // if node is leaf,
                break;                            // go insert
                // node is not full, not a leaf; so go to lower level
            else
                curNode = getNextChild(curNode, data);
        }

        curNode.insertItem(tempItem);       // insert new DataItem
    }

    public void split(Tree234Node<T> thisNode) {
        // assumes node is full
        Tree234Node<T> parent, child2, child3;

        //拿出节点与断开链接
        DataItem<T> itemC = thisNode.removeItem();    // remove items from
        DataItem<T> itemB = thisNode.removeItem();    // this node
        child2 = thisNode.disconnectChild(2); // remove children
        child3 = thisNode.disconnectChild(3); // from this node

        //确定父节点，并调整子树位置
        if (thisNode == root) {
            root = new Tree234Node<>();                // make new root
            parent = root;                    // root is our parent
            root.connectChild(0, thisNode);   // connect to parent
        } else                              // this node not the root
            parent = thisNode.getParent();    // get parent

        // deal with parent
        int itemIndex = parent.insertItem(itemB); // item B to parent
        int n = parent.getNumItems();         // total items?
        for (int j = n - 1; j > itemIndex; j--) {                                      // connections
            Tree234Node<T> temp = parent.disconnectChild(j); // one child
            parent.connectChild(j + 1, temp);        // to the right
        }
        //把新节点插入父节点
        Tree234Node<T> newRight = new Tree234Node<>();       // make new node
        parent.connectChild(itemIndex + 1, newRight);

        // deal with newRight
        newRight.insertItem(itemC);       // item C to newRight
        newRight.connectChild(0, child2); // connect to 0 and 1
        newRight.connectChild(1, child3); // on newRight
    }  // end split()

    private Tree234Node<T> getNextChild(Tree234Node<T> theNode, T theValue) {
        int j;
        int numItems = theNode.getNumItems();
        for (j = 0; j < numItems; j++) {                               // are we less?
            if (theValue.compareTo(theNode.getItem(j).data)<0)
                return theNode.getChild(j);  // return left child
        }  // end for                   // we're greater, so
        return theNode.getChild(j);        // return right child
    }

    public void displayTree() {
        recDisplayTree(root, 0, 0);
    }

    private void recDisplayTree(Tree234Node<T> thisNode, int level,
                                int childNumber) {
        print("level=" + level + " child=" + childNumber + " ");
        thisNode.displayNode();               // display this node

        // call ourselves for each child of this node
        int numItems = thisNode.getNumItems();
        for (int j = 0; j < numItems + 1; j++) {
            Tree234Node<T> nextNode = thisNode.getChild(j);
            if (nextNode != null)
                recDisplayTree(nextNode, level + 1, j);
            else
                return;
        }
    }  // end recDisplayTree()


    //编程作业10.1
    public T getMinValue() {
        Tree234Node<T> current = root;
        while (current!=null
                && current.getChild(0) != null) {
            current = current.getChild(0);
        }
        return current.getItem(0).data;
    }

    // 编程作业 10.2
    public void inOrderIter() {
        inOrder(this.root);
    }

    private void inOrder(Tree234Node<T> root) {
        if (root == null) {
            return;
        }
        int i = 0;
        for (; i < root.getNumItems(); i++) {
            inOrder(root.getChild(i));
            print(root.getItem(i).data + " ");
        }
        if (i != 0) {
            inOrder(root.getChild(i));
        }
    }

//    public void inOrderIter1() {
//        inOrder1(root);
//    }
//
//    private void inOrder1(Tree234Node<T> root) {
//        if(root!=null) {
//            int nItems = root.getNumItems();
//            int i=0;
//            for( ;i<nItems; i++) {
//                inOrder1(root.getChild(i));
//                println(root.getItem(i).data);
//            }
//            inOrder1(root.getChild(i));
//        }
//    }

    // 编程作业 10.3
    public void sort(T[] array) {
        this.root = new Tree234Node<>();
        for (int i = 0; i < array.length; i++) {
            this.insert(array[i]);
        }
        inOrderForSort(array, root, 0);
    }


    private int inOrderForSort(T[] array, Tree234Node<T> root, int arrayIndex) {
        if (root == null) {
            return arrayIndex;
        }
        int i = 0;
        for (; i < root.getNumItems(); i++) {
            arrayIndex = inOrderForSort(array, root.getChild(i), arrayIndex);
            array[arrayIndex++] = root.getItem(i).data;
        }
        if (i != 0) {
            arrayIndex = inOrderForSort(array, root.getChild(i), arrayIndex);
        }
        return arrayIndex;
    }



}
