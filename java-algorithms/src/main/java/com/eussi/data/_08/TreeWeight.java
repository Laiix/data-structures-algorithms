package com.eussi.data._08;

import java.util.Stack;

import static com.eussi.util.Func.repeatPrint;
import static com.eussi.util.Func.repeatPrintln;
import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * @author wangxueming
 * @create 2020-01-23 13:51
 * @description
 */
public class TreeWeight<T> implements Comparable<TreeWeight<T>> {
    public TreeNode<T> root;             // first node of tree
    public int weight; // 权重

    public TreeWeight() {
        root = null;
    }

    public TreeWeight(TreeNode<T> root) {
        this.root = root;
    }

    // 添加了toString()方法
    public String toString() {
        return root.data + "";
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
        }
        println();
    }

    private void preOrder(TreeNode<T> localRoot) {
        if (localRoot != null) {
            print(localRoot.data + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    private void inOrder(TreeNode<T> localRoot) {
        if (localRoot != null) {
            print("(");
            inOrder(localRoot.leftChild);
            print(localRoot.data + " ");
            inOrder(localRoot.rightChild);
            print(")");
        }
    }

    private void postOrder(TreeNode<T> localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            print(localRoot.data + " ");
        }
    }

    public void displayTree() {
        int dotWidth = 128;
        repeatPrintln(dotWidth, ".");

        Stack<TreeNode<T>> outerStack = new Stack<>();
        outerStack.push(root);

        boolean isRowEmpty = false;//记录下一层是否还有数据
        int nBlanks = dotWidth / 2;
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


    @Override
    public int compareTo(TreeWeight o) {
        if (o == null) {
            return -1;
        }
        return weight - o.weight;
    }
}
