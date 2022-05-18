package com.eussi.data._08;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static com.eussi.util.Func.repeatPrint;
import static com.eussi.util.Func.repeatPrintln;
import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

public class Tree<T extends Comparable<T>> {
    private TreeNode<T> root;             // first node of tree

    public Tree() {
        root = null;
    }

    public Tree(TreeNode<T> root) {
        this.root = root;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    public TreeNode<T> find(T key) {                           // (assumes non-empty tree)
        TreeNode<T> current = root;               // start at root
        while (!current.data.equals(key)) {
            if (key.compareTo(current.data) < 0)         // go left?
                current = current.leftChild;
            else                            // or go right?
                current = current.rightChild;
            if (current == null)             // if no child,
                return null;                 // didn't find it
        }
        return current;                    // found it
    }

    public void insert(T... keys) {
        for (T key : keys) {
          insert(key);
        }
    }


    public void insert(T key) {
        TreeNode<T> newNode = new TreeNode<>();    // make new node
        newNode.data = key;          // insert data
        if (root == null)                // no node in root
            root = newNode;
        else {
            TreeNode<T> current = root;       // start at root
            TreeNode<T> parent;
            while (true) {
                parent = current;
                if (key.compareTo(current.data) < 0) {
                    current = current.leftChild;
                    if (current == null) {                 // insert on left
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {                 // insert on right
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    public boolean delete(T key) {
        TreeNode<T> current = root;
        TreeNode<T> parent = root;
        boolean isLeftChild = true;

        while(!current.data.equals(key)) {
            parent = current;
            if (key.compareTo(current.data)<0) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null)             // end of the line,
                return false;                // didn't find it
        }

        // if no children, simply delete it
        if (current.leftChild == null && current.rightChild == null) {
            if (current == root)             // if root,
                root = null;                 // tree is empty
            else if (isLeftChild)
                parent.leftChild = null;     // disconnect
            else                            // from parent
                parent.rightChild = null;
        } else if (current.rightChild == null) {
            if (current == root)
                root = current.leftChild;
            else if (isLeftChild)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.leftChild;

            // if no left child, replace with right subtree
        } else if (current.leftChild == null) {
            if (current == root)
                root = current.rightChild;
            else if (isLeftChild)
                parent.leftChild = current.rightChild;
            else
                parent.rightChild = current.rightChild;

        } else {
            // get successor of node to delete (current)
            TreeNode<T> successor = getSuccessor(current);

            // connect parent of current to successor instead
            if (current == root)
                root = successor;
            else if (isLeftChild)       //父节点的左子节点需要删除
                parent.leftChild = successor;
            else
                parent.rightChild = successor;  //父节点的右子节点需要删除

            // connect successor to current's left child
            successor.leftChild = current.leftChild;    //将后继节点的左子节点与原来删除节点的左子节点连接
        }  // end else two children
        // (successor cannot have a left child)
        return true;                                // success
    }

    private TreeNode<T> getSuccessor(TreeNode<T> delNode) {
        TreeNode<T> successorParent = delNode;
        TreeNode<T> successor = delNode;
        TreeNode<T> current = delNode.rightChild;   // go to right child
        while (current != null) {               // left children,
            successorParent = successor;
            successor = current;
            current = current.leftChild;      // go to left child
        }
        // if successor not
        if (successor != delNode.rightChild) {                                 // make connections
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }


    public void traverse(int traverseType) {
        switch (traverseType) {
            case 1:
                print("\nPreorder traversal: ");
                preOrder(root);
                break;
            case 2:
                print("\nInorder traversal:  ");
                inOrder(root);
                break;
            case 3:
                print("\nPostorder traversal: ");
                postOrder(root);
                break;
            case 4:
                print("\nExpr Inorder traversal: ");
                inOrderExpr(root);
                break;
        }
        println();
    }

    private void preOrder(TreeNode<T> localRoot) {
        if (localRoot != null) {
            print(localRoot.data.toString() + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    private void inOrder(TreeNode<T> localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            print(localRoot.data.toString() + " ");
            inOrder(localRoot.rightChild);
        }
    }

    private void inOrderExpr(TreeNode<T> localRoot) {
        if (localRoot != null) {
            print("(");
            inOrderExpr(localRoot.leftChild);
            print(localRoot.data.toString() + " ");
            inOrderExpr(localRoot.rightChild);
            print(")");
        }
    }

    private void postOrder(TreeNode<T> localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            print(localRoot.data.toString() + " ");
        }
    }

    public void displayTree() {
        int dotWidth = 64;
        repeatPrintln(dotWidth, ".");

        Stack<TreeNode<T>> outerStack = new Stack<>();
        outerStack.push(root);

        boolean isRowEmpty = false;//记录下一层是否还有数据
        int nBlanks = dotWidth/2;
        while (!isRowEmpty) {
            Stack<TreeNode<T>> innerStack = new Stack<>();
            isRowEmpty = true;
            repeatPrint(nBlanks, " ");
            //取出一层的数据，进行打印，然后下一层如果有，把下一层入栈
            while (!outerStack.isEmpty()) {
                TreeNode<T> temp = outerStack.pop();
                if (temp != null) {
                    print(temp.data);
                    innerStack.push(temp.leftChild);
                    innerStack.push(temp.rightChild);
                    if (temp.leftChild != null || temp.rightChild != null) {
                        isRowEmpty = false;    //任何一个包含子节点就会将此值设置为false
                    }
                } else {
                    print("--");
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

    public void displayTreeByQueue() {
        if (root == null) { // 如果是空Heap
            println("empty Tree!");
            return;
        }

        int dotWidth = 64;
        repeatPrintln(dotWidth, ".");

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        int itemsPerRow = 1;//当前行应该有多少数据
        int column = 0; //记录当前行处理数
        int nullNum = 0;//记录下一层null层数，判断下一层是否还有数据

        int nBlanks = dotWidth/2;

        while (!queue.isEmpty()) {
            if (column == 0) { // first item in row?
                repeatPrint(nBlanks, " ");
            }
            TreeNode<T> node = queue.remove();
            print(node==null?"--":node.data);

            //下一层入队列
            if (node!=null && node.leftChild != null) {
                queue.add(node.leftChild);
            }else {
                queue.add(null);
                nullNum++;
            }
            if (node!=null && node.rightChild != null) {
                queue.add(node.rightChild);
            } else {
                queue.add(null);
                nullNum++;
            }

            if (nullNum == itemsPerRow*2) // done? 下一行为空的数量等于当行数量元素的两倍
                break;

            if (++column == itemsPerRow)  {//换行到下一层
                nBlanks /= 2; // half the blanks

                itemsPerRow *= 2; // twice the items
                nullNum = 0;
                column = 0; // start over on

                println(); // new row
            } else {
                repeatPrint(nBlanks * 2 - 2, " ");
            }
        }

        println();
        repeatPrintln(dotWidth, ".");
    }

    @Override
    public String toString() {
        return root.data + "";
    }

}