package com.eussi.data._13;


import com.eussi.data._14.DistPar;
import com.eussi.data._04.StackX;

import java.util.ArrayList;
import java.util.List;

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

    //path
    private int nTree;           // number of verts in tree
    private DistPar sPath[];     // array for shortest-path data
    private int infinity;
    private int currentVert;     // current vertex
    private int startToCurrent;  // distance to currentVert

    //exercise 14.4
    private int vertexs[];		// array for index of vertex in vertexList
    private List<int[]> list;	// save the possible vertexs[] ;

    public GraphD() {
        this(0);
    }  // end constructor

    public GraphD(int initValue) {
        vertexList = new Vertex[MAX_VERTS];
        // adjacency matrix
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int j = 0; j < MAX_VERTS; j++)      // set adjacency
            for (int k = 0; k < MAX_VERTS; k++)   //    matrix to 0
                adjMat[j][k] = initValue;
        sortedArray = new char[MAX_VERTS];  // sorted vert labels
        theStack = new StackX<>();  //use for exercise 13.3

        nTree = 0;
        sPath = new DistPar[MAX_VERTS];
        infinity = initValue;
    }

    public void addVertex(char... labs) {
        for (char lab : labs) {
            addVertex(lab);
        }
    }

    public void addVertex(char lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }

    public void addEdge(int start, int end) {
        addEdge(start, end, 1);
    }

    public void addEdge(int start, int end, int weight) {
        adjMat[start][end] = weight;  // (directed)
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

    public void path() {
        path(0);
    }

    public void path2() {
        // 编程作业 14.1
        // 外层加了个for循环
        for (int i = 0; i < nVerts; i++) {
            println("started vertex:" + vertexList[i].label);
            path(i);
        }
    }

    private void path(int startTree) {
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

            if (minDist == infinity) {                        // or in tree,
                println("There are unreachable vertices:" +
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
            println("There are all reachable vertices");
        }
        displayPaths();                // display sPath[] contents

        nTree = 0;                     // clear tree
        for (int j = 0; j < nVerts; j++)
            vertexList[j].isInTree = false;
    }

    public void displayPaths() {
        for (int j = 0; j < nVerts; j++) {
            print(vertexList[j].label + "="); // B=
            if (sPath[j].distance == infinity)
                print("inf");                  // inf
            else
                print(sPath[j].distance);      // 50
            char parent = vertexList[sPath[j].parentVert].label;
            print("(" + parent + ") ");       // (A)
        }
        println("");
    }

    public void adjust_sPath() {
        // adjust values in shortest-path array sPath
        int column = 1;                // skip starting vertex
        while (column < nVerts) {
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
            if (startToFringe < sPathDist) {
                sPath[column].parentVert = currentVert;
                sPath[column].distance = startToFringe;
            }
            column++;
        }  // end while(column < nVerts)
    }

    public int getMin() {                              //    with minimum distance
        int minDist = infinity;        // assume minimum
        int indexMin = 0;
        for (int j = 0; j < nVerts; j++) {                           // if it's in tree and
            if (!vertexList[j].isInTree &&  // smaller than old one
                    sPath[j].distance < minDist) {
                minDist = sPath[j].distance;
                indexMin = j;            // update minimum
            }
        }  // end for
        return indexMin;               // return index of minimum
    }


    public void floyd() {
        for (int i = 0; i < nVerts; i++) {
            for (int j = 0; j < nVerts; j++) {
                if (adjMat[i][j] < infinity) {
                    for (int k = 0; k < nVerts; k++) {
                        if (adjMat[k][i] < infinity) {
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

    public void displayPaths2() {
        for (int i = 0; i < nVerts; i++) {
            print("	" + vertexList[i].label);
        }
        println();
        for (int j = 0; j < nVerts; j++) {
            print(vertexList[j].label);
            for (int i = 0; i < nVerts; i++) {
                int temp = adjMat[j][i];
                if (temp != infinity)
                    print("	" + adjMat[j][i]);
                else {
                    print("	");
                }
            }
            println();
        }
        println();
    }

    public void travelingSalesman() {
        vertexs = new int[nVerts];
        list = new ArrayList<>();
        for (int i = 0; i < nVerts; i++) {
            vertexs[i] = i;
        }
        doAnagram(vertexs.length);
    }

    public void doAnagram(int newSize) {
        if (newSize == 1) {                    // if too small,
            for (int i = 0; i < nVerts - 1; i++) {
                if (adjMat[vertexs[i]][vertexs[i + 1]] == infinity) {
                    return;
                }
            }
            if (adjMat[vertexs[nVerts - 1]][vertexs[0]] != infinity) {// 最后一个顶点到起始点
                int[] temp = new int[nVerts];
                System.arraycopy(vertexs, 0, temp, 0, nVerts);
                list.add(temp);
            }
            return;
        }                          // go no further
        for (int j = 0; j < newSize; j++) {
            doAnagram(newSize - 1);             // anagram remaining
            rotate(newSize);
        }
    }

    public void rotate(int newSize) {
        int j;
        int position = nVerts - newSize;
        int temp = vertexs[position];       // save first letter
        for (j = position + 1; j < nVerts; j++)
            // shift others left
            vertexs[j - 1] = vertexs[j];
        vertexs[j - 1] = temp;                 // put first on right
    }

    public void displayWord() {
        int minIndex = 0;
        int minWeight = infinity;
        println("Calculating weight:");
        for (int i = 0; i < list.size(); i++) {
            int weight = 0;
            int[] temp = list.get(i);

            //打印此次排列的节点
            for (int j = 0; j < nVerts; j++) {
                print(" " + vertexList[temp[j]].label);
            }
            print(" " + vertexList[temp[0]].label);

            for (int j = 0; j < temp.length - 1; j++) {  //计算权重
                weight += adjMat[temp[j]][temp[j + 1]];
            }
            weight += adjMat[temp[temp.length - 1]][temp[0]];  //计算最后一个节点到第一个节点权重
            println("(" + weight + ")");
            if (weight < minWeight) {
                minWeight = weight;
                minIndex = i;
            }
        }

        println("minimum weight:");
        int[] min = list.get(minIndex);
        for (int j = 0; j < nVerts; j++) {
            print(" " + vertexList[min[j]].label);
        }
        print(" " + vertexList[min[0]].label);
        println("(" + minWeight + ")");
    }
}