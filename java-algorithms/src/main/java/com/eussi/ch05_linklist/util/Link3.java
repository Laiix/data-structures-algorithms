package com.eussi.ch05_linklist.util;

/**
 * @author wangxueming
 * @create 2019-11-03 22:38
 * @description
 */
public class Link3 {
    public int data;
    public Link3 goRight;
    public Link3 goDown;

    public Link3(int d) {
        data = d;
        goRight = null;
        goDown = null;
    }

    public Link3() {
        data = 0;
        goRight = null;
        goDown = null;
    }
}
