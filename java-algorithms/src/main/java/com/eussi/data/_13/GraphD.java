package com.eussi.data._13;


import com.eussi.data._04.StackX;

import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * @author wangxueming
 * @create 2020-03-08 16:40
 * @description 有向图
 */
public class GraphD {
    private final int MAX_VERTS = 20;
    private Vertex vertexList[]; // list of vertices
    private int adjMat[][];      // adjacency matrix
    private int nVerts;          // current number of vertices
    private char sortedArray[];
    private StackX<Integer> theStack;   //use for exercise 13.3

    public GraphD() {
        vertexList = new Vertex[MAX_VERTS];
        // adjacency matrix
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int j = 0; j < MAX_VERTS; j++)      // set adjacency
            for (int k = 0; k < MAX_VERTS; k++)   //    matrix to 0
                adjMat[j][k] = 0;
        sortedArray = new char[MAX_VERTS];  // sorted vert labels
        theStack = new StackX<>();  //use for exercise 13.3
    }  // end constructor

    public void addVertex(char... labs) {
        for (char lab : labs) {
            addVertex(lab);
        }
    }

    public void addVertex(char lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
    }

    public void displayVertex(int v) {
        print(vertexList[v].label);
    }

    public void topo() {
        int orig_nVerts = nVerts;  // remember how many verts

        while (nVerts > 0) {
            // get a vertex with no successors, or -1
            int currentVertex = noSuccessors();
            if (currentVertex == -1) {
                println("ERROR: Graph has cycles");
                return;
            }
            // insert vertex label in sorted array (start at end)
            sortedArray[nVerts - 1] = vertexList[currentVertex].label;

            deleteVertex(currentVertex);  // delete vertex
        }  // end while

        // vertices all gone; display sortedArray
        print("Topologically sorted order: ");
        for (int j = 0; j < orig_nVerts; j++)
            print(sortedArray[j]);
        println("");
    }  // end topo

    public int noSuccessors() {
        boolean isEdge;  // edge from row to column in adjMat

        for (int row = 0; row < nVerts; row++) {
            isEdge = false;                 // check edges
            for (int col = 0; col < nVerts; col++) {
                if (adjMat[row][col] > 0) {                         // another,
                    isEdge = true;
                    break;                    // this vertex
                }                         //    has a successor
            }                            //    try another
            if (!isEdge)                   // if no edges,
                return row;                  //    has no successors
        }
        return -1;                         // no such vertex
    }

    public void deleteVertex(int delVert) {
        if (delVert != nVerts - 1) {                         // delete from vertexList
            for (int j = delVert; j < nVerts - 1; j++)
                vertexList[j] = vertexList[j + 1];
            // delete row from adjMat
            for (int row = delVert; row < nVerts - 1; row++)
                moveRowUp(row, nVerts);
            // delete col from adjMat
            for (int col = delVert; col < nVerts - 1; col++)
                moveColLeft(col, nVerts - 1);
        }
        nVerts--;                    // one less vertex
    }  // end deleteVertex

    private void moveRowUp(int row, int length) {
        for (int col = 0; col < length; col++)
            adjMat[row][col] = adjMat[row + 1][col];
    }

    private void moveColLeft(int col, int length) {
        for (int row = 0; row < length; row++)
            adjMat[row][col] = adjMat[row][col + 1];
    }

    public int getAdjUnvisitedVertex(int v) {
        for (int j = 0; j < nVerts; j++)
            if (adjMat[v][j] == 1 && !vertexList[j].wasVisited)
                return j;
        return -1;
    }  // end getAdjUnvisitedVertex()

    // 编程作业 13.3
    // Connectivity 连通性
    // 通过修改dfs()方法得来
    public void getConnectivity() {
        for (int i = 0; i < nVerts; i++) {   //遍历所有节点
            vertexList[i].wasVisited = true;  // mark it
            displayVertex(i); // display it
            theStack.push(i);                 // push it
            while (!theStack.isEmpty()) {
                // get an unvisited vertex adjacent to stack top
                int v = getAdjUnvisitedVertex(theStack.peek());
                if (v == -1)                    // if no such vertex,
                    theStack.pop();
                else {
                    vertexList[v].wasVisited = true;  // mark it
                    displayVertex(v);                 // display it
                    theStack.push(v);                 // push it
                }
            }  // end while
            // stack is empty, so we're done
            for (int j = 0; j < nVerts; j++)
                // reset flags
                vertexList[j].wasVisited = false;
            println();
        }
    }

    // 编程作业 13.4
    // TransitiveClosure 传递闭包
    // Warshall算法
    public void doTransitiveClosure() {
        for (int x = 0; x < adjMat.length; x++) { // 行
            for (int y = 0; y < adjMat.length; y++) { // 列
                if (adjMat[x][y] == 1) { // x -> y
                    for (int z = 0; z < adjMat.length; z++) {// 行
                        if (adjMat[z][x] == 1) { // z -> x
                            adjMat[z][y] = 1; // x -> y
                        }
                    }
                }
            }
        }
    }

    public void displayMat() {
        for (int i = 0; i < nVerts; i++) {
            for (int j = 0; j < nVerts; j++) {
                print(adjMat[i][j] + " ");
            }
            println();
        }
    }
}