package com.eussi.data._09;

import static com.eussi.util.PrintUtil.print;

public class RBTreeNode<T> {
    private T data;
    private boolean isRed = true; //颜色
    private RBTreeNode<T> leftChild;         // this node's left child
    private RBTreeNode<T> rightChild;        // this node's right child

    public RBTreeNode() {
    }

    public RBTreeNode(T data) {
        this.data = data;
    }

    public boolean isRed() {
        return isRed;
    }

    public void setRed(boolean red) {
        isRed = red;
    }

    public void changeColor() {
        isRed = !isRed;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RBTreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(RBTreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public RBTreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(RBTreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public void displayNode() {
        print(data.toString());
    }

    @Override
    public String toString() {
        return data.toString()+"(" + (isRed?"R":"B") + ")";
    }
}