package com.eussi.chapter03.util;

/**
 * @author wangxueming
 * @create 2019-10-22 15:31
 * @description
 */
public class ArrayOddEven {
    private long[] a;
    private int nElems;
    public ArrayOddEven(int max) {
        a = new long[max];
        nElems = 0;
    }
    public void insert(long value) {
        a[nElems] = value;
        nElems++;
    }
    public void display() {
        for(int j=0; j<nElems; j++) {
            System.out.print(a[j] + " ");
        }
        System.out.println("");
    }

    public void oddEvenSort() {
        int num = 0;
        while(!isOrder()){
            for(int j=1; j<nElems-1; j+=2) {
                if(a[j]>a[j+1]) {
                    swap(j, j+1);
                }
            }
            for(int j=0; j<nElems-1; j+=2) {
                if(a[j]>a[j+1]) {
                    swap(j, j+1);
                }
            }
            num++;
        }
        System.out.println("Sort times: " + num);
    }
    private boolean isOrder() {
        boolean b = true;
        for(int j=0; j<nElems-1; j++) {
            if(a[j+1]<a[j]) {
                b = false;
            }
        }
        return b;
    }
    private void swap(int in, int i) {
        long temp;
        temp = a[in];
        a[in] = a[i];
        a[i] = temp;
    }
}