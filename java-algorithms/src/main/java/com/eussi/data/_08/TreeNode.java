package com.eussi.data._08;

import static com.eussi.util.PrintUtil.print;

/**
 * @author wangxueming
 * @create 2020-01-21 16:18
 * @description
 */
public class TreeNode<T> {
    public T data;
    public TreeNode<T> leftChild;         // this node's left child
    public TreeNode<T> rightChild;        // this node's right child

    public TreeNode() {
    }

    public TreeNode(T data) {
        this.data = data;
    }

    public void displayNode() {
        print(data.toString());
    }

    @Override
    public String toString() {
        return data.toString();
    }
}