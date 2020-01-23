package com.eussi.ch08_binary_tree.util;

import java.util.Stack;

/**
 * @author wangxueming
 * @create 2020-01-23 13:51
 * @description
 */
public class CharTree implements Comparable {
    // 改成了public
    public CharNode root;             // first node of tree
    public int weight; // 权重

    // -------------------------------------------------------------
    public CharTree()                  // constructor
    {
        root = null;
    }            // no nodes in tree yet

    // 添加了toString()方法
    public String toString() {
        return root.cchar + "";
    }

    // -------------------------------------------------------------
    public void traverse(int traverseType) {
        switch (traverseType) {
            case 1:
                System.out.print("\nPreorder traversal: ");
                preOrder(root);
                break;
            case 2:
                System.out.print("\nInorder traversal:  ");
                inOrder(root);
                break;
            case 3:
                System.out.print("\nPostorder traversal: ");
                postOrder(root);
                break;
        }
        System.out.println();
    }

    // -------------------------------------------------------------
    private void preOrder(CharNode localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.cchar + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    // -------------------------------------------------------------
    private void inOrder(CharNode localRoot) {
        if (localRoot != null) {
            System.out.print("(");
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.cchar + " ");
            inOrder(localRoot.rightChild);
            System.out.print(")");
        }
    }

    // -------------------------------------------------------------
    private void postOrder(CharNode localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.cchar + " ");
        }
    }

    // -------------------------------------------------------------
    public void displayTree() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out
                .println("......................................................");
        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++)
                System.out.print(' ');

            while (globalStack.isEmpty() == false) {
                CharNode temp = (CharNode) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.cchar);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if (temp.leftChild != null || temp.rightChild != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++)
                    System.out.print(' ');
            }  // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop());
        }  // end while isRowEmpty is false
        System.out
                .println("......................................................");
    }  // end displayTree()
    // -------------------------------------------------------------

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            return -1;
        }
        return weight - ((CharTree) o).weight;
    }

}
