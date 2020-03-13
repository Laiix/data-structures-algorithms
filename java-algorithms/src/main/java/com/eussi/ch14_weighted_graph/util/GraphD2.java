package com.eussi.ch14_weighted_graph.util;

import java.util.List;

/**
 * @author wangxueming
 * @create 2020-03-12 22:59
 * @description
 */
public class GraphD2 {
    private final int MAX_VERTS = 20;
    private final int INFINITY = 1000000;
    private Vertex2[] vertexs;
    private int nVerts;
    private int nTree;
    private DistPar sPath[];     // array for shortest-path data
    private int currentVert;     // current vertex
    private int startToCurrent;  // distance to currentVert

    public GraphD2() {
        this.vertexs = new Vertex2[MAX_VERTS];
        this.sPath = new DistPar[MAX_VERTS];    // shortest paths
    }

    public void addEdge(int start, int end, int weight) {
        Edge2 edge = new Edge2(weight, end);
        vertexs[start].addEdge(edge);
    }

    // -------------------------------------------------------------

    public void addVertex(char lab) {
        vertexs[nVerts++] = new Vertex2(lab);
    }

    // -------------------------------------------------------------
    public void path()                // find all shortest paths
    {
        for (int k = 0; k < nVerts; k++) {
            int startTree = k;             // start at vertex 0
            vertexs[startTree].isInTree = true;
            nTree = 1;                     // put it in tree

            // transfer row of distances from adjMat to sPath
            List<Edge2> edgesList = vertexs[startTree].edges;
            for (int i = 0; i < MAX_VERTS; i++) {  //not like adjMat than has all data for edges
                sPath[i] = new DistPar(startTree, INFINITY);
            }
            for (int i = 0; i < edgesList.size(); i++) {
                Edge2 tempEdge = edgesList.get(i);
                sPath[tempEdge.end] = new DistPar(startTree, tempEdge.weight);
            }

            // until all vertices are in the tree
            while (nTree < nVerts) {
                int indexMin = getMin();    // get minimum from sPath
                int minDist = sPath[indexMin].distance;

                if (minDist == INFINITY)     // if all infinite
                {                        // or in tree,
                    System.out.println("There are unreachable vertices:" +
                            vertexs[indexMin].label +
                            ", parentVert:" +
                            vertexs[sPath[indexMin].parentVert].label);
                    break;                   // sPath is complete
                } else {                        // reset currentVert
                    currentVert = indexMin;  // to closest vert
                    startToCurrent = sPath[indexMin].distance;
                    // minimum distance from startTree is
                    // to currentVert, and is startToCurrent
                }
                // put current vertex in tree
                vertexs[currentVert].isInTree = true;
                nTree++;
                adjust_sPath();             // update sPath[] array
            }  // end while(nTree<nVerts)

            if (nTree == nVerts) {
                System.out.println("There are all reachable vertices");
            }
            displayPaths();                // display sPath[] contents

            nTree = 0;                     // clear tree
            for (int j = 0; j < nVerts; j++)
                vertexs[j].isInTree = false;
        }
    }  // end path()
    // -------------------------------------------------------------

    public int getMin()               // get entry from sPath
    {                              // with minimum distance
        int minDist = INFINITY;        // assume minimum
        int indexMin = 0;
        for (int j = 1; j < nVerts; j++)    // for each vertex,
        {                           // if it's in tree and
            if (!vertexs[j].isInTree &&  // smaller than old one
                    sPath[j].distance < minDist) {
                minDist = sPath[j].distance;
                indexMin = j;            // update minimum
            }
        }  // end for
        return indexMin;               // return index of minimum
    }  // end getMin()
    // -------------------------------------------------------------

    public void adjust_sPath() {
        // adjust values in shortest-path array sPath
        List<Edge2> list = vertexs[currentVert].edges;
        for (int i = 0; i < list.size(); i++)         // go across columns
        {
            // if this column's vertex already in tree, skip it
            int v = list.get(i).end;
            if (vertexs[v].isInTree) {
                continue;
            }
            // calculate distance for one sPath entry
            // get edge from currentVert to fringe
            Edge2 e = list.get(i);
            int fringe = e.end;
            int currentToFringe = e.weight;
            // add distance from start
            int startToFringe = startToCurrent + currentToFringe;
            // get distance of current sPath entry
            int sPathDist = sPath[fringe].distance;

            // compare distance from start with sPath entry
            if (startToFringe < sPathDist)   // if shorter,
            {                            // update sPath
                sPath[fringe].parentVert = currentVert;
                sPath[fringe].distance = startToFringe;
            }
        }  // end while(column < nVerts)
    }  // end adjust_sPath()

    // -------------------------------------------------------------

    public void displayPaths() {
        for (int j = 0; j < nVerts; j++) // display contents of sPath[]
        {
            System.out.print(vertexs[j].label + "="); // B=
            if (sPath[j].distance == INFINITY)
                System.out.print("inf");                  // inf
            else
                System.out.print(sPath[j].distance);      // 50
            char parent = vertexs[sPath[j].parentVert].label;
            System.out.print("(" + parent + ") ");       // (A)
        }
        System.out.println("");
    }
    // -------------------------------------------------------------
}