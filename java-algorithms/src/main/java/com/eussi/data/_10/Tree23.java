package com.eussi.data._10;

import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * @author wangxueming
 * @create 2020-02-28 15:43
 * @description
 */
public class Tree23<T extends Comparable<T>> {
    private Tree23Node<T> root = new Tree23Node<>();            // make root node


    public int find(T key) {
        Tree23Node<T> curNode = root;
        int childNumber;
        while (true) {
            if ((childNumber = curNode.findItem(key)) != -1)
                return childNumber;               // found it
            else if (curNode.isLeaf())
                return -1;                        // can't find it
            else
                // search deeper
                curNode = getNextChild(curNode, key);
        }  // end while
    }

    public void simpleInsert(T... data) {
        for (T datum : data) {
            simpleInsert(datum);
        }
    }

    // insert a DataItem
    public void simpleInsert(T data) {
        Tree23Node<T> curNode = root;
        DataItem<T> tempItem = new DataItem<>(data);

        while (true) {
            if (curNode.isLeaf())          // if node is leaf,
                break;                            // go insert
                // node is not full, not a leaf; so go to lower level
            else
                curNode = getNextChild(curNode, data);
        }
        if (curNode.isFull()) {
            simpleSplit(curNode, tempItem); //只分裂叶节点
        } else {
            curNode.insertItem(tempItem);       // insert new DataItem
        }
    }


    // 编程作业 10.4
    // thisNode 当前要分裂的节点 ,只能为叶子节点
    // newItem 要插入的数据项
    public void simpleSplit(Tree23Node<T> thisNode, DataItem<T> newItem) {
        // assumes node is full
        DataItem<T> itemB, itemC; // itemB保存中间数据项,itemC保存最大数据项
        // 分裂当前节点
        if (thisNode.getItem(0).data.compareTo(newItem.data) > 0) { // 1.新数据项最小
            itemC = thisNode.removeItem();
            itemB = thisNode.removeItem();
            thisNode.insertItem(newItem);
        } else if (thisNode.getItem(1).data.compareTo(newItem.data) < 0) {// 2.新数据项最大
            itemC = newItem;
            itemB = thisNode.removeItem();
        } else {// 3.新数据项在中间
            itemC = thisNode.removeItem();
            itemB = newItem;
        }
        // 新节点
        Tree23Node<T> newRight = new Tree23Node<>();       // make new node
        newRight.insertItem(itemC);       // item C to newRight

        // 得到父节点
        Tree23Node<T> parent;
        if (thisNode != root) {
            parent = thisNode.getParent();
        } else {
            root = new Tree23Node<>();                // make new root
            parent = root;                    // root is our parent
            root.connectChild(0, thisNode);   // connect to parent
        }

        // 中间数据项插入到父节点
        if (parent.isFull()) {
            println("还未实现父节点分裂！！！");
        } else {
            int itemIndex = parent.insertItem(itemB);
            int n = parent.getNumItems();         // total items?
            for (int j = n - 1; j > itemIndex; j--) {                                      // connections
                Tree23Node<T> temp = parent.disconnectChild(j); // one child
                parent.connectChild(j + 1, temp);        // to the right
            }
            // connect newRight to parent
            parent.connectChild(itemIndex + 1, newRight);
        }
    }

    public void insert(T... data) {
        for (T datum : data) {
           insert(datum);
        }
    }

    public void insert(T data) {
        Tree23Node<T> curNode = root;
        DataItem<T> tempItem = new DataItem<>(data);

        while (true) {
            if (curNode.isLeaf())          // if node is leaf,
                break;                            // go insert
                // node is not full, not a leaf; so go to lower level
            else
                curNode = getNextChild(curNode, data);
        }  // end while
        if (curNode.isFull()) {
            split(curNode, tempItem, null); // 可以递归分裂
        } else {
            curNode.insertItem(tempItem);       // insert new DataItem
        }
    }  // end insert()

    // thisNode 当前要分裂的节点
    // newItem 要插入的数据项
    // newNode 上次分裂(子节点分裂)得到的新节点，如果是第一次分裂(叶节点分裂)，则为null
    public void split(Tree23Node<T> thisNode, DataItem<T> newItem, Tree23Node<T> newNode) {
        // assumes node is full
        DataItem<T> itemB, itemC; // itemB保存中间数据项,itemC保存最大数据项
        Tree23Node<T> parent, child1, child2, newRight;

        // 分裂当前节点
        // 根据新数据项与当前节点中两个数据项的比较，分三种情况：
        if (thisNode.getItem(0).data.compareTo(newItem.data) > 0) { // 1.新数据项最小
            itemC = thisNode.removeItem();
            itemB = thisNode.removeItem();
            thisNode.insertItem(newItem);

            //当前节点不一定是叶节点
            child1 = thisNode.disconnectChild(1);
            child2 = thisNode.disconnectChild(2);
            thisNode.connectChild(1, newNode);

            newRight = new Tree23Node<>();       // make new node
            newRight.insertItem(itemC);       // item C to newRight

            newRight.connectChild(0, child1);
            newRight.connectChild(1, child2);
        } else if (thisNode.getItem(1).data.compareTo(newItem.data) < 0) { // 2.新数据项最大
            itemC = newItem;
            itemB = thisNode.removeItem();

            //相对于split新增
            child2 = thisNode.disconnectChild(2);

            newRight = new Tree23Node<>();       // make new node
            newRight.insertItem(itemC);       // item C to newRight

            newRight.connectChild(0, child2);
            newRight.connectChild(1, newNode);
        } else { // 3.新数据项在中间
            itemC = thisNode.removeItem();
            itemB = newItem;

            child2 = thisNode.disconnectChild(2);

            newRight = new Tree23Node<>();       // make new node
            newRight.insertItem(itemC);       // item C to newRight

            newRight.connectChild(0, newNode);
            newRight.connectChild(1, child2);
        }

        // 得到父节点
        if (thisNode != root) { // 当前节点是非根节点
            parent = thisNode.getParent();
        } else {// 当前节点是根节点
            root = new Tree23Node<>();                // make new root
            parent = root;                    // root is our parent
            root.connectChild(0, thisNode);   // connect to parent
        }

        // 中间数据项插入到父节点
        if (parent.isFull()) { // 父节点是满，则递归分裂父节点
            split(parent, itemB, newRight);
        } else { // 父节点不满，直接插入
            int itemIndex = parent.insertItem(itemB);
            // 调整相应的子节点
            if (itemIndex == 0) {// 插入到第一个
                parent.connectChild(2, parent.disconnectChild(1));
                parent.connectChild(1, newRight);
            } else { // 插入到第二个
                parent.connectChild(2, newRight);
            }
        }
    }

    // gets appropriate child of node during search for value
    private Tree23Node<T> getNextChild(Tree23Node<T> theNode, T theValue) {
        int j;
        // assumes node is not empty, not full, not a leaf
        int numItems = theNode.getNumItems();
        for (j = 0; j < numItems; j++) {
            if (theValue.compareTo(theNode.getItem(j).data) < 0)
                return theNode.getChild(j);  // return left child
        }  // end for // we're greater, so
        return theNode.getChild(j);        // return right child
    }

    public void displayTree() {
        recDisplayTree(root, 0, 0);
    }

    private void recDisplayTree(Tree23Node<T> thisNode, int level, int childNumber) {
        print("level=" + level + " child=" + childNumber + " ");
        thisNode.displayNode();               // display this node

        // call ourselves for each child of this node
        int numItems = thisNode.getNumItems();
        for (int j = 0; j < numItems + 1; j++) {
            Tree23Node<T> nextNode = thisNode.getChild(j);
            if (nextNode != null)
                recDisplayTree(nextNode, level + 1, j);
            else
                return;
        }
    }
}
