package com.eussi.data._12;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static com.eussi.util.Func.repeatPrint;
import static com.eussi.util.Func.repeatPrintln;
import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * @author wangxueming
 * @create 2020-03-04 17:01
 * @description
 */
public class TreeHeap {
    private TreeHeapNode root;
    private int currentSize; // number of nodes in array

    public TreeHeap() {
        currentSize = 0;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean insert(int key) {
        TreeHeapNode nullNode = findNullNode();// 找到空节点
        nullNode.key = key; // 把值设到空节点
        trickleUp(nullNode); // 空节点上移到合适的位置
        currentSize++;
        println("insert complete! heap size is :" + currentSize);
        return true;
    }

    public TreeHeapNode remove() {
        if (currentSize == 0) { // 如果是空Heap
            println("remove flase! heap is empty!");
            return null;
        }
        int key = root.key;// 保存原来的key
        if (currentSize == 1) { // 如果只有根节点
            currentSize--;
            root = null;
            println("remove complete! heap size is :" + currentSize);
        } else {
            // 如果是非空Heap
            TreeHeapNode lastNode = findLastNode();
            root.key = lastNode.key;
            trickleDown(root);
            currentSize--;
            println("remove complete! heap size is :" + currentSize);
        }
        return new TreeHeapNode(key);// 返回原来的Node
    }

    private void trickleDown(TreeHeapNode currentNode) {
        int key = currentNode.key;
        TreeHeapNode parent = currentNode;
        TreeHeapNode bigChild;
        while (parent.leftChild != null) {
            bigChild = parent.leftChild;
            if (parent.rightChild != null
                    && parent.rightChild.key > parent.leftChild.key) {
                bigChild = parent.rightChild;
            }
            if (bigChild.key <= key) {
                break;
            }
            parent.key = bigChild.key;
            parent = bigChild;
        }
        parent.key = key;
    }

    private void trickleUp(TreeHeapNode currentNode) {
        TreeHeapNode parent = currentNode.parent;
        int key = currentNode.key;
        while (currentNode != root) {
            if (parent.key >= key) {
                break;
            } else {
                currentNode.key = parent.key;
                currentNode = parent;
                parent = parent.parent;
            }
        }
        currentNode.key = key;
    }

    // 新建一个空节点，并把空节点插入到树的最后面
    private TreeHeapNode findNullNode() {
        TreeHeapNode nullNode = new TreeHeapNode(); // 创建nullNode空节点
        // 如果是非空Heap
        int[] path = getPath(currentSize + 1);// 第一个空节点的编号为currentSize+1 //
        // 计算第一个空节点的路径
        TreeHeapNode currentNode = root;
        for (int i = 0; i < path.length; i++) {// 按路径找到第一个空节点的正确位置，把nullNode节点链接上去
            if (path[i] == 0) {
                if (currentNode.leftChild != null) {
                    currentNode = currentNode.leftChild;
                } else {
                    currentNode.leftChild = nullNode;
                    nullNode.parent = currentNode;
                    return nullNode;
                }
            } else {
                if (currentNode.rightChild != null) {
                    currentNode = currentNode.rightChild;
                } else {
                    currentNode.rightChild = nullNode;
                    nullNode.parent = currentNode;
                    return nullNode;
                }
            }
        }
        // 如果是空Heap
        root = nullNode; // 把新空节点赋值给root
        return nullNode;
    }

    // 找到最后一个节点，并断开与heap的连接
    private TreeHeapNode findLastNode() {
        TreeHeapNode parentNode = root;
        TreeHeapNode lastNode = root;

        int[] path = getPath(currentSize);// 最后一个节点的编号为currentSize //
        // 计算最后一个节点的路径
        for (int i = 0; i < path.length; i++) {
            if (path[i] == 0) { // 向左
                parentNode = lastNode;
                lastNode = lastNode.leftChild;
                if (i == path.length - 1) { // 找到最后一个节点，断开节点和Heap的连接
                    parentNode.leftChild = null;
                    lastNode.parent = null;
                    return lastNode;
                }
            } else { // 向右
                parentNode = lastNode;
                lastNode = lastNode.rightChild;
                if (i == path.length - 1) {// 找到最后一个节点，断开节点和Heap的连接
                    parentNode.rightChild = null;
                    lastNode.parent = null;
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
            println("heapArray: empty heap!");
            return;
        }

        // 如果非空Heap
        print("heapArray: "); // array format
        Queue<TreeHeapNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeHeapNode node = queue.remove();
            print(node.key + " ");
            if (node.leftChild != null) {
                queue.add(node.leftChild);
            }
            if (node.rightChild != null) {
                queue.add(node.rightChild);
            }
        }
        println();

        println("heapTree: "); // array format
        int dotWidth = 64;
        repeatPrintln(dotWidth, ".");

        Stack<TreeHeapNode> outerStack = new Stack<>();
        outerStack.push(root);

        boolean isRowEmpty = false;//记录下一层是否还有数据
        int nBlanks = dotWidth/2;
        while (!isRowEmpty) {
            Stack<TreeHeapNode> innerStack = new Stack<>();
            isRowEmpty = true;
            repeatPrint(nBlanks, " ");
            //取出一层的数据，进行打印，然后下一层如果有，把下一层入栈
            while (!outerStack.isEmpty()) {
                TreeHeapNode temp = outerStack.pop();
                if (temp != null) {
                    print(temp.key);
                    innerStack.push(temp.leftChild);
                    innerStack.push(temp.rightChild);
                    if (temp.leftChild != null || temp.rightChild != null) {
                        isRowEmpty = false;    //任何一个包含子节点就会将此值设置为false
                    }
                } else {
//                    print("--");
                    innerStack.push(null);//特殊数据，用来打印空值
                    innerStack.push(null);
                }
                repeatPrint(nBlanks * 2 - 2, " ");
            }
            println();
            nBlanks /= 2;
            while (!innerStack.isEmpty()) {
                outerStack.push(innerStack.pop());
            }
        }
        repeatPrintln(dotWidth, ".");
    }

}
