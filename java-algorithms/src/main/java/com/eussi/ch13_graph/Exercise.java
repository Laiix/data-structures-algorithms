package com.eussi.ch13_graph;

import com.eussi.data._13.Chess;
import com.eussi.data._13.Graph;
import com.eussi.data._13.GraphD;
import com.eussi.data._13.GraphWithLink;

import static com.eussi.util.PrintUtil.*;

/**
 * @author wangxueming
 * @create 2020-03-09 10:08
 * @description 编程作业
 */
public class Exercise {
    public static void main(String[] args) {
        /**
         * 13.1 修改bfs程序（清单13.2），通过广度优先搜索找到最小生成树，在
         *  Graph.java程序（清单13.3）中，这个工作是由深度优先搜索完成的。在
         *  main()中，创建带有9个顶点和12条边的图，然后找到最小生成树。
         */
        printExercise("13.1");
        exercise_1();

        /**
         * 13.2 修改dfs程序（清单13.1），用邻接表而不是邻接矩阵执行深度优先
         *  搜索。可以通过修改第5章linkList2.java程序（清单5.2）的Link类和
         *  LinkList类得到链表。修改LinkList中的find()方法，搜索一个未访问的
         *  顶点，而不是一个关键字值。
         */
        sepExercise("13.2");
        exercise_2();

        /**
         * 13.3 修改dfs程序（清单13.1）显示有向图的连通性表，就像“有向图的连
         *  通性”那节描述的一样。
         */
        sepExercise("13.3");
        exercise_3();

        /**
         * 13.4 实现Warshall算法来找到一个图的传递闭包。可以从编程作业13.3题开始。
         *  它有助于显示在算法的不同阶段显示邻接矩阵。
         */
        sepExercise("13.4");
        exercise_4();

        /**
         *	13.5 骑士旅行是一个古老而著名的象棋谜题。题目是在一个空的棋盘上移动
         *	一个骑士，从一个方块到另一个，直到踏遍了棋盘的所有的方块。写一个程序，用
         *	深度优先搜索解决这个问题。最好使棋盘的大小可变，这样可以在较小的棋盘上解
         *	决这个问题。8*8的棋盘中，用个人电脑大概需要几年的时间解决这个问题。5*5的
         *	棋盘只需要几分钟而己。
         * 参考本章“深度优先搜索和游戏仿真”一节。容易想到当发生移动时，创建一个新的骑
         * 士，并放在新的方块中。如果这样，骑士对应一个顶点，而骑士的序列被放到栈中。
         * 当棋盘被骑士完全填满（栈满），任务就完成了。这个问题中，棋盘一般按顺序编号，
         * 左上角为1,右下角为64 (或者 在5*5的棋盘上为1到25)。当査找下一步移动时，骑士
         * 不仅要按规则规定的方式走，还不能踏出棋盘或己经被占用（己经访问）的方块。如果
         * 写程序绘制棋盘并在每步移动后等待键盘输入，就能看到算法的过程，这时越来越多的
         * 骑士放在了棋盘上，当骑士无路可走时，删除一些骑士，尝试一些其他的移动。在下一
         * 章将讨论这个问题的复杂性。
         *
         *	 //骑士只能根据象棋的规则进行移动，要么横向跳动一格纵向跳动两格
         *	 //要么纵向跳动一格横向跳动两格。   例如，   n=4，m=3   时，若骑士在格子(2;1)，
         *	 //则骑士只能移入下面格子：(1;3),(3;3) 或   (4;2)
         *
         *             * 代表骑士的位置
         *             ■ 代表骑士可以走到的位置
         *             □ 代表骑士不可以到的位置
         *                          □ ■ □ ■ □
         *                          ■ □ □ □ ■
         *                          □ □ * □ □
         *                          ■ □ □ □ ■
         *                          □ ■ □ ■ □
         */
        sepExercise("13.5");
        exercise_5(5, 5);
    }

    public static void exercise_5(int x, int y) {
        println("棋盘的宽：" + x);
        println("棋盘的高：" + y);
        Chess chess = new Chess(x, y);
        // chess.disPlayChessBoard();
        chess.knightTour();
    }

    public static void exercise_4() {
        GraphD theGraph = new GraphD();
        theGraph.addVertex('A', 'B', 'C', 'D', 'E');    // 4

        theGraph.addEdge(0, 2);     // AC          from GraphWApp Line 481
        theGraph.addEdge(1, 0);     // BA
        theGraph.addEdge(1, 4);     // BE
        theGraph.addEdge(3, 4);     // DE
        theGraph.addEdge(4, 2);     // EC

        theGraph.displayMat();
        theGraph.doTransitiveClosure();
        println("传递闭包: ");
        theGraph.displayMat();
    }

    public static void exercise_3() {
        GraphD theGraph = new GraphD();
        theGraph.addVertex('A', 'B', 'C', 'D', 'E', 'F');    // 5

        theGraph.addEdge(0, 1);     // AB
        theGraph.addEdge(1, 2);     // BC
        theGraph.addEdge(0, 3);     // AD
        theGraph.addEdge(3, 4);     // DE
        theGraph.addEdge(0, 5);     // AE

        println("连通性: ");
        theGraph.getConnectivity();
    }

    public static void exercise_2() {
        GraphWithLink theGraph = new GraphWithLink();
        theGraph.addVertex('A', 'B', 'C', 'D', 'E', 'F');    // 5

        theGraph.addEdge(0, 1);     // AB
        theGraph.addEdge(1, 2);     // BC
        theGraph.addEdge(0, 3);     // AD
        theGraph.addEdge(3, 4);     // DE
        theGraph.addEdge(0, 5);     // AF

        print("GraphWithLink DFS(depth-first search) Visits: ");
        theGraph.dfs();             // depth-first search
    }

    public static void exercise_1() {
        Graph theGraph = new Graph();
        theGraph.addVertex('A', 'B', 'C', 'D', 'E');    // 4

        theGraph.addEdge(0, 1);     // AB
        theGraph.addEdge(0, 2);     // AC
        theGraph.addEdge(0, 3);     // AD
        theGraph.addEdge(0, 4);     // AE
        theGraph.addEdge(1, 2);     // BC
        theGraph.addEdge(1, 3);     // BD
        theGraph.addEdge(1, 4);     // BE
        theGraph.addEdge(2, 3);     // CD
        theGraph.addEdge(2, 4);     // CE
        theGraph.addEdge(3, 4);     // DE

        print("Minimum spanning tree: ");
        theGraph.mstByBfs();             // minimum spanning tree
    }

}
