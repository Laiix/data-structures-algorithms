package com.eussi.high_frequency_test;

import java.util.LinkedList;
import java.util.Queue;

public class PrintBinaryTree {
    public static void main(String[] args) {
        /**
         *          10
         *       7       15
         *    1       11     17
         */

        Node n1 = new Node(null, null, 1);
        Node n2 = new Node(null, null, 11);
        Node n3 = new Node(null, null, 17);
        Node n4 = new Node(n1, null, 7);
        Node n5 = new Node(n2, n3, 15);
        Node root = new Node(n4, n5, 10);

        printBinaryTree(root);

        printBinaryTree2(root);

    }

    private static void printBinaryTree(Node root) {
        Queue<Node> globalQ = new LinkedList<Node>();
        globalQ.offer(root);
        boolean end = false;
        while(!end) {
            Queue<Node> localQ = new LinkedList<Node>();
            while(!globalQ.isEmpty()) {
                Node node = globalQ.poll();
                System.out.print(node.getValue() + " ");
                if(node.getLeft()!=null) {
                    localQ.offer(node.getLeft());
                }
                if(node.getRight()!=null) {
                    localQ.offer(node.getRight());
                }
            }
            System.out.println();


            if(localQ.isEmpty()) {
                end = true;
            } else {
                while(!localQ.isEmpty()) {
                    globalQ.offer(localQ.poll());
                }
            }

        }
    }

    private static void printBinaryTree2(Node root) {
        Queue<Node> globalQ = new LinkedList<Node>();
        globalQ.offer(root);
        int nblank = 32;
        boolean end = false;
        while(!end) {
            Queue<Node> localQ = new LinkedList<Node>();
            int nextRowNum = 0;

            for(int i=0; i<nblank; i++)
                System.out.print(" ");

            while(!globalQ.isEmpty()) {
                Node node = globalQ.poll();

                if(node.getValue()!=-1)
                    System.out.print(node.getValue() + " ");
                else
                    System.out.print("~");

                for(int i=0; i<nblank*2-2; i++)
                    System.out.print(" ");

                if(node.getLeft()!=null) {
                    localQ.offer(node.getLeft());
                    nextRowNum++;
                } else {
                    localQ.offer(new Node(null, null, -1));
                }

                if(node.getRight()!=null) {
                    localQ.offer(node.getRight());
                    nextRowNum++;
                } else {
                    localQ.offer(new Node(null, null, -1));
                }

            }
            nblank = nblank/2;
            System.out.println();


            if(nextRowNum==0) {
                end = true;
            } else {
                while(!localQ.isEmpty()) {
                    globalQ.offer(localQ.poll());
                }
            }

        }
    }

}

class Node {
    private Node left;
    private Node right;
    private int value;

    public Node(Node left, Node right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
