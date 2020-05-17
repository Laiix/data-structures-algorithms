package com.eussi.algorithm_thinking;

import java.util.Arrays;

/**
 * @author wangxueming
 * @create 2020-05-17 6:47
 * @description
 */
public class DoublePointer {
    public static void main(String[] args) {
        //判定列表中是否包含环
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        node0.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node7);

        //判断链表是否有环
        System.out.println(hasCycle(node0));
        //找链表中点
        System.out.println(findMid(node0).value);
        //找倒数第k个数据
        System.out.println(findK(node0, 2).value);


        //设置环
        node7.setNext(node2);
        System.out.println(hasCycle(node0));
        //返回环的起始位置
        System.out.println(detectCycle(node0).value);



        //有序数组，两数之和，返回索引，索引从1开始
        System.out.println(Arrays.toString(twoSum(new int[]{1, 3, 3, 6}, 6)));
        //反转数组
        int[] nums = new int[]{1, 5, 7, 9, 0, 12};
        reverse(nums);
        System.out.println(Arrays.toString(nums));

    }

    static void reverse(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // swap(nums[left], nums[right])
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++; right--;
        }
    }

    static int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                //索引从1开始
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++; // 让 sum ⼤⼀点
            } else if (sum > target) {
                right--; // 让 sum ⼩⼀点
            }
        }
        return new int[]{-1, -1};
    }

    static Node findK(Node head, int k) {
        Node slow, fast;
        slow = fast = head;
        while (k-- > 0)
            fast = fast.next;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    static Node findMid(Node head) {
        Node fast, slow;
        fast = slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // slow 就在中间位置
        return slow;
    }

    static Node detectCycle(Node head) {
        Node fast, slow;
        fast = slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        // 上面的代码类似 hasCycle函数
        slow = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    static boolean hasCycle(Node head) {
        Node fast, slow;
        fast = slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }





    static class Node {
        private Node next;
        private int value;

        public Node(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
