package com.eussi.data._05;

public class SimpleLinkData {
    private int iData;
    private double dData;

    public SimpleLinkData(int iData, Double dData) {
        this.iData = iData;
        this.dData = dData;
    }

    public int getiData() {
        return iData;
    }

    public void setiData(int iData) {
        this.iData = iData;
    }

    public Double getdData() {
        return dData;
    }

    public void setdData(Double dData) {
        this.dData = dData;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof SimpleLinkData)) {
            return false;
        }
        SimpleLinkData other = (SimpleLinkData) obj;
        return iData==other.getiData()
                && dData-other.getdData()<0.000000001D;
    }

    @Override
    public String toString() {
        return iData + ", " + dData;
    }
}
