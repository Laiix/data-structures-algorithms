package com.eussi.ch14_weighted_graph;

import com.eussi.ch14_weighted_graph.util.Graph;
import com.eussi.ch14_weighted_graph.util.GraphD2;
import com.eussi.ch14_weighted_graph.util.GraphD;
import com.eussi.util.Util;

/**
 * @author wangxueming
 * @create 2020-03-12 18:03
 * @description
 */
public class Exercise {
    public static void main(String[] args) {
        /**
         * 习题
         *      14.1 修改path.java(清单14.2)，打印一张任意两点间最小耗费的表。这个
         * 		 练习需要修改原来假设源点总是A的例程。
         * 	    14.2 迄今为止已经用邻接矩阵和邻接表实现了图。另一个方法是用Java有引用
         * 	 	 代表边，这样Vertex对象就包含一个对其邻接点的引用列表。在有向图中，
         * 	 	 这种方式的引用尤其直观。因为它“指明”从一个顶点到另一个顶点。写程
         * 	 	 序实现这个表示方法。main()方法应该与path.java程序(清单)的图。然
         * 	 	 后，它应该显示图的连通性表，以证明图被正确的构建。还要在某处存储
         * 	 	 每条边的权值。一个方法是使用Edge类，类中存储权值和边的终点。每个
         * 	 	 顶点拥有一个Edge对象的列表--即，从这个顶点开始的边。
         * 	 	14.3 实现Floyd算法。可以从path.java程序（清单14.2）开始，适当地修改它。
         * 		 例如，可以删除所有的最短路径代码。保留对不可到达顶点为无穷大的表示
         * 		 法。这样作，当比较新得到的耗费和已存在的耗费时，就不再需要检查0。
         * 		 所有可能到达的路线的耗费都比无穷大小。可以在main()方法中构建任意
         * 		 复杂的图。
         * 		14.4 实现本单“难题”中描述的旅行商问题。不考虑它的难度，当N较小时，比
         * 	 	  如10或更小，这个问题很好解决。在无向图上尝试一下。使用穷举法测
         * 	 	  试每种可能的城市序列。序列改变的方式，参考第6章“递归”中的
         * 	 	  anagram.java程序(清单6.2)。用无穷大表示不存在的边。这样做，当
         * 	 	  从一个城市到下一个城市的边不存在的情况发生时，不需要中止对序列的
         * 	 	  计算；任何无穷大的权值总和都是不可能的路线。而且，不必考虑消除对
         * 	 	  称路线的情况，例如ABCDEA和AEDCBA都要显示
         * 	 	14.5 编写程序找到并显示带权无向图的所有汉密尔顿回路。
         */
        //习题 14.1
        test_1();
        Util.printSeparator();

        //习题 14.2
        test_2();
        Util.printSeparator();

        //习题 14.3 这个算法可以算出自己到自己的距离，但是Dijkstra算法不可以，因为该算法不会在考虑自己
        test_3();
        Util.printSeparator();

        //习题 14.4
        test_4();
        Util.printSeparator();

        //习题 14.5   方法与14.4一致，只不过一个在无向图，一个在有向图中
        test_5();
    }

    public static void test_5() {
        Graph theGraph = new Graph();
        theGraph.addVertex('A'); // 0 (start)
        theGraph.addVertex('B'); // 1
        theGraph.addVertex('C'); // 2
        theGraph.addVertex('D'); // 3
        theGraph.addVertex('E'); // 4

        theGraph.addEdge(0, 1, 50); // AB 50
        theGraph.addEdge(0, 2, 50); // AC 50
        theGraph.addEdge(1, 3, 90); // BD 90
        theGraph.addEdge(2, 3, 40); // CD 40
        theGraph.addEdge(3, 4, 20); // DE 20
        theGraph.addEdge(4, 0, 70); // EA 70
        theGraph.addEdge(4, 1, 50); // EB 50

        theGraph.travelingSalesman();
    }  // end main()

    public static void test_4() {
        GraphD theGraph = new GraphD();
        theGraph.addVertex('A'); // 0 (start)
        theGraph.addVertex('B'); // 1
        theGraph.addVertex('C'); // 2
        theGraph.addVertex('D'); // 3
        theGraph.addVertex('E'); // 4

        theGraph.addEdge(0, 1, 50); // AB 50
        theGraph.addEdge(0, 2, 50); // AC 50
        theGraph.addEdge(1, 0, 50); // BA 50
        theGraph.addEdge(3, 0, 80); // DA 80
        theGraph.addEdge(1, 2, 60); // BC 60
        theGraph.addEdge(1, 3, 90); // BD 90
        theGraph.addEdge(2, 3, 40); // CD 40
        theGraph.addEdge(3, 4, 20); // DE 20
        theGraph.addEdge(4, 0, 70); // EA 70
        theGraph.addEdge(4, 1, 50); // EB 50

        theGraph.travelingSalesman();
        theGraph.displayWord();
    }

    public static void test_3() {
        GraphD theGraph = new GraphD();
        theGraph.addVertex('A');     // 0 (start)
        theGraph.addVertex('B');     // 1
        theGraph.addVertex('C');     // 2
        theGraph.addVertex('D');     // 3
        theGraph.addVertex('E');     // 4

        theGraph.addEdge(0, 1, 50);  // AB 50
        theGraph.addEdge(0, 3, 80);  // AD 80
        theGraph.addEdge(1, 2, 60);  // BC 60
        theGraph.addEdge(1, 3, 90);  // BD 90
        theGraph.addEdge(2, 4, 40);  // CE 40
        theGraph.addEdge(3, 2, 20);  // DC 20
        theGraph.addEdge(3, 4, 70);  // DE 70
        theGraph.addEdge(4, 1, 50);  // EB 50

        System.out.println("Shortest paths");
        theGraph.floyd();             // shortest paths
        theGraph.displayPaths2();
        System.out.println();
    }

    public static void test_2() {
        GraphD2 theGraph = new GraphD2();
        theGraph.addVertex('A');     // 0 (start)
        theGraph.addVertex('B');     // 1
        theGraph.addVertex('C');     // 2
        theGraph.addVertex('D');     // 3
        theGraph.addVertex('E');     // 4

        theGraph.addEdge(0, 1, 50);  // AB 50
        theGraph.addEdge(0, 3, 80);  // AD 80
        theGraph.addEdge(1, 2, 60);  // BC 60
        theGraph.addEdge(1, 3, 90);  // BD 90
        theGraph.addEdge(2, 4, 40);  // CE 40
        theGraph.addEdge(3, 2, 20);  // DC 20
        theGraph.addEdge(3, 4, 70);  // DE 70
        theGraph.addEdge(4, 1, 50);  // EB 50

        System.out.println("Shortest paths");
        theGraph.path();             // shortest paths
        System.out.println();
    }

    public static void test_1()
    {
        GraphD theGraph = new GraphD();
        theGraph.addVertex('A');     // 0  (start)
        theGraph.addVertex('B');     // 1
        theGraph.addVertex('C');     // 2
        theGraph.addVertex('D');     // 3
        theGraph.addVertex('E');     // 4

        theGraph.addEdge(0, 1, 50);  // AB 50
        theGraph.addEdge(0, 3, 80);  // AD 80
        theGraph.addEdge(1, 2, 60);  // BC 60
        theGraph.addEdge(1, 3, 90);  // BD 90
        theGraph.addEdge(2, 4, 40);  // CE 40
        theGraph.addEdge(3, 2, 20);  // DC 20
        theGraph.addEdge(3, 4, 70);  // DE 70
        theGraph.addEdge(4, 1, 50);  // EB 50

        System.out.println("Shortest paths");
        theGraph.path2();             // shortest paths
        System.out.println();
    }  // end main()
}
