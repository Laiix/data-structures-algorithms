package com.eussi.ch13_graph.util;

/**
 * @author wangxueming
 * @create 2020-03-09 10:58
 * @description
 */
public class Link {
    public Vertex vertex;              // data item (key)
    public Link next;              // next link in list

    // -------------------------------------------------------------

    public Link(Vertex data) // constructor
    {
        this.vertex = data;
    }

    // -------------------------------------------------------------
    public void displayLink()      // display ourself
    {
        System.out.print(vertex.label);
    }
}  // end class Link