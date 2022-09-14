package com.eussi.data._14;

/**
 * @author wangxueming
 * @create 2020-03-12 11:06
 * @description
 */
public class DistPar {                      // items stored in sPath array
    public int distance;   // distance from start to this vertex
    public int parentVert; // current parent of this vertex

    public DistPar(int pv, int d) {
        distance = d;
        parentVert = pv;
    }
}