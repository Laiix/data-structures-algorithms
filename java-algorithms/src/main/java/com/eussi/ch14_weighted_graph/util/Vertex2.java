package com.eussi.ch14_weighted_graph.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxueming
 * @create 2020-03-12 22:58
 * @description
 */
public class Vertex2 {
    public char label;        // label (e.g. 'A')
    public boolean isInTree;
    public List<Edge2> edges; // list of vertices

    // -------------------------------------------------------------
    public Vertex2(char lab)   // constructor
    {
        label = lab;
        isInTree = false;
        edges = new ArrayList<Edge2>();
    }

    // 添加边
    public void addEdge(Edge2 edge) {
        this.edges.add(edge);
    }
    // -------------------------------------------------------------
}