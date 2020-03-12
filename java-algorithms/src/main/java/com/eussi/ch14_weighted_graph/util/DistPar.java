package com.eussi.ch14_weighted_graph.util;

/**
 * @author wangxueming
 * @create 2020-03-12 11:06
 * @description
 */
public class DistPar             // distance and parent
{                      // items stored in sPath array
    public int distance;   // distance from start to this vertex
    public int parentVert; // current parent of this vertex
    // -------------------------------------------------------------
    public DistPar(int pv, int d)  // constructor
    {
        distance = d;
        parentVert = pv;
    }
// -------------------------------------------------------------
}  // end class DistPar