package com.eussi.ch14_weighted_graph.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxueming
 * @create 2020-03-12 11:06
 * @description
 */
public class GraphD {
    private final int MAX_VERTS = 20;
    private final int INFINITY = 1000000;
    private Vertex vertexList[]; // list of vertices
    private int adjMat[][];      // adjacency matrix
    private int nVerts;          // current number of vertices
    private int nTree;           // number of verts in tree
    private DistPar sPath[];     // array for shortest-path data
    private int currentVert;     // current vertex
    private int startToCurrent;  // distance to currentVert

    //exercise 14.4
    private int vertexs[];		// array for index of vertex in vertexList
    private List<int[]> list;	// save the possible vertexs[] ;


    // -------------------------------------------------------------
    public GraphD()               // constructor
    {
        vertexList = new Vertex[MAX_VERTS];
        // adjacency matrix
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        nTree = 0;
        for (int j = 0; j < MAX_VERTS; j++)     // set adjacency
            for (int k = 0; k < MAX_VERTS; k++)  //     matrix
                adjMat[j][k] = INFINITY;     //     to infinity
        sPath = new DistPar[MAX_VERTS];    // shortest paths
    }  // end constructor

    // -------------------------------------------------------------
    public void addVertex(char lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }

    // -------------------------------------------------------------
    public void addEdge(int start, int end, int weight) {
        adjMat[start][end] = weight;  // (directed)
    }

    // -------------------------------------------------------------
    public void path()                // find all shortest paths
    {
        int startTree = 0;             // start at vertex 0
        vertexList[startTree].isInTree = true;
        nTree = 1;                     // put it in tree

        // transfer row of distances from adjMat to sPath
        for (int j = 0; j < nVerts; j++) {
            int tempDist = adjMat[startTree][j];
            sPath[j] = new DistPar(startTree, tempDist);
        }

        // until all vertices are in the tree
        while (nTree < nVerts) {
            int indexMin = getMin();    // get minimum from sPath
            int minDist = sPath[indexMin].distance;

            if (minDist == INFINITY)     // if all infinite
            {                        // or in tree,
                System.out.println("There are unreachable vertices");
                break;                   // sPath is complete
            } else {                        // reset currentVert
                currentVert = indexMin;  // to closest vert
                startToCurrent = sPath[indexMin].distance;
                // minimum distance from startTree is
                // to currentVert, and is startToCurrent
            }
            // put current vertex in tree
            vertexList[currentVert].isInTree = true;
            nTree++;
            adjust_sPath();             // update sPath[] array
        }  // end while(nTree<nVerts)

        displayPaths();                // display sPath[] contents

        nTree = 0;                     // clear tree
        for (int j = 0; j < nVerts; j++)
            vertexList[j].isInTree = false;
    }  // end path()

    public void path2()                // find all shortest paths
    {
        // ==============================================
        // 编程作业 14.1
        // 外层加了个for循环
        for (int i = 0; i < nVerts; i++) {
            int startTree = i;             // start at vertex 0
            vertexList[startTree].isInTree = true;
            nTree = 1;                     // put it in tree
            System.out.println("started vertex:" + vertexList[startTree].label);

            // transfer row of distances from adjMat to sPath
            for (int j = 0; j < nVerts; j++) {
                int tempDist = adjMat[startTree][j];
                sPath[j] = new DistPar(startTree, tempDist);
            }

            // until all vertices are in the tree
            while (nTree < nVerts) {
                int indexMin = getMin();    // get minimum from sPath
                int minDist = sPath[indexMin].distance;

                if (minDist == INFINITY)     // if all infinite
                {                        // or in tree,
                    System.out.println("There are unreachable vertices:" +
                            vertexList[indexMin].label +
                            ", parentVert:" +
                            vertexList[sPath[indexMin].parentVert].label);

                    break;                   // sPath is complete
                } else {                        // reset currentVert
                    currentVert = indexMin;  // to closest vert
                    startToCurrent = sPath[indexMin].distance;
                    // minimum distance from startTree is
                    // to currentVert, and is startToCurrent
                }
                // put current vertex in tree
                vertexList[currentVert].isInTree = true;
                nTree++;
                adjust_sPath();             // update sPath[] array
            }  // end while(nTree<nVerts)

            if (nTree == nVerts) {
                System.out.println("There are all reachable vertices");
            }
            displayPaths();                // display sPath[] contents

            nTree = 0;                     // clear tree
            for (int j = 0; j < nVerts; j++)
                vertexList[j].isInTree = false;
            // ==============================================
        }
    }

    // -------------------------------------------------------------
    public int getMin()               // get entry from sPath
    {                              //    with minimum distance
        int minDist = INFINITY;        // assume minimum
        int indexMin = 0;
        for (int j = 0; j < nVerts; j++)    // for each vertex,
        {                           // if it's in tree and
            if (!vertexList[j].isInTree &&  // smaller than old one
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
        int column = 1;                // skip starting vertex
        while (column < nVerts)         // go across columns
        {
            // if this column's vertex already in tree, skip it
            if (vertexList[column].isInTree) {
                column++;
                continue;
            }
            // calculate distance for one sPath entry
            // get edge from currentVert to column
            int currentToFringe = adjMat[currentVert][column];
            // add distance from start
            int startToFringe = startToCurrent + currentToFringe;
            // get distance of current sPath entry
            int sPathDist = sPath[column].distance;

            // compare distance from start with sPath entry
            if (startToFringe < sPathDist)   // if shorter,
            {                            // update sPath
                sPath[column].parentVert = currentVert;
                sPath[column].distance = startToFringe;
            }
            column++;
        }  // end while(column < nVerts)
    }  // end adjust_sPath()

    public void floyd() {
        for (int i = 0; i < nVerts; i++) {
            for (int j = 0; j < nVerts; j++) {
                if (adjMat[i][j] < INFINITY) {
                    for (int k = 0; k < nVerts; k++) {
                        if (adjMat[k][i] < INFINITY) {
                            int temp = adjMat[k][i] + adjMat[i][j];
                            if (adjMat[k][j] > temp) {
                                adjMat[k][j] = temp;
                            }
                        }
                    }
                }
            }
        }
    }


    // -------------------------------------------------------------
    public void displayPaths() {
        for (int j = 0; j < nVerts; j++) // display contents of sPath[]
        {
            System.out.print(vertexList[j].label + "="); // B=
            if (sPath[j].distance == INFINITY)
                System.out.print("inf");                  // inf
            else
                System.out.print(sPath[j].distance);      // 50
            char parent = vertexList[sPath[j].parentVert].label;
            System.out.print("(" + parent + ") ");       // (A)
        }
        System.out.println("");
    }

    public void displayPaths2() {
        for (int i = 0; i < nVerts; i++) {
            System.out.print("	" + vertexList[i].label);
        }
        System.out.println();
        for (int j = 0; j < nVerts; j++) // display contents of sPath[]
        {
            System.out.print(vertexList[j].label);
            for (int i = 0; i < nVerts; i++) {
                int temp = adjMat[j][i];
                if (temp != INFINITY)
                    System.out.print("	" + adjMat[j][i]);
                else {
                    System.out.print("	");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // 编程作业 14.4
    public void travelingSalesman() {
        vertexs = new int[nVerts];
        list = new ArrayList();
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
                int[] temp = new int[nVerts];
                System.arraycopy(vertexs, 0, temp, 0, nVerts);
                list.add(temp);
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

    // ===========================================================
    public void displayWord() {
        int minIndex = 0;
        int minWeight = INFINITY;
        System.out.println("Calculating weight:");
        for (int i = 0; i < list.size(); i++) {
            int weight = 0;
            int[] temp = list.get(i);

            //打印此次排列的节点
            for (int j = 0; j < nVerts; j++) {
                System.out.print(" " + vertexList[temp[j]].label);
            }
            System.out.print(" " + vertexList[temp[0]].label);

            for (int j = 0; j < temp.length - 1; j++) {  //计算权重
                weight += adjMat[temp[j]][temp[j + 1]];
            }
            weight += adjMat[temp[temp.length - 1]][temp[0]];  //计算最后一个节点到第一个节点权重
            System.out.println("(" + weight + ")");
            if (weight < minWeight) {
                minWeight = weight;
                minIndex = i;
            }
        }

        System.out.println("minimum weight:");
        int[] min = list.get(minIndex);
        for (int j = 0; j < nVerts; j++) {
            System.out.print(" " + vertexList[min[j]].label);
        }
        System.out.print(" " + vertexList[min[0]].label);
        System.out.println("(" + minWeight + ")");
    }

// -------------------------------------------------------------
}