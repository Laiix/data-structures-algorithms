package com.eussi.ch12_heap.util;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wangxueming
 * @create 2020-03-04 17:01
 * @description
 */
public class TreeHeap {
    class Node {
        private int key; // data item (key)
        private Node Parent;
        private Node LeftChild;
        private Node RightChild;

        public Node() {

        }

        public Node(int key) {
            super();
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public Node getParent() {
            return Parent;
        }

        public void setParent(Node parent) {
            Parent = parent;
        }

        public Node getLeftChild() {
            return LeftChild;
        }

        public void setLeftChild(Node leftChild) {
            LeftChild = leftChild;
        }

        public Node getRightChild() {
            return RightChild;
        }

        public void setRightChild(Node rightChild) {
            RightChild = rightChild;
        }

        @Override
        public String toString() {
            return key + " ";
        }
    }

    private Node root;
    private int currentSize; // number of nodes in array

    public TreeHeap() {
        currentSize = 0;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean insert(int key) {
        Node nullNode = findNullNode();// 找到空节点
        nullNode.key = key; // 把值设到空节点
        trickleUp(nullNode); // 空节点上移到合适的位置
        currentSize++;
        System.out.println("insert complete! heap size is :" + currentSize);
        return true;
    }

    public Node remove() {
        if (currentSize == 0) { // 如果是空Heap
            System.out.println("remove flase! heap is empty!");
            return null;
        }
        int key = root.key;// 保存原来的key
        if (currentSize == 1) { // 如果只有根节点
            currentSize--;
            root = null;
            System.out.println("remove complete! heap size is :" + currentSize);
        } else {
            // 如果是非空Heap
            Node lastNode = findLastNode();
            root.key = lastNode.key;
            trickleDown(root);
            currentSize--;
            System.out.println("remove complete! heap size is :" + currentSize);
        }
        return new Node(key);// 返回原来的Node
    }

    private void trickleDown(Node currentNode) {
        int key = currentNode.key;
        Node parent = currentNode;
        Node bigChild;
        while (parent.LeftChild != null) {
            bigChild = parent.LeftChild;
            if (parent.RightChild != null
                    && parent.RightChild.key > parent.LeftChild.key) {
                bigChild = parent.RightChild;
            }
            if (bigChild.key <= key) {
                break;
            }
            parent.key = bigChild.key;
            parent = bigChild;
        }
        parent.key = key;
    }

    private void trickleUp(Node currentNode) {
        Node parent = currentNode.Parent;
        int key = currentNode.key;
        while (currentNode != root) {
            if (parent.key >= key) {
                break;
            } else {
                currentNode.key = parent.key;
                currentNode = parent;
                parent = parent.Parent;
            }
        }
        currentNode.key = key;
    }

    // 新建一个空节点，并把空节点插入到树的最后面
    private Node findNullNode() {
        Node nullNode = new Node(); // 创建nullNode空节点
        // 如果是非空Heap
        int[] path = getPath(currentSize + 1);// 第一个空节点的编号为currentSize+1 //
        // 计算第一个空节点的路径
        Node currentNode = root;
        for (int i = 0; i < path.length; i++) {// 按路径找到第一个空节点的正确位置，把nullNode节点链接上去
            if (path[i] == 0) {
                if (currentNode.LeftChild != null) {
                    currentNode = currentNode.LeftChild;
                } else {
                    currentNode.LeftChild = nullNode;
                    nullNode.Parent = currentNode;
                    return nullNode;
                }
            } else {
                if (currentNode.RightChild != null) {
                    currentNode = currentNode.RightChild;
                } else {
                    currentNode.RightChild = nullNode;
                    nullNode.Parent = currentNode;
                    return nullNode;
                }
            }
        }
        // 如果是空Heap
        root = nullNode; // 把新空节点赋值给root
        return nullNode;
    }

    // 找到最后一个节点，并断开与heap的连接
    private Node findLastNode() {
        Node parentNode = root;
        Node lastNode = root;

        int[] path = getPath(currentSize);// 最后一个节点的编号为currentSize //
        // 计算最后一个节点的路径
        for (int i = 0; i < path.length; i++) {
            if (path[i] == 0) { // 向左
                parentNode = lastNode;
                lastNode = lastNode.LeftChild;
                if (i == path.length - 1) { // 找到最后一个节点，断开节点和Heap的连接
                    parentNode.LeftChild = null;
                    lastNode.Parent = null;
                    return lastNode;
                }
            } else { // 向右
                parentNode = lastNode;
                lastNode = lastNode.RightChild;
                if (i == path.length - 1) {// 找到最后一个节点，断开节点和Heap的连接
                    parentNode.RightChild = null;
                    lastNode.Parent = null;
                    return lastNode;
                }
            }
        }
        // 如果只有一个根节点
        root = null;// 断开根节点
        return lastNode;
    }

    // 计算完全二叉树给定节点的路径,路径保有存在数组path中
    // 遍历数组path即得到即点的路径
    private int[] getPath(int NodeNumber) {
        if (NodeNumber == 0) {
            return null;
        }
        // ============================================
        // 完求二叉树求给定节点路径
        // 根据节点编号NodeNumber
        // 求得编号NodeNumber的二进制数
        // 去掉二进制的第一位,即得节点的路径
        int level = (int) (Math.log(NodeNumber) / Math.log(2)) + 1;// 节点所在的层=以2为底NodeNumber的对数
        int[] binary = new int[level];// 用来存放二进制数
        while (NodeNumber >= 1) {
            binary[--level] = NodeNumber % 2;
            NodeNumber = NodeNumber / 2;
        }
        int[] path = new int[binary.length - 1];
        System.arraycopy(binary, 1, path, 0, path.length);
        return path;
    }

    public void displayHeap() {

        if (root == null) { // 如果是空Heap
            System.out.println("heapArray: empty heap!");
            return;
        }
        // 如果非空Heap
        System.out.print("heapArray: "); // array format
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print(node.key + " ");
            if (node.LeftChild != null) {
                queue.add(node.LeftChild);
            }
            if (node.RightChild != null) {
                queue.add(node.RightChild);
            }
        }
        System.out.println();
        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0; // current item
        String dots = "...............................";
        System.out.println(dots + dots); // dotted top line
        queue.add(root);
        while (!queue.isEmpty()) {
            if (column == 0) // first item in row?
                for (int k = 0; k < nBlanks; k++)
                    System.out.print(' ');// preceding blanks
            Node node = queue.remove();
            System.out.print(node.key);
            if (node.LeftChild != null) {
                queue.add(node.LeftChild);
            }
            if (node.RightChild != null) {
                queue.add(node.RightChild);
            }
            if (queue.isEmpty()) // done?
                break;
            if (++column == itemsPerRow) // end of row?
            {
                nBlanks /= 2; // half the blanks
                itemsPerRow *= 2; // twice the items
                column = 0; // start over on
                System.out.println(); // new row
            } else
                // next item on row
                for (int k = 0; k < nBlanks * 2 - 2; k++)
                    System.out.print(' '); // interim blanks
        } // end for
        System.out.println("\n" + dots + dots); // dotted bottom line
    }

}
