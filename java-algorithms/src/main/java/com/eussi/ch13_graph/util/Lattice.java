package com.eussi.ch13_graph.util;

/**
 * @author wangxueming
 * @create 2020-03-09 18:33
 * @description
 */
public class Lattice {
    public int f; // 从前驱棋格的哪个方向而来
    public int x;
    public int y;

    public Lattice(int f, int x, int y) {
        this.f = f;
        this.x = x;
        this.y = y;
    }

    public Lattice getNextLattice(int f, Direction d) {
        return new Lattice(f, this.x + d.x, this.y + d.y);
    }
}
