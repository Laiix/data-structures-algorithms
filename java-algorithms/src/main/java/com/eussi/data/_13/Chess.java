package com.eussi.data._13;


import com.eussi.data._04.StackX;

import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * @author wangxueming
 * @create 2020-03-09 18:34
 * @description
 */
public class Chess {

    // 参数
    private boolean[][] chessBoard; // 棋盘
    int MAX_X = 5; // 棋盘宽
    int MAX_Y = 5; // 棋盘高
    private int number; // 未访问棋格数
    private Lattice[] path;

    private Direction[] direction = new Direction[] {
            new Direction(2, -1),     //骑士只能根据象棋的规则进行移动，
            new Direction(2, 1),     // 要么纵向跳动一格横向跳动两格
            new Direction(1, 2),    //要么横向跳动一格纵向跳动两格
            new Direction(-1, 2),

            new Direction(-2, 1),
            new Direction(-2, -1),
            new Direction(-1, -2),
            new Direction(1, -2)
    };

    public Chess(int x, int y) {
        this.MAX_X = x;
        this.MAX_Y = y;
        this.number = MAX_X * MAX_Y; // 未访问棋格数
        this.chessBoard = new boolean[MAX_X][MAX_Y]; // 棋盘
        for (int i = 0; i < MAX_X; i++) { // 初始化棋盘
            for (int j = 0; j < MAX_Y; j++) {
                chessBoard[i][j] = false;
            }
        }
        path = new Lattice[number];
    }

    // 判断给定棋格lattice，是否在棋盘内，超出范围则不合法
    public boolean isValid(Lattice lattice) {
        if (lattice.x >= 0 && lattice.y >= 0 && lattice.x < MAX_X
                && lattice.y < MAX_Y) {
            return true;
        }
        return false;
    }

    // lattice 给定的棋格
    // f 开始遍历的方法
    // 返回lattice的下一个未访问的后继棋格
    public Lattice getNextUnVisitedLattice(int f, Lattice lattice) {
        for (int i = f; i < direction.length; i++) {
            Lattice temp = lattice.getNextLattice(i, direction[i]);
            if (isValid(temp)) { // 在棋盘内
                if (!chessBoard[temp.x][temp.y]) { // 没有访问
                    return temp;
                }
            }
        }
        return null;
    }

    // 编程作业 13.5
    // 骑士的旅行
    // 过程：
    // 首先任选一个棋格标记为已访问并加入栈
    // 如果栈不为空-------(1)
    // --找栈顶棋格的后继未访问棋格
    // --如果找到，则后继未访问棋格标记为已访问，并入栈
    // --如果未找到，则把栈顶元素退栈
    // --如果所有棋格都已入栈，则骑士旅行完成，方法结束
    // 循环进入(1)
    // 如果栈为空
    // 则未找到骑士旅行的路径，方法结束
    public void knightTour() {
        StackX<Lattice> path = new StackX<>(MAX_X * MAX_Y); // 存放已访问的棋格
        path.push(new Lattice(-1, 0, 0)); // 从第(0,0)个棋格开始
        number--;
        chessBoard[0][0] = true;
        // disPlayChessBoard();
        int f = 0; // 方向

        while (!path.isEmpty()) {
            Lattice temp = getNextUnVisitedLattice(f, path.peek()); // 后继未访问棋格
            if (temp == null) { // 没找到
                Lattice l = path.pop();
                chessBoard[l.x][l.y] = false;
                f = l.f + 1; // 下一个方向
                number++;
            } else {// 找到
                chessBoard[temp.x][temp.y] = true;
                path.push(temp);
                f = 0; // 下一个方向，重新从零开始
                number--;
            }

            // 如果number == 0,说明，全部棋格已入栈，则骑士旅行完成
            if (number == 0) {
                int j = this.path.length - 1;
                while (!path.isEmpty()) { // 把栈中棋格转存入数组
                    this.path[j--] = path.pop();

                }
                disPlayKnightTour(); // 显示骑士旅行路径
                println("success!找到骑士旅行的路径!");
                return;
            }
        }
        println("failure!找不到骑士旅行的路径!");
    }

    // 显示骑士旅行路径
    public void disPlayKnightTour() {
        for (int i = 0; i < MAX_X; i++) { // 初始化棋盘
            for (int k = 0; k < MAX_Y; k++) {
                chessBoard[i][k] = false;
            }
        }
        for (int i = 0; i < path.length; i++) { // 骑士每移动一步，打印一次棋盘
            Lattice temp = path[i];
            chessBoard[temp.x][temp.y] = true;
            disPlayChessBoard();
        }
    }

    // 打印棋盘
    public void disPlayChessBoard() {
        print("  ");
        for (int i = 0; i < MAX_Y; i++) {
            print(" " + i);
        }
        println();
        for (int i = 0; i < MAX_X; i++) {
            print(" " + i);
            for (int j = 0; j < MAX_Y; j++) {
                if (!chessBoard[i][j]) {
                    print(" □");
                } else {
                    print(" ■");
                }
            }
            println();
        }
        println();
    }

}
