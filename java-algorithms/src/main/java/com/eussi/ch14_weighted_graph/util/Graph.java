package com.eussi.ch14_weighted_graph.util;

/**
 * @author wangxueming
 * @create 2020-03-11 11:48
 * @description
 */
public class Graph
{
    private final int MAX_VERTS = 20;
    private final int INFINITY = 1000000;
    private Vertex vertexList[]; // list of vertices
    private int adjMat[][];      // adjacency matrix
    private int nVerts;          // current number of vertices
    private int currentVert;
    private PriorityQ thePQ;
    private int nTree;           // number of verts in tree

    //14.5
    private int vertexs[];		// array for index of vertex in vertexList
    // -------------------------------------------------------------
    public Graph()               // constructor
    {
        vertexList = new Vertex[MAX_VERTS];
        // adjacency matrix
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for(int j=0; j<MAX_VERTS; j++)      // set adjacency
            for(int k=0; k<MAX_VERTS; k++)   //    matrix to 0
                adjMat[j][k] = INFINITY;
        thePQ = new PriorityQ();
    }  // end constructor
    // -------------------------------------------------------------
    public void addVertex(char lab)
    {
        vertexList[nVerts++] = new Vertex(lab);
    }
    // -------------------------------------------------------------
    public void addEdge(int start, int end, int weight)
    {
        adjMat[start][end] = weight;
        adjMat[end][start] = weight;
    }
    // -------------------------------------------------------------
    public void displayVertex(int v)
    {
        System.out.print(vertexList[v].label);
    }
    // -------------------------------------------------------------
    public void mstw()           // minimum spanning tree
    {
        currentVert = 0;          // start at 0

        while(nTree < nVerts-1)   // while not all verts in tree
        {                      // put currentVert in tree
            vertexList[currentVert].isInTree = true;
            nTree++;

            // insert edges adjacent to currentVert into PQ
            for(int j=0; j<nVerts; j++)   // for each vertex,
            {
                if(j==currentVert)         // skip if it's us
                    continue;
                if(vertexList[j].isInTree) // skip if in the tree
                    continue;
                int distance = adjMat[currentVert][j];
                if( distance == INFINITY)  // skip if no edge
                    continue;
                putInPQ(j, distance);      // put it in PQ (maybe)
            }
            if(thePQ.size()==0)           // no vertices in PQ?
            {
                System.out.println(" GRAPH NOT CONNECTED");
                return;
            }
            // remove edge with minimum distance, from PQ
            Edge theEdge = thePQ.removeMin();
            int sourceVert = theEdge.srcVert;
            currentVert = theEdge.destVert;

            // display edge from source to current
            System.out.print( vertexList[sourceVert].label);
            System.out.print( vertexList[currentVert].label);
            System.out.print( adjMat[sourceVert][currentVert]);
            System.out.print(" ");
        }  // end while(not all verts in tree)

        // mst is complete
        for(int j=0; j<nVerts; j++)     // unmark vertices
            vertexList[j].isInTree = false;
    }  // end mstw
    // -------------------------------------------------------------
    public void putInPQ(int newVert, int newDist)
    {
        // is there another edge with the same destination vertex?
        int queueIndex = thePQ.find(newVert);
        if(queueIndex != -1)              // got edge's index
        {
            Edge tempEdge = thePQ.peekN(queueIndex);  // get edge
            int oldDist = tempEdge.distance;
            if(oldDist > newDist)          // if new edge shorter,
            {
                thePQ.removeN(queueIndex);  // remove old edge
                Edge theEdge =
                        new Edge(currentVert, newVert, newDist);
                thePQ.insert(theEdge);      // insert new edge
            }
            // else no action; just leave the old vertex there
        }  // end if
        else  // no edge with same destination vertex
        {                              // so insert new one
            Edge theEdge = new Edge(currentVert, newVert, newDist);
            thePQ.insert(theEdge);
        }
    }  // end putInPQ()


    // ===========================================================
    public void displayWord() {
        for (int j = 0; j < nVerts; j++) {
            System.out.print(" " + vertexList[vertexs[j]].label);
        }
        System.out.println(" " + vertexList[vertexs[0]].label);
    }

    // ===========================================================
    // 编程作业 14.5
    public void travelingSalesman() {
        vertexs = new int[nVerts];
        for (int i = 0; i < nVerts; i++) {
            vertexs[i] = i;
        }
        doAnagram(vertexs.length);
    }

    // ===========================================================
    public void doAnagram(int newSize) {
        if (newSize == 1) {                    // if too small,
            for (int i = 0; i < nVerts - 1; i++) {
                if (adjMat[vertexs[i]][vertexs[i + 1]] == INFINITY) {
                    return;
                }
            }
            if (adjMat[vertexs[nVerts - 1]][vertexs[0]] != INFINITY) {// 最后一个顶点到起始点
                displayWord();
            }
            return;
        }                          // go no further
        for (int j = 0; j < newSize; j++)         // for each position,
        {
            doAnagram(newSize - 1);             // anagram remaining
            rotate(newSize);
        }
    }

    // ===========================================================
    public void rotate(int newSize)
    // rotate left all chars from position to end
    {
        int j;
        int position = nVerts - newSize;
        int temp = vertexs[position];       // save first letter
        for (j = position + 1; j < nVerts; j++)
            // shift others left
            vertexs[j - 1] = vertexs[j];
        vertexs[j - 1] = temp;                 // put first on right
    }
    // ===========================================================
// -------------------------------------------------------------
}  // end class Graph