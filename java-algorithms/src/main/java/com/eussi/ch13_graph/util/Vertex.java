package com.eussi.ch13_graph.util;

/**
 * @author wangxueming
 * @create 2020-03-04 23:36
 * @description
 */
public class Vertex {
    public char label;        // label (e.g. 'A')
    public boolean wasVisited;

    // ------------------------------------------------------------
    public Vertex(char lab)   // constructor
    {
        label = lab;
        wasVisited = false;
    }
// ------------------------------------------------------------
}  // end class Vertex