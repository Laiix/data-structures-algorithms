package com.eussi.ch06_recursion;

import com.eussi.data._06.Group;
import com.eussi.data._06.Knapsack;


import java.util.Arrays;

import static com.eussi.util.PrintUtil.*;

/**
 * @author wangxueming
 * @create 2020-01-10 14:10
 * @description 编程作业
 */
public class Exercise {
    public static void main(String[] args) {
        /**
         * 6.1	假设买了一个价格便宜的掌上电脑，但是发现它内置的芯片不能做乘法，只能做
         * 加法。摆脱这种窘境需要自己编写程序，写的是一个递归的方法mult()，它的参
         * 数是x和y返回值是x乘它.当这个方法调用自身或者当它返回的时候，加法是否起
         * 作用了?
         */
        printExercise("6.1");
        exercise_1();

        /**
         *6.2	在第8章"二叉树"中，我们将看到二叉树，在它的每一个节点中确实有两个子树。
         *	如果使用字符在屏幕上画一棵二叉树，可以在顶层有一个节点，在下一层有两个，
         *	然后是4、8、16个，依此类推。下面是一棵最底层有16个字符的树的样子：
         *	--------X-------
         *	----X-------X---
         *	--X---X---X---X-
         *	-X-X-X-X-X-X-X-X
         *	XXXXXXXXXXXXXXXX
         *	（注意最下面的一行，应当向右移动半个字符宽，但是在字符模式中做不到。）
         *	可以使用递归的makeBranches()方法来画这个树，这个方法的参数为left和
         *	right，它们是水平范围的端点。当第一次进入这个程序的时候，left等于0，而
         *	且right是所有行中字符的数目（包括短线）减一。在这行范围的中间画一个X。
         *	然后这个方法调用自己两次：一次左一半的范围，一次右一半的范围。当这个范
         *	围太小的时候返回。你可能想要把所有的短线和X都放到一个数组中，并且一次性
         *	显示数组，或许可以使用display()方法。编写main()调用makeBranches()和
         *	display()来画这棵树。允许main()决定显示的每一行的宽度（32，64或者其他
         *	的任何值）。确保存放显示字符的数组不会比所需要的空间大。行数(图中为五）
         *	和每一行的宽度有什么关系？
         */
        sepExercise("6.2");
        exercise_2();

        /**
         *6.3	应用递归的算法来实现求一个数的乘方，如在这一章接近结尾处的"求一个数的乘
         *	方"部分所讲。写递归的power()方法以及一个main()来测试它。
         */
        sepExercise("6.3");
        exercise_3();

        /**
         * 	6.4	写一个能解决背包问题的程序，任意给定背包的容量以及一系列物品的重量，设
         * 		把这些重量值存在一个数组中。提示：递归方法knapsack()的参数是目标重量和
         * 		剩下物品开始位置的数组下标。
         */
        sepExercise("6.4");
        exercise_4();

        /**
         * 6.5 编写递归的showTeams()方法和一个main()方法来提示用户输入人群的人数以及
         * 		组队的人数，以此来作为showTeams()的参数，然后显示所有的组合。
         *
         * 	    同样可以使用深度优先搜索
         */

        sepExercise("6.5");
        group(new char[] { 'A', 'B', 'C', 'D', 'E' }, 3);

    }

    private static void exercise_4() {
        int[] weights = new int[]{11, 8, 7, 6, 5, 4, 3};
        int target = 20;
        println("背包问题：");
        println("weights: " + Arrays.toString(weights));
        println("target: " + target);
        Knapsack knapsack = new Knapsack(weights);

        knapsack.knapsack(target, 0);

        println("dfs: ");
        knapsack.knapsackDfs(target, 0);
    }

    private static void exercise_3() {
        println(power(2,3));
    }

    private static void exercise_2() {
        showTree(32);
    }

    private static void showTree(int num) {
        int layer = 1;//需要获取层数，决定数组大小
        int temp = num;
        while(temp/2!=0) {
            layer ++;
            temp /= 2;
        }
        println("layer: " + layer);
        char[][] rets = new char[layer][num];
        makeBranches(rets, 0, 0, num);
        for(char[] chars : rets) {//打印输出
            for(char c : chars)
                print(c);
            println();
        }
    }
    private static void makeBranches(char[][] rets, int layer, int left, int right) {
        int mid = (left + right)/2;
        for(int i=left; i<right; i++) {
            if(i == mid) {
                rets[layer][i] = 'X';
            } else {
                rets[layer][i] = '-';
            }
        }

        if(right-left <= 1) //注意结束条件不是left, right，每次去区间为[left, right)，取区间[left, right]，可以这样判断
            return;

        makeBranches(rets, layer+1, left, mid);
        makeBranches(rets, layer+1, mid, right);
    }

    private static void exercise_1() {
        println(mult(3,2));
    }


    public static void group(char[] groups, int num) {
        Group group = new Group(groups);
        group.showTeams(num);
    }


    public static int power(int x, int y) {
        if (y == 1) {
            return x;
        } else {
            if (y % 2 == 0) {
                return power(x * x, y / 2);
            } else {
                return x * power(x * x, y / 2);
            }
        }
    }




    public static int mult(int x, int y) {
        if (y == 1) {
            return x;
        } else {
            return x + mult(x, y - 1);
        }
    }




}
