package com.eussi.ch13_graph.util;

/**
 * @author wangxueming
 * @create 2020-03-09 10:58
 * @description
 */
public class LinkList {
    public Link first;            // ref to first link on list
    public Link last;

    // -------------------------------------------------------------
    public LinkList(Vertex c)              // constructor
    {
        Link newLink = new Link(c);
        last = first = newLink;               // no links on list yet
    }

    // -------------------------------------------------------------

    public void insertFirst(Vertex c) {                        // make new link
        Link newLink = new Link(c);
        newLink.next = first;       // it points to old first link
        first = newLink;            // now first points to this
    }

    // ==============================================================
    // 编程作业 13.2
    public void insertLast(Vertex c) {                        // make new link
        Link newLink = new Link(c);
        last.next = newLink;            // now first points to this
        last = newLink;
    }

    // ==============================================================
    // 编程作业 13.2
    public Link find()      // find link with non-visited vertex
    {                           // (assumes non-empty list)
        Link current = first.next;              // start at 'first.next'
        while (current != null && current.vertex.wasVisited)  // while no match,
        {
            current = current.next;      // go to next link
        }
        return current;                    // found it
    }

    // ==============================================================
    // -------------------------------------------------------------
    public void displayList()      // display the list
    {
        Link current = first;       // start at beginning of list
        System.out.print("List (" + current.vertex.label + "): ");
        current = current.next;
        while (current != null)      // until end of list,
        {
            current.displayLink();   // print data
            current = current.next;  // move to next link
        }
        System.out.println("");
    }
    // -------------------------------------------------------------
}  // end class LinkList