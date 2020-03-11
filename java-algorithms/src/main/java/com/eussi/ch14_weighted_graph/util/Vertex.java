package com.eussi.ch14_weighted_graph.util;

/**
 * @author wangxueming
 * @create 2020-03-11 11:48
 * @description
 */
public class Vertex
{
    public char label;        // label (e.g. 'A')
    public boolean isInTree;
    // -------------------------------------------------------------
    public Vertex(char lab)   // constructor
    {
        label = lab;
        isInTree = false;
    }
// -------------------------------------------------------------
}  // end class Vertex