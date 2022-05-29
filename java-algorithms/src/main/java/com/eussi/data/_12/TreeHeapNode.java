package com.eussi.data._12;

public class TreeHeapNode {
    public int key; // data item (key)
    public TreeHeapNode parent;
    public TreeHeapNode leftChild;
    public TreeHeapNode rightChild;

    public TreeHeapNode() {

    }

    public TreeHeapNode(int key) {
        super();
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public TreeHeapNode getParent() {
        return parent;
    }

    public void setParent(TreeHeapNode parent) {
        this.parent = parent;
    }

    public TreeHeapNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeHeapNode leftChild) {
        this.leftChild = leftChild;
    }

    public TreeHeapNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeHeapNode rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return key + " ";
    }
}