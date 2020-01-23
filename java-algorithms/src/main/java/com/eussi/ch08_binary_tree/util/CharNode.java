package com.eussi.ch08_binary_tree.util;

/**
 * @author wangxueming
 * @create 2020-01-23 13:50
 * @description
 */
public class CharNode {
    public char cchar;
    public CharNode leftChild;         // this node's left child
    public CharNode rightChild;        // this node's right child

    public CharNode() {
    }

    public CharNode(char c) {
        cchar = c;
    }

    public void displayNode()      // display ourself
    {
        System.out.print('{');
        System.out.print(cchar);
        System.out.print("} ");
    }
}
