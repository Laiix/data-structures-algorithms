package com.eussi.util;

/**
 * @author wangxueming
 * @create 2019-11-02 14:55
 * @description
 */
public class PrintUtil {
    public static void sep() {
        System.out.println();
        System.out.println("---");
        System.out.println();
    }

    public static <T> void print(T t) {
        System.out.print(t);
    }

    public static <T> void println(T t) {
        System.out.println(t);
    }

    public static void printExercise(String num) {
        println("Exercise " + num + ": ");
    }

    public static void sepExercise(String num) {
        sep();
        printExercise(num);
    }
}
