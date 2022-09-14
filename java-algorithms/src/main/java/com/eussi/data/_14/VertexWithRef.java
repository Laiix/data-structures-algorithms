package com.eussi.data._14;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxueming
 * @create 2020-03-12 22:58
 * @description
 */
public class VertexWithRef {
    public char label;        // label (e.g. 'A')
    public boolean isInTree;
    public List<EdgeWithRef> edges; // list of vertices

    public VertexWithRef(char lab) {
        label = lab;
        isInTree = false;
        edges = new ArrayList<>();
    }

    // 添加边
    public void addEdge(EdgeWithRef edge) {
        this.edges.add(edge);
    }
}