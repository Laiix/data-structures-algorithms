package com.eussi.data_structure;

public class ReverseKList {
    public static void main(String[] args) {
        LinkNode root = new LinkNode(1,
                new LinkNode(2,
                        new LinkNode(3,
                                new LinkNode(4,
                                        new LinkNode(5, null)))));


        traverse(root);
        System.out.println();

        printLinkList(root);


        //反正整个链表
        root = reverse(root);
        printLinkList(root);

        //反正root到node2
        root = reverseKGroup(root, 2);
        printLinkList(root);

        root = reverseKGroup(root, 3);
        printLinkList(root);

        root = reverse(root, root.next.next.next);
        printLinkList(root);



    }

    private static void traverse(LinkNode head) {
        if(head==null) {
            return;
        }

        // 前序遍历代码
        traverse(head.next);
        // 后序遍历代码
        System.out.print(head.value + " ");

    }

    private static LinkNode reverseKGroup(LinkNode head, int k) {
        if(head == null) return null;
        //区间[a, b) 包含k个待反转元素
        LinkNode a, b;
        a = b = head;
        for(int i=0; i<k; i++) {
            //不足k个，不需要反转， base case
            if(b==null) return head;
            b = b.next;
        }

        //反转前k个元素
        LinkNode newHead = reverse(a, b);
        //递归反转后续链表并连接起来
        a.next = reverseKGroup(b, k);

        return newHead;

    }

    private static LinkNode reverse(LinkNode a, LinkNode b) {
        LinkNode pre, cur, nxt;
        pre = null; cur = a; nxt = a;
        while(cur!=b) {
            nxt = cur.next;
            //逐个节点反转
            cur.next = pre;
            //更新指针位置
            pre = cur;
            cur = nxt;
        }

        return pre;

    }

    private static LinkNode reverse(LinkNode root) {
        LinkNode pre, cur, nxt;
        pre = null; cur = root; nxt = root;
        while(cur!=null) {
            nxt = cur.next;
            //逐个节点反转
            cur.next = pre;
            //更新指针位置
            pre = cur;
            cur = nxt;
        }

        return pre;

    }



    private static void printLinkList(LinkNode root) {
        LinkNode curr = root;
        while(curr != null) {
            System.out.print(curr.value + " ");
            curr = curr.next;
        }

        System.out.println();
    }
}

class LinkNode {
    LinkNode next;
    int value;

    public LinkNode(int value, LinkNode next) {
        this.next = next;
        this.value = value;
    }

    public LinkNode getNext() {
        return next;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
