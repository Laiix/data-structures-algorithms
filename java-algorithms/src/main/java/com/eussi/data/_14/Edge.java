package com.eussi.data._14;

/**
 * @author wangxueming
 * @create 2020-03-11 11:47
 * @description
 */
public class Edge {
    public int srcVert;   // index of a vertex starting edge
    public int destVert;  // index of a vertex ending edge
    public int distance;  // distance from src to dest

    public Edge(int sv, int dv, int d) {
        srcVert = sv;
        destVert = dv;
        distance = d;
    }
}