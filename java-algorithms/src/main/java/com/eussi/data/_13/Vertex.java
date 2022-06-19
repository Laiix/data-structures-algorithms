package com.eussi.data._13;

/**
 * @author wangxueming
 * @create 2020-03-04 23:36
 * @description
 */
public class Vertex {
    public char label;        // label (e.g. 'A')
    public boolean wasVisited;

    public Vertex(char lab) {
        label = lab;
        wasVisited = false;
    }
}