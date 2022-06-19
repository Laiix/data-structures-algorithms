package com.eussi.data._13;

import com.eussi.data._04.StackX;
import com.eussi.data._05.FirstLastLinkList;
import com.eussi.data._05.Link;

/**
 * @author wangxueming
 * @create 2020-03-09 17:15
 * @description
 */
public class GraphWithLink {
    // 编程作业 13.2
    // 这里只用了一个FirstLastLinkList数组
    // 每个FirstLastLinkList的第一个节点Link表示当前节点
    // 后面节点表示与前节点相邻接的节点
    // 其实Link完全可以由Vertex代替，在Vertex里面添加一个域next
    // 这个next指向与此Vertex相邻接的顶点
    private final int MAX_VERTS = 20;
    private FirstLastLinkList<Vertex> adjList[];      // adjacency list
    private int nVerts;          // current number of vertices
    private StackX<Vertex> theStack;

    public GraphWithLink() {
        // adjacency matrix
        adjList = new FirstLastLinkList[MAX_VERTS];
        nVerts = 0;
        theStack = new StackX<>();
    }

    public void addVertex(char... labs) {
        for (char lab : labs) {
            addVertex(lab);
        }
    }

    public void addVertex(char lab) {
        Vertex newVertex = new Vertex(lab);
        FirstLastLinkList<Vertex> list = new FirstLastLinkList<>(newVertex);
        adjList[nVerts++] = list;
    }

    public void addEdge(int start, int end) {
        adjList[start].insertLast(adjList[end].getFirst().data);        // directed?
    }

    public void displayVertex(Vertex v) {
        System.out.print(v.label);
    }

    // 编程作业 13.2
    public void dfs() {                                 // begin at vertex 0
        adjList[0].getFirst().data.wasVisited = true;            // mark it
        displayVertex(adjList[0].getFirst().data);                 // display it
        theStack.push(adjList[0].getFirst().data);                 // push it

        while (!theStack.isEmpty()) {
            // get an unvisited vertex adjacent to stack top
            Vertex v = getAdjUnvisitedVertex(theStack.peek());
            if (v == null)                    // if no such vertex,
                theStack.pop();
            else {
                v.wasVisited = true;  // mark it
                displayVertex(v);                 // display it
                theStack.push(v);                 // push it
            }
        }  // end while

        // stack is empty, so we're done
        for (int j = 0; j < nVerts; j++)
            // reset flags
            adjList[j].getFirst().data.wasVisited = false;
    }  // end dfs

    // 编程作业 13.2
    // returns an unvisited vertex adj to v
    public Vertex getAdjUnvisitedVertex(Vertex v) {
        int j = -1;
        for (int i = 0; i < adjList.length; i++) {
            if (adjList[i].getFirst().data.label == v.label) {
                j = i;
                break;
            }
        }
        Link<Vertex> link = find(adjList[j]);   //find and not be visited
        if (link != null) {
            return link.data;
        } else {
            return null;
        }
    }

    // 编程作业 13.2
    public Link<Vertex> find(FirstLastLinkList<Vertex> linkList) {                           // (assumes non-empty list)
        Link<Vertex> current = linkList.getFirst().next;              // start at 'first.next'
        while (current != null && current.data.wasVisited) {
            current = current.next;      // go to next link
        }
        return current;                    // found it
    }

}