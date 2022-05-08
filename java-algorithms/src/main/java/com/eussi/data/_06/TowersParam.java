package com.eussi.data._06;

public class TowersParam {
    public TowersParam(boolean endFlag, char src, char inter, char dest, int dishN) {
        this.endFlag = endFlag;
        this.src = src;
        this.inter = inter;
        this.dest = dest;
        this.dishN = dishN;
    }

    public boolean endFlag;
    public char src;
    public char inter;
    public char dest;
    public int dishN;

    @Override
    public String toString() {
        return "TowersParam{" +
                "endFlag=" + endFlag +
                ", src=" + src +
                ", inter=" + inter +
                ", dest=" + dest +
                ", dishN=" + dishN +
                '}';
    }
}
