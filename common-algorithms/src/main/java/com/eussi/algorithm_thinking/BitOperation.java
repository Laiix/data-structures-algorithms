package com.eussi.algorithm_thinking;

public class BitOperation {
    public static void main(String[] args) {

        System.out.println("[_] " + ((int)'_') + " " + Integer.toBinaryString('_'));
        System.out.println("[ ] " + ((int)' ') + "  " + Integer.toBinaryString(' '));
        System.out.println("[A] " + ((int)'A') + " " + Integer.toBinaryString('A'));
        System.out.println("[a] " + ((int)'a') + " " + Integer.toBinaryString('a'));
        System.out.println("--------------------------");
        System.out.println('a' | ' ');
        System.out.println('A' | ' ');

        System.out.println("--------------------------");
        System.out.println('a' & '_');
        System.out.println('A' & '_');

        System.out.println("--------------------------");
        System.out.println('a' ^ ' ');
        System.out.println('A' ^ ' ');

        System.out.println("--------------------------");
        int x = -1, y = 2;
        boolean f = ((x ^ y) < 0); // true
        System.out.println(f);
        x = 3;
        y = 2;
        f = ((x ^ y) < 0); // false
        System.out.println(f);

        System.out.println("--------------------------");
        //交换两个数
        int a = 1, b = 2;
        a ^= b;
        b ^= a;
        a ^= b;
        // 现在 a = 2, b = 1
        System.out.println(a + "  " + b);
        System.out.println("--------------------------");
        //加⼀
        int n = 1;
        n = -~n;
        // 现在 n = 2
        System.out.println(n);
        System.out.println("--------------------------");
        //减⼀
        n = 2;
        n = ~-n;
        // 现在 n = 1
        System.out.println(n);
    }
}
