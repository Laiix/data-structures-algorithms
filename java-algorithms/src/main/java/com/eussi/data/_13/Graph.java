package com.eussi.data._13;

import com.eussi.data._14.Edge;
import com.eussi.data._04.Queue;
import com.eussi.data._04.StackX;
import com.eussi.data._14.PriorityQ;

import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * @author wangxueming
 * @create 2020-03-04 23:37
 * @description
 */
public class Graph {
    private final int MAX_VERTS = 20;
    private Vertex vertexList[]; // list of vertices
    private int adjMat[][];      // adjacency matrix
    private int nVerts;          // current number of vertices
    private StackX<Integer> theStack;
    private Queue<Integer> theQueue;
    //mstw
    private int currentVert;
    private int nTree;
    private int infinity;
    private PriorityQ thePQ;

    //14.5
    private int vertexs[];		// array for index of vertex in vertexList

    public Graph() {
        this(0);
    }  // end constructor

    public Graph(int initValue) {
        vertexList = new Vertex[MAX_VERTS];
        // adjacency(毗邻；四周；邻接物) matrix
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int y = 0; y < MAX_VERTS; y++)      // set adjacency
            for (int x = 0; x < MAX_VERTS; x++)   //    matrix to 0
                adjMat[x][y] = initValue;
        theStack = new StackX<>();
        theQueue = new Queue<>();
        thePQ = new PriorityQ();
        infinity = initValue;
    }  // end constructor

    public void addVertex(char... lab) {
        for (char c : lab) {
            addVertex(c);
        }
    }

    public void addVertex(char lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    public void addEdge(int start, int end, int weight) {
        adjMat[start][end] = weight;
        adjMat[end][start] = weight;
    }

    public void displayVertex(int v) {
        print(vertexList[v].label);
    }


    /**
     * 这个方法实际执行了深度优先搜索。下面会看到这段代
     * 码如何包含了前面提出的三条规则。它循环执行,直到栈为空。每次循环中,它做四件事
     * 1.用peek()方法检查栈顶的顶点。
     * 2.试图找到这个顶点还未访问的邻接点。
     * 3.如果没有找到,出栈
     * 4.如果找到这样的顶点,访问这个顶点,并把它放入栈。
     * 方法的最后，重置了所有的标志位，这样稍后可以继续使用dfs
     */
    public void dfs() {       // depth-first search
        // begin at vertex 0
        vertexList[0].wasVisited = true;  // mark it
        displayVertex(0);                 // display it
        theStack.push(0);                 // push it

        while(!theStack.isEmpty()) {
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
        for (int j = 0; j < nVerts; j++)          // reset flags
            vertexList[j].wasVisited = false;
    }

    public void bfs() {      // breadth-first search
        // begin at vertex 0
        vertexList[0].wasVisited = true; // mark it
        displayVertex(0);                // display it
        theQueue.insert(0);              // insert at tail
        int v2;

        while (!theQueue.isEmpty()) {
            int v1 = theQueue.remove();   // remove vertex at head
            // until it has no unvisited neighbors
            while ((v2 = getAdjUnvisitedVertex(v1)) != -1) {                                  // get one,
                vertexList[v2].wasVisited = true;  // mark it
                displayVertex(v2);                 // display it
                theQueue.insert(v2);               // insert it
            }   // end while
        }  // end while(queue not empty)

        // queue is empty, so we're done
        for (int j = 0; j < nVerts; j++)             // reset flags
            vertexList[j].wasVisited = false;
    }  // end bfs()


    public void mstByDfs() {      // minimum spanning tree (depth first)
        // start at 0
        vertexList[0].wasVisited = true;   // mark it
        theStack.push(0);                  // push it

        while (!theStack.isEmpty()) {                               // get stack top
            int currentVertex = theStack.peek();
            // get next unvisited neighbor
            int v = getAdjUnvisitedVertex(currentVertex);
            if (v == -1)                     // if no more neighbors
                theStack.pop();              //    pop it away
            else {
                vertexList[v].wasVisited = true;  // mark it
                theStack.push(v);                 // push it
                // display edge
                displayVertex(currentVertex);     // from currentV
                displayVertex(v);                 // to v
                print(" ");
            }
        }  // end while(stack not empty)

        // stack is empty, so we're done
        for (int j = 0; j < nVerts; j++)          // reset flags
            vertexList[j].wasVisited = false;
    }  // end mst()


    //通过bfs实现
    public void mstByBfs() {                                // begin at vertex 0
        vertexList[0].wasVisited = true; // mark it
//        displayVertex(0);                // display it
        theQueue.insert(0);              // insert at tail
        int v2;

        while (!theQueue.isEmpty()) {
            int v1 = theQueue.remove();   // remove vertex at head
            // until it has no unvisited neighbors
            while ((v2 = getAdjUnvisitedVertex(v1)) != -1) {                                  // get one,
                vertexList[v2].wasVisited = true;  // mark it
                displayVertex(v1);     // from currentV
                displayVertex(v2);                 // to v
                print(" ");
                theQueue.insert(v2);               // insert it
            }   // end while
        }  // end while(queue not empty)

        // queue is empty, so we're done
        for (int j = 0; j < nVerts; j++)             // reset flags
            vertexList[j].wasVisited = false;
    }


    /**
     * 深度优先搜索的关键在于能够找到与某一顶点邻接且没有访问过的顶点。如何做呢?邻接矩阵
     * 是关键。找到指定顶点所在的行,从第一列开始向后寻找值为1的列;列号是邻接顶点的号码。检
     * 查这个顶点是否未访问过,如果是这样,那么这就是要访问的下一个顶点,如果该行没有顶点既等
     * 于1(邻接)且又是未访问的,那么与指定点相邻接的顶点就全部访问过了。这个过程实现如下
     *
     * @param v
     * @return  an unvisited vertex adj to v
     */
    public int getAdjUnvisitedVertex(int v) {
        for (int j = 0; j < nVerts; j++)
            if (adjMat[v][j] == 1 && !vertexList[j].wasVisited)
                return j;
        return -1;
    }  // end getAdjUnvisitedVertex()


    public void mstw() {
        currentVert = 0;          // start at 0

        while(nTree < nVerts-1) {                      // put currentVert in tree
            vertexList[currentVert].isInTree = true;
            nTree++;

            // insert edges adjacent to currentVert into PQ
            for(int j=0; j<nVerts; j++) {
                if(j==currentVert)         // skip if it's us
                    continue;
                if(vertexList[j].isInTree) // skip if in the tree
                    continue;
                int distance = adjMat[currentVert][j];
                if( distance == infinity)  // skip if no edge
                    continue;
                putInPQ(j, distance);      // put it in PQ (maybe)
            }
            if(thePQ.size()==0) {
                println(" GRAPH NOT CONNECTED");
                return;
            }
            // remove edge with minimum distance, from PQ
            Edge theEdge = thePQ.removeMin();
            int sourceVert = theEdge.srcVert;
            currentVert = theEdge.destVert;

            // display edge from source to current
            print( vertexList[sourceVert].label);
            print( vertexList[currentVert].label);
            print( adjMat[sourceVert][currentVert]);
            print(" ");
        }  // end while(not all verts in tree)

        // mst is complete
        for(int j=0; j<nVerts; j++)     // unmark vertices
            vertexList[j].isInTree = false;
    }

    public void putInPQ(int newVert, int newDist) {
        // is there another edge with the same destination vertex?
        int queueIndex = thePQ.find(newVert);
        if(queueIndex != -1) {
            Edge tempEdge = thePQ.peekN(queueIndex);  // get edge
            int oldDist = tempEdge.distance;
            if(oldDist > newDist) {
                thePQ.removeN(queueIndex);  // remove old edge
                Edge theEdge = new Edge(currentVert, newVert, newDist);
                thePQ.insert(theEdge);      // insert new edge
            }
            // else no action; just leave the old vertex there
        } else {  // no edge with same destination vertex
            Edge theEdge = new Edge(currentVert, newVert, newDist);
            thePQ.insert(theEdge);
        }
    }

    public void travelingSalesman() {
        vertexs = new int[nVerts];
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

    public void displayWord() {
        for (int j = 0; j < nVerts; j++) {
            print(" " + vertexList[vertexs[j]].label);
        }
        println(" " + vertexList[vertexs[0]].label);
    }

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
}