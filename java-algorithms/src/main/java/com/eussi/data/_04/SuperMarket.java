package com.eussi.data._04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * @author wangxueming
 * @create 2019-10-28 23:57
 * @description
 */
public class SuperMarket {
    private Queue[] queue = { null, new Queue(20), new Queue(20), new Queue(20), new Queue(20) }; // 4个顾客队列

    // 模拟收银
    public void simulate() throws IOException {
        long id = 0; // 顾客编号
        boolean flag = true;
        while (flag) {
            println("请选择事件：");
            print("0.有顾客进入某个队列。");
            print("1.有顾客离开第1个队例。");
            print("2.有顾客离开第2个队例。");
            print("3.有顾客离开第3个队例。");
            print("4.有顾客离开第4个队例。");
            println("q.表示程序退出！");
            String s = getString();
            if (s.length() == 0) {// 直接输入回车
                continue;
            }
            char ch = s.charAt(0);
            switch (ch) {
                case '0':
                    id++;
                    insertQueue(id); // 顾客进入队列
                    displayQueue(); // 显示队列
                    break;
                case '1':
                    removeQueue(1); // 顾客离开队列
                    displayQueue(); // 显示队列
                    break;
                case '2':
                    removeQueue(2);
                    displayQueue();
                    break;
                case '3':
                    removeQueue(3);
                    displayQueue();
                    break;
                case '4':
                    removeQueue(4);
                    displayQueue();
                    break;
                case 'q': // 退出程序
                    flag = false;
                    println("byebye!");
                    break;
                default:
                    break;
            }
        }
    }

    // 从队列中删除顾客
    private void removeQueue(int queueId) {
        if (queue[queueId].size() == 0) {
            return;
        }
        long id = ((Queue<Long>)queue[queueId]).remove();
        println("顾客" + id + "离开第" + queueId + "个队列！");
    }

    // 把顾客插入到队列
    public void insertQueue(long id) {
        int queueId = getMinQueueId();
        queue[queueId].insert(id);
        println("顾客" + id + "进入第" + queueId + "个队例");
    }

    // 得到最小队列的编号
    private int getMinQueueId() {
        int min = 1;
        for (int i = 2; i < 5; i++) {
            if (queue[i].size() < queue[min].size()) {
                min = i;
            }
        }
        return min;
    }

    // 打印显示四条队列
    public void displayQueue() {
        for (int i = 1; i < 5; i++) {
            System.out.print("第" + i + "个");
            queue[i].display();
        }
        println();
    }

    public String getString() throws IOException {// 接受键盘输入字符串
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(in);
        String s = bf.readLine();
        return s;
    }

}
