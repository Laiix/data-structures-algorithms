package com.eussi.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueue {
    public static void main(String[] args) {
        NQueue nQueue = new NQueue();
        List<List<char[]>> res = nQueue.nQueue(5);
        for(List<char[]> ret : res) {
            for(char[] row : ret) {
                for(char c : row) {
                    System.out.print(c + " ");
                }
                System.out.println();
            }

            System.out.println("\n---\n");

        }

    }

    private List<List<char[]>> res = new ArrayList<List<char[]>>();

    private List<List<char[]>> nQueue(int n) {
        List<char[]> board = new ArrayList<char[]>();
        for(int i=0; i < n; i++) {
            char[] chars = new char[n];
            for(int j=0; j<chars.length; j++)
                chars[j] = '-';
            board.add(chars);
        }

        backtrack(board, 0);

        return res;

    }

    private void backtrack(List<char[]> board, int row) {
        if(row==board.size()) {
            ArrayList<char[]> list = new ArrayList<char[]>();
            for(char[] c : board) {
                list.add(Arrays.copyOf(c, c.length));
            }

            res.add(list);
            return;
        }

        int n = board.get(row).length;
        for(int i=0; i<n; i++) {
            if(!isValid(board, row, i))
                continue;

            board.get(row)[i] = 'Q';

            backtrack(board, row + 1);

            board.get(row)[i] = '-';
        }

    }

    /* 是否可以在 board[row][col] 放置皇后？ */
    private boolean isValid(List<char[]> board, int row, int col) {
        int n = board.size();
        // 检查列是否有皇后互相冲突
        for (int i = 0; i < n; i++) {
            if (board.get(i)[col] == 'Q')
                return false;
        }
        // 检查右上⽅是否有皇后互相冲突
        for (int i = row - 1, j = col + 1;
             i >= 0 && j < n; i--, j++) {
            if (board.get(i)[j] == 'Q')
                return false;
        }
        // 检查左上⽅是否有皇后互相冲突
        for (int i = row - 1, j = col - 1;
             i >= 0 && j >= 0; i--, j--) {
            if (board.get(i)[j] == 'Q')
                return false;
        }
        return true;
    }
}
