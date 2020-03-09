package com.eussi.ch13_graph.util;

/**
 * @author wangxueming
 * @create 2020-03-09 17:15
 * @description
 */
public class GraphWithLink {
    // ============================================================
    // 编程作业 13.2
    // 这里只用了一个LinkList数组
    // 每个LinkList的第一个节点Link表示当前节点
    // 后面节点表示与前节点相邻接的节点
    // 其实Link完全可以由Vertex代替，在Vertex里面添加一个域next
    // 这个next指向与此Vertex相邻接的顶点
    private final int MAX_VERTS = 20;
    private LinkList adjList[];      // adjacency list
    private int nVerts;          // current number of vertices
    private StackX<Vertex> theStack;

    // ------------------------------------------------------------
    public GraphWithLink()               // constructor
    {
        // adjacency matrix
        adjList = new LinkList[MAX_VERTS];
        nVerts = 0;
        theStack = new StackX();
    }  // end constructor

    // ------------------------------------------------------------
    public void addVertex(char lab) {
        Vertex newVertex = new Vertex(lab);
        LinkList list = new LinkList(newVertex);
        adjList[nVerts++] = list;
    }

    // ------------------------------------------------------------
    public void addEdge(int start, int end) {
        adjList[start].insertLast(adjList[end].first.vertex);        // directed?
    }

    // ------------------------------------------------------------
    public void displayVertex(Vertex v) {
        System.out.print(v.label);
    }

    // ------------------------------------------------------------
    // ============================================================
    // 编程作业 13.2
    public void dfs()  // depth-first search
    {                                 // begin at vertex 0
        adjList[0].first.vertex.wasVisited = true;  			// mark it
        displayVertex(adjList[0].first.vertex);                 // display it
        theStack.push(adjList[0].first.vertex);                 // push it

        while (!theStack.isEmpty())      // until stack empty,
        {
            // get an unvisited vertex adjacent to stack top
            Vertex v = getAdjUnvisitedVertex(theStack.peek());
            if (v == null)                    // if no such vertex,
                theStack.pop();
            else                           // if it exists,
            {
                v.wasVisited = true;  // mark it
                displayVertex(v);                 // display it
                theStack.push(v);                 // push it
            }
        }  // end while

        // stack is empty, so we're done
        for (int j = 0; j < nVerts; j++)
            // reset flags
            adjList[j].first.vertex.wasVisited = false;
    }  // end dfs

    // ============================================================
    // 编程作业 13.2
    // returns an unvisited vertex adj to v
    public Vertex getAdjUnvisitedVertex(Vertex v) {
        int j = -1;
        for (int i = 0; i < adjList.length; i++) {
            if (adjList[i].first.vertex.label == v.label) {
                j = i;
                break;
            }
        }
        Link link = adjList[j].find();   //find and not be visited
        if (link != null) {
            return link.vertex;
        } else {
            return null;
        }
    }  // end getAdjUnvisitedVertex()

    // ============================================================
}