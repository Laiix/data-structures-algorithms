package com.eussi.ch05_linklist.util;

/**
 * @author wangxueming
 * @create 2019-11-02 15:11
 * @description
 */
public class CircleList {
    private Link2 current;
    private int nItems;

    public CircleList() {
        current = null;
    }

    public void insert(long value) {
        Link2 link = new Link2(value);
        if (current == null) {
            current = link;
            link.next = link;
        } else {
            link.next = current.next;
            current.next = link;
            current = link;// 插入元素，current要移动要新元素
        }
        nItems++;
    }

    public long remove() {
        // list为空是没有考虑，在调用remove之前应该判断是否为空
        long temp = current.next.dData;// 删掉current的下一个元素
        if (current.next == current) { // 只有一个元素时
            current = null;
        } else {
            current.next = current.next.next;
        }
        nItems--;
        return temp;
    }

    public long peek() { // 返回最早插入的元素,因为是一个环，所以当前元素的下一个
        // 调用前要判断是否为空
        return current.next.dData;
    }

    public Link2 find(long value) {
        Link2 temp = current; // 保存原来的位置
        Link2 result = null;
        if (current == null) {
            return result;
        }
        do {
            step(); // 从current的下一个元素开始比较
            if (current.dData == value) {
                result = current;
                current = temp; // 还原current到原来的位置,这样就不会打乱插入的顺序，current指向最后插入的元素
                return result;
            }
        } while (current != temp); // current到达原来的位置,一周循环结束
        return result;
    }

    // 往下移一步
    public void step() { // 调用step()方法后，顺序会被打乱
        if (current != null) {
            current = current.next;
        }
    }

    public void display() {
        if (current != null) {
            Link2 temp = current;
            do {
                step(); // 从current的一下个开始显示
                System.out.print(current.dData + " ");
            } while (current != temp);
        }
        System.out.println();
    }

    public boolean isEmpty() {
        return current == null;
    }

    public int size() {
        return nItems;
    }
}
